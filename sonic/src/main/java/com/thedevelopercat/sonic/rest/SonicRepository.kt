package com.thedevelopercat.sonic.rest

import android.app.Application
import androidx.annotation.RawRes
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.thedevelopercat.sonic.exceptions.NoNetworkException
import com.thedevelopercat.sonic.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream


abstract class SonicRepository {

    fun <T : SonicResponse> makeRequest(call: Call<T>, requestType: Int): MutableLiveData<T> {
        val result = MutableLiveData<T>()
        if (NetworkUtils.isNetworkConnected()) {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>?, response: Response<T>?) {
                    handleResponse(response, requestType, result)
                }

                override fun onFailure(call: Call<T>?, t: Throwable?) {
                    handleFailure(requestType, result)
                }
            })
        } else {
            handleFailure(requestType, result)
        }
        return result
    }

    fun <T : SonicResponse> handleResponse(
        response: Response<T>?, requestType: Int, result: MutableLiveData<T>) {
        response?.body()?.let { data ->
            data.status = response.code()
            data.success = true
            result.value = data
            return
        }
        onRequestFailed(requestType, result, response?.errorBody().toString())
    }

    open fun <T : SonicResponse> onRequestFailed(
        requestType: Int,
        result: MutableLiveData<T>,
        errorBody: String?
    ) {

    }

    fun <T : SonicResponse> handleFailure(requestType: Int, result: MutableLiveData<T>) {
        var status = -1
        val response = SonicResponse()

        if (!NetworkUtils.isNetworkConnected()) {
            status = 408
            response.error = NoNetworkException()
            response.errorMessage = response.error?.toString()
        }
        response.status = status

        onRequestFailed(requestType, result, response.toString())
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