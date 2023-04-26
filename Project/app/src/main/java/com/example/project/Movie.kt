package com.example.project

import java.io.Serializable


data class Movie(
    val title: String,
    val year: String,
    val poster: String,
    val plot: String
): Serializable

