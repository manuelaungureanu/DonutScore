package com.chefless.ela.donutscore

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by ela on 07/12/2017.
 */
interface IDataModel {
    abstract fun getScoreData(): Observable<Model.Result>
}