package com.chefless.ela.donutscore

import android.app.Application
import android.arch.lifecycle.*
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.view.animation.Animation


class ScoreActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var viewModel: ScoreViewModel

    private lateinit var scoreView: ScoreView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_score)
        scoreView = findViewById(R.id.donutView)
        viewModel = obtainViewModel(this)
        viewModel.scoreData.observe(this, Observer { result -> refreshView(result) })

        animateScoreView(android.R.anim.fade_in)
    }

    private fun refreshView(result: Model.Result?) {
        animateScoreView(android.R.anim.fade_out)

        scoreView.score = result?.creditReportInfo?.score ?: 0f
        scoreView.maxScore = result?.creditReportInfo?.maxScoreValue ?: 0f
        scoreView.invalidateOutline()
    }

    private fun animateScoreView(animationId: Int) {
        val mLoadAnimation = AnimationUtils.loadAnimation(applicationContext, animationId)
        scoreView.startAnimation(mLoadAnimation)
    }

    private fun obtainViewModel(activity: AppCompatActivity): ScoreViewModel {

        //TODO: to build injection instead of passing concrete instance of DataModel
        val dataModel = DataModel()
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ScoreViewModelFactory(dataModel)

        return ViewModelProviders.of(activity, factory).get(ScoreViewModel::class.java)
    }

    /** Simple factory class to provide view model */
    inner class ScoreViewModelFactory(private val dataModel: DataModel) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ScoreViewModel(dataModel, Application()) as T
        }
    }
}
