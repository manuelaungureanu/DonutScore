package com.chefless.ela.donutscore

/**
 * Created by ela on 07/12/2017.
 */
class DataModel : IDataModel {

    private val scoreApiService by lazy {
        ScoreApiService.create()
    }

    override fun getScoreData(): RetrofitLiveData<Model.Result> {
        return RetrofitLiveData(scoreApiService.hitScoreCheck())
    }
}