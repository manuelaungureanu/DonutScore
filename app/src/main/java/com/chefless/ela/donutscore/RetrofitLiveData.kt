package com.chefless.ela.donutscore

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ela on 07/12/2017.
 */
class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<T>(), Callback<T> {

    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        //not implemented
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        value = response?.body()
    }

    fun cancel() = if (!call.isCanceled) call.cancel() else Unit
}