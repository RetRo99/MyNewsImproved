package com.example.mynewsimproved.ui.articleList.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.articleList.model.UiArticle
import com.example.mynewsimproved.utils.inflate
import com.example.mynewsimproved.utils.loadImage
import kotlinx.android.synthetic.main.item_article_row.view.*

class ArticleAdapter(
    private var articles: List<UiArticle>, val action: (String) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ArticleVH>() {


    inner class ArticleVH(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindArticle(article: UiArticle) {
            view.apply {
                dateCreatedTextView.text = article.publishedDate
                sectionSubSectionTextView.text = article.section
                abstractTextView.text = article.abstract
                imageView.loadImage(article.photoUrl)

                setOnClickListener {
                    action(article.webUrl)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val inflatedView = parent.inflate(R.layout.item_article_row, false)
        return ArticleVH(inflatedView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        if (articles.isNotEmpty()) {
            val topStoryArticle = articles[position]
            holder.bindArticle(topStoryArticle)
        }
    }
}
