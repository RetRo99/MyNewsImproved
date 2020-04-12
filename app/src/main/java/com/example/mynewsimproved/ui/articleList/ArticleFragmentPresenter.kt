package com.example.mynewsimproved.ui.articleList

import com.example.mynewsimproved.api.repository.NewsRepository
import com.example.mynewsimproved.ui.articleList.types.ArticleType
import com.example.mynewsimproved.ui.mainactivity.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class ArticleFragmentPresenter(
    private val view: ArticleView,
    private val parentView: MainView
) {

    private val repo = NewsRepository
    private var disposable: Disposable? = null
    private lateinit var articleType: ArticleType

    fun onViewCreated(articleType: ArticleType) {
        this.articleType = articleType
        loadData()

    }

    fun onArticleClicked(url: String) {
        parentView.fromHomeToWeb(url)
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    private fun loadData(){
        disposable = repo.loadArticles(articleType)
            .doOnSubscribe {
                view.showLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    view.showData(it)
                    view.hideLoading()
                },
                onError = {
                    it.printStackTrace()
                })
    }

    fun onRefresh() {
        loadData()
    }
}
