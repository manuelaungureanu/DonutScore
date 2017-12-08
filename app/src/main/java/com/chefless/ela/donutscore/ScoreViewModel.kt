package com.chefless.ela.donutscore

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * Created by ela on 07/12/2017.
 */
class ScoreViewModel : AndroidViewModel {

    private lateinit var mDataModel: IDataModel

    lateinit var scoreData: RetrofitLiveData<Model.Result>

    constructor(mContext: Application) : super(mContext)

    constructor(mDataModel: IDataModel, mContext: Application) : super(mContext) {
        this.mDataModel = mDataModel
        this.scoreData = mDataModel.getScoreData()
    }

    override fun onCleared() {
        scoreData.cancel()
    }
}

