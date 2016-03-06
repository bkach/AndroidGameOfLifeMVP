package com.github.bkach.gameoflife

import rx.Observable
import rx.Subscription

/**
 * Created by boris on 3/5/16.
 */
interface GridView {
    fun getOnGridUpdateSubscription(gridUpdateObservable: Observable<List<List<Boolean>>>): Subscription
    fun getOnGridTouchObservable(): Observable<Pair<Int,Int>>
    fun getOnGridClearObservable(): Observable<Void>
}