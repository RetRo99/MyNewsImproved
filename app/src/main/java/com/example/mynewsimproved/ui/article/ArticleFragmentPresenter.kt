package com.example.mynewsimproved.ui.article

import com.example.mynewsimproved.api.repository.NewsRepository
import com.example.mynewsimproved.ui.article.types.ArticleType
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

    fun onViewCreated(articleType: ArticleType) {
        disposable = repo.loadArticles(articleType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { view.showData(it) },
                onError = {
                    it.printStackTrace()
                })

    }

    fun onArticleClicked(url: String) {
        parentView.fromHomeToWeb(url)
    }

    fun onDestroy() {
        disposable?.dispose()
    }
}
