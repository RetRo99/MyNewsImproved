package com.example.mynewsimproved.RecycleViewAdapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsimproved.ArticleModel.TopStoryArticle
import com.example.mynewsimproved.R
import com.example.mynewsimproved.WebViewActivity
import com.example.mynewsoc.Utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row.view.*

class TopStoryArticleAdapter(private var articles: ArrayList<TopStoryArticle>, private val context: Context?) : RecyclerView.Adapter<TopStoryArticleAdapter.TopStoriesArticleHolder>() {

    var mArticles:ArrayList<TopStoryArticle> = this.articles


    class TopStoriesArticleHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindArticle(article: TopStoryArticle, context:Context?) {
            view.dateCreatedTextView.text = article.published_date.subSequence(0,10)
            val subsection = article.subsection
            val section = article.section
            val final = "$subsection > $section"

            if(subsection != ""){
                view.sectionSubSectionTextView.text = final
            }else{
                view.sectionSubSectionTextView.text = section
            }
            view.abstractTextView.text = article.abstract
             if(article.multimedia.size != 0) Picasso.get().load(article.multimedia[0].url).into(view.imageView)


            view.setOnClickListener{

                val webViewIntent =  Intent(context, WebViewActivity::class.java)
                webViewIntent.putExtra("WEBSITE_ADDRESS", article.url)
                context?.startActivity(webViewIntent)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesArticleHolder {
        val inflatedView = parent.inflate(R.layout.article_row, false)
        return TopStoriesArticleHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    override fun onBindViewHolder(holder: TopStoriesArticleHolder, position: Int) {
        if(articles.size > 0) {
            val topStoryArticle = mArticles[position]
            holder.bindArticle(topStoryArticle, context)
        }
    }
}