package com.example.project

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val search: List<MovieSearchResult>,
    val totalResults: Int,
    val response: Boolean
)

data class MovieSearchResult(
    val title: String,
    val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("type") val mediaType: String,
    @SerializedName("poster") val posterUrl: String?
)
data class Movie(
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("type") val mediaType: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: Boolean
)

