package com.chefless.ela.donutscore

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Paint.Align

/**
 * Created by ela on 06/12/2017.
 */
class ScoreView : View {

    private var ovalOutline = RectF()

    private var ovalScoreOutline = RectF()

    private val paintPrimary = Paint()

    private val paintSecondary = Paint()

    private val mLabelPaint = Paint()

    private val mScorePaint = Paint()

    private val colorPrimary = Color.BLACK

    private val colorSecondary = Color.GREEN

    var score: Float = 0f

    var maxScore: Float = 700f

    private val offsetOutline: Float = 50f

    private val offsetScoreOutline: Float = 70f

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setup()

        displayOutlines(canvas)

        if (canvas != null)
            displayContent(canvas)
    }

    private fun displayOutlines(canvas: Canvas?) {

        val scorePercentageInDegrees = (score / maxScore) * 360

        canvas?.drawArc(ovalOutline, 0f, 360f, false, paintPrimary);

        canvas?.drawArc(ovalScoreOutline, -90f, scorePercentageInDegrees, false, paintSecondary)

    }

    private fun displayContent(canvas: Canvas) {
        val dimen: Float = if (canvas.width < canvas.height) canvas.width.toFloat() else canvas.height.toFloat()

        val positionXForText = dimen / 2
        val positionYForTextBottom = ovalScoreOutline.bottom - dimen / 4
        val positionYForTextCenter = ovalScoreOutline.top + dimen / 2
        val positionYForTextTop = ovalScoreOutline.top + dimen / 4

        canvas.drawText(resources.getString(R.string.your_credit_score_is), positionXForText, positionYForTextTop, mLabelPaint)

        val scoreAsString = String.format("%.${0}f", score)
        canvas.drawText(scoreAsString, positionXForText, positionYForTextCenter, mScorePaint)

        val maxScoreContent: String = String.format("%s %.${0}f", resources.getString(R.string.out_of), maxScore)
        canvas.drawText(maxScoreContent, positionXForText, positionYForTextBottom, mLabelPaint)
    }

    private fun setup() {
        paintPrimary.isAntiAlias = true
        paintPrimary.color = colorPrimary
        paintPrimary.strokeWidth = 5f
        paintPrimary.style = Paint.Style.STROKE
        paintPrimary.strokeCap = Paint.Cap.ROUND

        paintSecondary.isAntiAlias = true
        paintSecondary.strokeWidth = 15f
        paintSecondary.color = colorSecondary
        paintSecondary.style = Paint.Style.STROKE
        paintSecondary.strokeCap = Paint.Cap.ROUND

        mLabelPaint.color = Color.BLACK
        mLabelPaint.textAlign = Align.CENTER
        mLabelPaint.textSize = 50f

        mScorePaint.color = paintSecondary.color
        mScorePaint.textAlign = Align.CENTER
        mScorePaint.isFakeBoldText = true
        mScorePaint.textSize = 200f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initOvals(w, h)
    }

    private fun initOvals(w: Int, h: Int) {
        val minDimen: Float = if (w < h) w.toFloat() else h.toFloat()
        ovalOutline = RectF(offsetOutline, offsetOutline, (minDimen - offsetOutline), (minDimen - offsetOutline))
        ovalScoreOutline = RectF(offsetScoreOutline, offsetScoreOutline, (minDimen - offsetScoreOutline), (minDimen - offsetScoreOutline))
    }
}