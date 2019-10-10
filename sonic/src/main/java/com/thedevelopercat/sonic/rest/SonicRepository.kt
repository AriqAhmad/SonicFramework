package com.thedevelopercat.sonic.rest

import android.app.Application
import androidx.annotation.RawRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.thedevelopercat.sonic.exceptions.InvalidRequestException
import com.thedevelopercat.sonic.exceptions.NoNetworkException
import com.thedevelopercat.sonic.utils.NetworkUtils
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream


abstract class SonicRepository<Service> {

    protected var service: Service? = null
    abstract fun getServiceClass(): Class<Service>
    abstract fun onFailure(t: Throwable?, requestType: Int)
    abstract fun onInvalidRequest(
        requestType: Int,
        result: MutableLiveData<SonicResponse>
    )
    abstract fun getErrorResponseKeys(): Array<String>

    fun <T : SonicResponse> makeRequest(call: Call<T>?, requestType: Int): LiveData<SonicResponse> {
        val result = MutableLiveData<SonicResponse>()
        if (NetworkUtils.isNetworkConnected()) {
            call?.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>?, response: Response<T>?) {
                    if (response?.isSuccessful == true) {
                        handleResponse(response, requestType, result)
                    } else {
                        handleRequestInvalid(response, requestType, result)
                    }
                }

                override fun onFailure(call: Call<T>?, t: Throwable?) {
                    handleFailure(t, requestType, result)
                }
            })
        } else {
            handleFailure(NoNetworkException(), requestType, result)
        }
        return transformResponse(requestType, result)
    }

    private fun <T : SonicResponse> handleRequestInvalid(
        response: Response<T>?,
        requestType: Int,
        result: MutableLiveData<SonicResponse>
    ) {
        var errorMessage = ""
        try {
            val errorBody = JSONObject(response?.errorBody()?.string() ?: "")
            for (key in getErrorResponseKeys()){
                if(errorBody.has(key)){
                    errorMessage = errorBody.getString(key)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val status = response?.code() ?: 404
        val res = SonicResponse()
        res.error = if(errorMessage.isEmpty()) InvalidRequestException() else InvalidRequestException(errorMessage)
        res.errorMessage = errorMessage
        res.status = status
        result.value = res
        onInvalidRequest(requestType, result)
    }

    fun <T : SonicResponse> handleResponse(
        response: Response<T>?, requestType: Int,
        result: MutableLiveData<SonicResponse>
    ) {
        response?.body()?.let { data ->
            data.status = response.code()
            result.value = data
            return
        }
        handleRequestInvalid(response, requestType, result)
    }

    fun handleFailure(t: Throwable?, requestType: Int, result: MutableLiveData<SonicResponse>) {
        var status = -1
        if (!NetworkUtils.isNetworkConnected()) {
            status = 408
        }
        val response = SonicResponse()
        response.status = status
        response.error = t
        result.value = response

        onFailure(t, requestType)
    }

    protected open fun transform(requestType: Int, response: SonicResponse?): SonicResponse? {
        return response
    }

    private fun transformResponse(
        requestType: Int,
        result: MutableLiveData<SonicResponse>
    ): LiveData<SonicResponse> {
        return Transformations.switchMap(result) {
            val data = MutableLiveData<SonicResponse>()
            data.value = transform(requestType, it)
            data
        }
    }

    fun <T : SonicResponse> parseLocalJson(@RawRes id: Int, type: Class<T>): T? {
        val json = jsonFromRawRes(id)
        return try {
            Gson().fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    private fun jsonFromRawRes(@RawRes id: Int): String? {
        return try {
            val inputStream: InputStream = getApplication().resources.openRawResource(id)
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }

    abstract fun getApplication(): Application
}
