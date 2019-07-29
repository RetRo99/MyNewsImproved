package com.example.mynewsimproved.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.example.mynewsimproved.ArticleModel.SearchResponse
import com.example.mynewsimproved.Retrofit.ApiClient
import com.example.mynewsoc.Utils.createOrCancelAlarm
import com.example.mynewsoc.Utils.createNotification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val intentAction = "SHOW_NEW_ARTICLES"
        //It either receives on boot action on my custom action
        when (intent?.action) {
            //If it is a reboot it registers alarm again and check if it needs to save the day
            Intent.ACTION_BOOT_COMPLETED -> {
                createOrCancelAlarm(context, true)

            }
            //if it it our action then it saves the day
            intentAction -> {
                Log.d("dobil", "succesfull")
                val client = ApiClient
                val myFormat = "yyyyMMdd" //In which you need put here
                val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
                val date = Date()

                val sharedPrefSearchPreferences: SharedPreferences =
                    context.getSharedPreferences("search_params", Context.MODE_PRIVATE)
                val query = sharedPrefSearchPreferences.getString("query", "hheh")
                val sections = sharedPrefSearchPreferences.getString("sections", "hheh")

                val callSearchedArticles = client.getClient.getSearchedArticles(query, sdf.format(date), null, sections)
                callSearchedArticles.enqueue(object : Callback<SearchResponse> {

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        Log.d("onFaulur", "Error")
                    }

                    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                        Log.d("onResponse", "Notr")
                        if (response.isSuccessful) {
                            Log.d("onResponse", "succesfull")
                            createNotification(response.body()!!.response.docs.size, context)
                        }

                    }
                })


            }
        }


    }


}