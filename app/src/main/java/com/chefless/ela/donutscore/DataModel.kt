package com.chefless.ela.donutscore

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by ela on 07/12/2017.
 */
class DataModel: IDataModel {

    val scoreApiServe by lazy {
        ScoreApiService.create()
    }

    override fun getScoreData(): Observable<Model.Result>
    {
        return scoreApiServe.hitScoreCheck()
    }
}