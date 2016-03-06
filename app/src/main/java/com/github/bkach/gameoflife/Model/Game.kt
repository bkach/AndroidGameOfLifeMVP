package com.github.bkach.gameoflife.Model

import rx.Observable
import rx.Subscription
import java.util.concurrent.TimeUnit.MILLISECONDS


/**
 * Created by boris on 3/5/16.
 */
class Game() {
    val board: Board = Board(50)
    val boardUpdateObservable: Observable<Board> = buildUpdateBoardObservable();
    private var paused = true

    private fun buildUpdateBoardObservable(): Observable<Board> {
        return timer()
                .map { board.nextGeneration() }
                .map { board }
    }

    private fun timer() : Observable<Long> {
        return Observable.interval(100, MILLISECONDS)
                .filter { !paused }
    }

    fun start() {
        paused = false
    }

    fun pause() {
        paused = true
    }

    fun subscribeToOnGridTouchObservable(onGridTouchObservable: Observable<Pair<Int,Int>>): Subscription {
        return onGridTouchObservable.subscribe { board.toggleNeighborhood(it) }
    }

    fun subscribeToOnGridClearObservable(onGridClearObservable: Observable<Void>): Subscription {
        return onGridClearObservable.subscribe { board.clear() }
    }

}

