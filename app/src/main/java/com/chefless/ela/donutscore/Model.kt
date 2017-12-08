package com.chefless.ela.donutscore

import java.io.Serializable

/**
 * Created by ela on 07/12/2017.
 */
object Model {

    data class Result(val creditReportInfo: CreditReportInfo) : Serializable

    data class CreditReportInfo(val score: Float, val maxScoreValue: Float)
}