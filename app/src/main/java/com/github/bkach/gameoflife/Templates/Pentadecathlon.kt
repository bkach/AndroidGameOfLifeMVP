package com.github.bkach.gameoflife.templates

/**
 * Created by boris on 3/6/16.
 */
class Pentadecathlon(center: Int) : GameOfLifeTemplate(center) {
    override fun createPairs(): List<Pair<Int, Int>> {
        return listOf(
                Pair(center,center),
                Pair(center+1,center),
                Pair(center+2,center+1),
                Pair(center+2,center-1),
                Pair(center+3,center),
                Pair(center+4,center),
                Pair(center-1,center),
                Pair(center-2,center),
                Pair(center-3,center+1),
                Pair(center-3,center-1),
                Pair(center-4,center),
                Pair(center-5,center))
    }
}