package com.github.bkach.gameoflife.Grids

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by boris on 3/6/16.
 */

class AsciiGrid(context: Context?, attrs: AttributeSet?) : TextView(context, attrs) {
    var grid : List<List<Boolean>> = arrayListOf();
    set(value) {
        field = value
        drawAsciiGrid()
    }

    fun drawAsciiGrid() {
        var printString = ""
        for (i in 0..grid.size - 1) {
            for (j in 0..grid.size - 1) {
                val valueString = if(grid[j][i]) "+" else " "
                printString += valueString
            }
            printString += "\n"
        }
        this.text = printString
    }

}
