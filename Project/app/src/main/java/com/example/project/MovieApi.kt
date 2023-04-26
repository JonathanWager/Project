package com.example.project

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

    fun fetchMovieData(title: String): Movie? {
        val apiKey = "fd8aff95"
        val client = OkHttpClient()
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("www.omdbapi.com")
            .addQueryParameter("t", title)
            .addQueryParameter("apikey", apiKey)
            .build()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        val json = response.body?.string()
        response.body?.close()
        val jsonObject = JSONObject(json)
        if (jsonObject.getString("Response") == "False") {
            return null
        }
        return Movie(
            title = jsonObject.getString("Title"),
            year = jsonObject.getString("Year"),
            poster = jsonObject.getString("Poster"),
            plot = jsonObject.getString("Plot")
        )
    }