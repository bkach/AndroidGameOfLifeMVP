package com.github.bkach.gameoflife.Model

/**
 * Created by boris on 3/5/16.
 */
class Board(val size: Int = 20) {
    val grid : MutableList<MutableList<Boolean>> = arrayListOf()
    var generation = 0

    init {
        buildEmptyGrid()
        buildPentaDecathlon();
    }

    private fun buildEmptyGrid() {
        grid.clear();
        for (i in 0..size-1) {
            grid.add(arrayListOf())
            for (j in 0..size-1) {
                grid[i].add(false)
            }
        }
    }

    private fun buildPentaDecathlon() {
        val center = size/2
        val booleanValue = true
        grid[center][center] = booleanValue
        grid[center+1][center] = booleanValue
        grid[center+2][center+1] = booleanValue
        grid[center+2][center-1] = booleanValue
        grid[center+3][center] = booleanValue
        grid[center+4][center] = booleanValue
        grid[center-1][center] = booleanValue
        grid[center-2][center] = booleanValue
        grid[center-3][center+1] = booleanValue
        grid[center-3][center-1] = booleanValue
        grid[center-4][center] = booleanValue
        grid[center-5][center] = booleanValue
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

    fun clear() {
        buildEmptyGrid()
    }
}