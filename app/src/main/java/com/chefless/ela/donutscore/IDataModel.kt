package com.chefless.ela.donutscore

/**
 * Created by ela on 07/12/2017.
 */
interface IDataModel {

    fun getScoreData(): RetrofitLiveData<Model.Result>
}