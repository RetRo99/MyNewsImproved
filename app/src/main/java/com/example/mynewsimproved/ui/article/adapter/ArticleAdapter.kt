package com.example.mynewsimproved.ui.articleFragment.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.WebViewActivity
import com.example.mynewsimproved.ui.articleFragment.model.UiArticle
import com.example.mynewsimproved.utils.inflate
import com.example.mynewsimproved.utils.loadImage
import kotlinx.android.synthetic.main.article_row.view.*

class ArticleAdapter(
    private var articles: List<UiArticle>
) : RecyclerView.Adapter<ArticleAdapter.ArticleVH>() {


    class ArticleVH(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindArticle(article: UiArticle) {
            view.apply {
                dateCreatedTextView.text = article.publishedDate
                sectionSubSectionTextView.text = article.section
                abstractTextView.text = article.abstract
                imageView.loadImage(article.photoUrl)

                setOnClickListener {
                    val webViewIntent = Intent(context, WebViewActivity::class.java)
                    webViewIntent.putExtra("WEBSITE_ADDRESS", article.webUrl)
                    context?.startActivity(webViewIntent)

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val inflatedView = parent.inflate(R.layout.article_row, false)
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
