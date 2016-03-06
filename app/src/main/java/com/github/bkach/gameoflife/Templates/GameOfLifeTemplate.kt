package com.github.bkach.gameoflife.templates

/**
 * Created by boris on 3/6/16.
 */

abstract class GameOfLifeTemplate(val center: Int) {
    var pairs : List<Pair<Int,Int>> = createPairs()
    abstract fun createPairs() : List<Pair<Int,Int>>
}
