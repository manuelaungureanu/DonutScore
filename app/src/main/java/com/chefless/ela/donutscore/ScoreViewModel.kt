package com.chefless.ela.donutscore

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by ela on 07/12/2017.
 */
class ScoreViewModel: AndroidViewModel {

    var mDataModel: IDataModel

    //Application context will be used to prevent memory leaks
    lateinit var mContext: Context

    constructor(mDataModel: IDataModel, mContext:Application): super(mContext)
    {
        this.mDataModel = mDataModel
    }

    fun getScoreData(): Observable<Model.Result> {
        return mDataModel.getScoreData()
    }
}

