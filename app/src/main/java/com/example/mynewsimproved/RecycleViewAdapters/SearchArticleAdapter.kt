package com.example.mynewsimproved.RecycleViewAdapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsimproved.ArticleModel.SearchResponse
import com.example.mynewsimproved.R
import com.example.mynewsimproved.WebViewActivity
import com.example.mynewsoc.Utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row.view.*

class SearchArticleAdapter(private var articles: ArrayList<SearchResponse.ResponseSearch.SearchedArticle>, private val context: Context?) : RecyclerView.Adapter<SearchArticleAdapter.SearchedArticleHolder>() {

    var mArticles:ArrayList<SearchResponse.ResponseSearch.SearchedArticle> = this.articles


    class SearchedArticleHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindArticle(article: SearchResponse.ResponseSearch.SearchedArticle, context:Context?) {
            view.dateCreatedTextView.text = article.published_date.subSequence(0,10)


            val subsection = article.subsection
            val section = article.section
            val final = "$subsection > $section"

            if(subsection != null){
                view.sectionSubSectionTextView.text = final
            }else{
                view.sectionSubSectionTextView.text = section
            }

            view.abstractTextView.text = article.abstract
             if(article.multimedia.size != 0){
                 val url = "https://static01.nyt.com/${article.multimedia[1].url}"
                 Picasso.get().load(url).into(view.imageView)
             }


            view.setOnClickListener{

                val webViewIntent =  Intent(context, WebViewActivity::class.java)
                webViewIntent.putExtra("WEBSITE_ADDRESS", article.url)
                context?.startActivity(webViewIntent)


            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedArticleHolder {
        val inflatedView = parent.inflate(R.layout.article_row, false)
        return SearchedArticleHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    override fun onBindViewHolder(holder: SearchedArticleHolder, position: Int) {
        if(articles.size > 0) {
            val topStoryArticle = mArticles[position]
            if(topStoryArticle.abstract != null)holder.bindArticle(topStoryArticle, context)

        }
    }
}