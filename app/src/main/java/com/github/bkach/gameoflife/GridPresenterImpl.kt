package com.github.bkach.gameoflife

import com.github.bkach.gameoflife.model.Game
import rx.Subscription
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by boris on 3/5/16.
 */
class GridPresenterImpl(private val view: GridView) : GridPresenter {
    private val subscriptions = CompositeSubscription()
    private val model = Game()

    override fun onCreate() {
        addSubscription(
                view.getOnGridUpdateSubscription(
                        model.boardUpdateObservable
                                .subscribeOn(Schedulers.io())
                                .map { it.grid }))

        addSubscription(
                model.subscribeToOnGridTouchObservable(
                        view.getOnGridTouchObservable().observeOn(Schedulers.io())))

        addSubscription(
                model.subscribeToOnGridClearObservable(
                        view.getOnGridClearObservable().observeOn(Schedulers.io())))
    }

    override fun onPause() {
        model.pause()
    }

    override fun onResume() {
        model.start()
    }

    override fun onDestroy() {
        subscriptions.unsubscribe();
    }

    protected fun addSubscription(subscription: Subscription) {
        subscriptions.add(subscription)
    }
}