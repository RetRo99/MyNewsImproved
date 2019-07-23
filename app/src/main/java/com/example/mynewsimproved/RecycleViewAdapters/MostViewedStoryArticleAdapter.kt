package com.example.mynewsimproved.RecycleViewAdapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsimproved.ArticleModel.MostViewedArticle
import com.example.mynewsimproved.R
import com.example.mynewsimproved.WebViewActivity
import com.example.mynewsoc.Utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row.view.*


class MostViewedStoryArticleAdapter(private var articles: ArrayList<MostViewedArticle>, private val context: Context?) : RecyclerView.Adapter<MostViewedStoryArticleAdapter.TopStoriesArticleHolder>() {

    var mArticles:ArrayList<MostViewedArticle> = articles




    class TopStoriesArticleHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindArticle(article: MostViewedArticle, context:Context?) {
            view.dateCreatedTextView.text = article.published_date.subSequence(0,10)



                view.sectionSubSectionTextView.text = article.section

            view.abstractTextView.text = article.abstract
             if(article.media[0].mostViewedMetaData.size != 0) Picasso.get().load(article.media[0].mostViewedMetaData[0].url).into(view.imageView)


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
        val mostViewedArticle = mArticles[position]
        holder.bindArticle(mostViewedArticle, context)
    }
}