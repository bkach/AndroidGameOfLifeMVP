package com.github.bkach.gameoflife

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding.view.RxView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_grid.*
import rx.Subscription

class GridActivity() : AppCompatActivity(), GridView {
    var presenter : GridPresenter = GridPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        presenter.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getOnGridUpdateSubscription(gridUpdateObservable: Observable<List<List<Boolean>>>):
            Subscription {
        return gridUpdateObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    canvasGrid.grid = it
                    asciiGrid.grid = it
                }
    }

    override fun getOnGridTouchObservable(): Observable<Pair<Int,Int>> {
        return canvasGrid.getOnGridTouchedObservable().subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun getOnGridClearObservable(): Observable<Void> {
        return RxView.clicks(asciiGrid).subscribeOn(AndroidSchedulers.mainThread())
    }
}
