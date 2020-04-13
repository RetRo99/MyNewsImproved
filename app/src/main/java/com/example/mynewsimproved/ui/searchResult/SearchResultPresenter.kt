package com.example.mynewsimproved.ui.searchResult

import com.example.mynewsimproved.api.repository.NewsRepository
import com.example.mynewsimproved.ui.mainactivity.MainView
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class SearchResultPresenter(private val view: SearchResultView, private val parentView: MainView) {

    private val repo = NewsRepository

    private var disposable: Disposable? = null
    private lateinit var params: SearchParam


    fun onViewCreated(
        params: SearchParam
    ) {
        this.params = params
        loadData()
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun onArticleClicked(url: String) {
        parentView.fromHomeToWeb(url)
    }

    private fun loadData() {
        disposable = repo.loadSearchedArticles(params)
            .doOnSubscribe {
                view.showLoading()
            }
            .doOnTerminate {
                view.hideLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if (it.isNotEmpty()) {
                        view.showData(it)
                    } else {
                        view.showNoResultDialog()
                    }

                },
                onError = {
                    it.printStackTrace()
                })



    }

    fun onRefresh() {
        loadData()
    }


}
