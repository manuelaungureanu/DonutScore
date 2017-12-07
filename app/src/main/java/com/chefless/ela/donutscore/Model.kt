package com.chefless.ela.donutscore

/**
 * Created by ela on 07/12/2017.
 */
object Model {
    data class Result(val creditReportInfo: CreditReportInfo)
    data class CreditReportInfo(val score: Float, val maxScoreValue: Float)
}