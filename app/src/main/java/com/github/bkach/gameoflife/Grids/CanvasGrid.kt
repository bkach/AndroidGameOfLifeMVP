package com.github.bkach.gameoflife.grids

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.github.bkach.gameoflife.R
import com.jakewharton.rxbinding.view.RxView
import rx.Observable

/**
 * Created by boris on 3/5/16.
 */
class CanvasGrid(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var grid : List<List<Boolean>> = arrayListOf();
    set(value) {
        field = value
        invalidate()
    }

    var paint = Paint()
    var cellWidth = 0
    var cellHeight = 0


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas);
        if (grid.size > 0) {
            drawGrid(canvas!!)
        } else {
            canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        }
    }

    private fun drawGrid(canvas: Canvas) {
        cellWidth = width / grid.size
        cellHeight = height / grid.size

        for (i in 0..grid.size - 1) {
            for (j in 0..grid.size - 1) {
                val left : Float = i.toFloat() * cellWidth
                val right : Float = left + cellWidth
                val top  : Float = j.toFloat() * cellHeight
                val bottom : Float = top + cellHeight

                val colorId = if (grid[i][j]) android.R.color.black else R.color.background_material_light
                paint.color = context.resources.getColor(colorId, null)

                canvas.drawRect(left, top, right, bottom, paint)
            }
        }
    }

    fun getOnGridTouchedObservable() : Observable<Pair<Int, Int>> {
        return RxView.touches(this)
                .filter { event -> event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE}
                .filter { event -> event.x >= 0 && event.y >= 0 }
                .map { event ->
                        val columnNum = (event.x / cellWidth).toInt()
                        val rowNum = (event.y / cellHeight).toInt()
                        Pair(columnNum, rowNum)
                }
                .filter { point -> point.first < grid.size && point.second < grid.size }
    }

}

