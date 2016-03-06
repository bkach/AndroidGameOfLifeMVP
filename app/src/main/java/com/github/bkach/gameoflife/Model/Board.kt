package com.github.bkach.gameoflife.model

import com.github.bkach.gameoflife.templates.GameOfLifeTemplate
import com.github.bkach.gameoflife.templates.GosperGliderGun
import com.github.bkach.gameoflife.templates.Pentadecathlon

/**
 * Created by boris on 3/5/16.
 */
class Board(val size: Int = 20) {
    var grid : MutableList<MutableList<Boolean>> = arrayListOf()
    val templates : List<GameOfLifeTemplate> = listOf(GosperGliderGun(size/2), Pentadecathlon(size/2))
    var currentTemplate = 0
    var generation = 0

    init {
        createFalseGrid()
        build(getNextTemplate())
    }

    private fun build(template: GameOfLifeTemplate) {
        for (pair in template.pairs) {
            grid[pair.first][pair.second] = true
        }
    }

    private fun createFalseGrid() {
        val tempGrid : MutableList<MutableList<Boolean>> = arrayListOf()
        for (i in 0..size-1) {
            tempGrid.add(arrayListOf())
            for (j in 0..size-1) {
                tempGrid[i].add(false)
            }
        }
        grid = tempGrid;
    }

    fun nextGeneration() {
        if (generation != 0) {
            val changeList: MutableList<Pair<Int, Pair<Int, Boolean>>> = arrayListOf()

            for (i in 0..size - 1) {
                for (j in 0..size - 1) {
                    var numLiveNeighbors = getNumLiveNeighbors(i, j);
                    if (grid[i][j]) {
                        when (numLiveNeighbors) {
                            2, 3 -> changeList.add(Pair(i, Pair(j, true)))
                            else -> changeList.add(Pair(i, Pair(j, false)))
                        }
                    } else if (numLiveNeighbors == 3) {
                        changeList.add(Pair(i, Pair(j, true)))
                    }
                }
            }

            for (value in changeList) {
                grid[value.first][value.second.first] = value.second.second
            }
        }
        generation++
    }

    private fun getNumLiveNeighbors(i: Int, j: Int): Int {
        var liveNeighborCount = 0
        for (k in -1..1) {
            for (l in -1..1) {
                val newColumn = i + k;
                val newRow = j + l;
                if (!(newColumn == i && newRow == j) &&
                    newColumn >= 0 && newColumn < size && newRow >= 0 && newRow < size) {
                    if (grid[newColumn][newRow]) {
                        liveNeighborCount++
                    }
                }
            }
        }
        return liveNeighborCount;
    }

    fun toggleNeighborhood(point: Pair<Int,Int>) {
        for (i in -1..1) {
            for (j in -1..1) {
                val newXPoint = point.first + i
                val newYPoint = point.second + j
                if (newXPoint < grid.size && newXPoint > 0 &&
                        newYPoint < grid.size && newYPoint > 0) {
                    grid[newXPoint][newYPoint] = !grid[newXPoint][newYPoint]
                }
            }
        }
    }

    fun getNextTemplate() : GameOfLifeTemplate {
        currentTemplate = (currentTemplate + 1) % templates.size
        return templates[currentTemplate]
    }

    fun clear() {
        createFalseGrid()
        build(getNextTemplate())
    }
}