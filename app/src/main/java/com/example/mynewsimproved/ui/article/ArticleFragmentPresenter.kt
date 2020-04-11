package com.example.mynewsimproved.ui.article

import com.example.mynewsimproved.api.repository.NewsRepository
import com.example.mynewsimproved.ui.article.types.ArticleType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class ArticleFragmentPresenter(private val view: ArticleView) {

    private val repo = NewsRepository
    private var disposable: Disposable? = null

    fun onViewCreated(articleType: ArticleType) {
        disposable = repo.loadArticles(articleType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {view.showData(it) },
                onError = {
                    it.printStackTrace()
                })

        // TODO not implemented
    }

    fun onDestroy() {
        disposable?.dispose()
    }
}
