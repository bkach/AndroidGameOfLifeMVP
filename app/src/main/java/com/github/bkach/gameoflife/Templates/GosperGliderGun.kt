package com.github.bkach.gameoflife.templates

/**
 * Created by boris on 3/6/16.
 */
class GosperGliderGun(center: Int) : GameOfLifeTemplate(center) {
    override fun createPairs(): List<Pair<Int, Int>> {
        return listOf(
                // Row 0
                Pair(center +  7,center -   5),
                // Row 1
                Pair(center +  7, center -  4),
                Pair(center +  5, center -  4),
                // Row 2
                Pair(center + 18, center -  3),
                Pair(center + 17, center -  3),
                Pair(center +  4, center -  3),
                Pair(center +  3, center -  3),
                Pair(center -  4, center -  3),
                Pair(center -  5, center -  3),
                // Row 3
                Pair(center + 18, center -  2),
                Pair(center + 17, center -  2),
                Pair(center +  4, center -  2),
                Pair(center +  3, center -  2),
                Pair(center -  2, center -  2),
                Pair(center -  6, center -  2),
                // Row 4
                Pair(center +  4, center -  1),
                Pair(center +  3, center -  1),
                Pair(center -  1, center -  1),
                Pair(center -  7, center -  1),
                Pair(center - 16, center -  1),
                Pair(center - 17, center -  1),
                // Row 5
                Pair(center +  7, center),
                Pair(center +  5, center),
                Pair(center     , center),
                Pair(center -  1, center),
                Pair(center -  3, center),
                Pair(center -  7, center),
                Pair(center - 16, center),
                Pair(center - 17, center),
                // Row 5
                Pair(center + 7, center + 1),
                Pair(center - 1, center + 1),
                Pair(center - 7, center + 1),
                // Row 6
                Pair(center - 2, center + 2),
                Pair(center - 6, center + 2),
                // Row 7
                Pair(center - 4, center + 3),
                Pair(center - 5, center + 3)
        )
    }
}