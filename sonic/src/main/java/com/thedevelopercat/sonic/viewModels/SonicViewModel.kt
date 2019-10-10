package com.thedevelopercat.sonic.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.thedevelopercat.sonic.rest.SonicResponse

open class SonicViewModel : ViewModel() {

    var isLoading: ObservableField<Boolean>? = ObservableField(false)

    protected open fun transform(requestCode: Int, response: SonicResponse?): SonicResponse? {
        return response
    }

    protected fun transformResponse(
        requestCode: Int,
        result: LiveData<SonicResponse>
    ): LiveData<SonicResponse> {
        return Transformations.switchMap(result) {
            val data = MutableLiveData<SonicResponse>()
            data.value = transform(requestCode, it)
            data
        }
    }

}