package com.chefless.ela.donutscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ScoreActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    val scoreApiServe by lazy {
        ScoreApiService.create()
    }

    lateinit var scoreView: ScoreView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        scoreView = findViewById(R.id.donutView)
    }


    override fun onResume() {
        super.onResume()

        disposable = scoreApiServe.hitScoreCheck()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> refreshView(result)},
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    private fun refreshView(result: Model.Result){
        scoreView.score = result.creditReportInfo.score
        scoreView.max_score = result.creditReportInfo.maxScoreValue
        scoreView.invalidate()
    }

    override fun onPause() {
        super.onPause()

        disposable?.dispose()
    }
}
