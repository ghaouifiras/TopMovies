package com.firas.myapplication.data.model

data class MovieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int = 0,
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val origin_country: List<String> = emptyList(),
    val original_language: String = "",
    val original_title: String = "Unknown Title",
    val overview: String = "No overview available",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val release_date: String = "N/A",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String = "Unknown",
    val tagline: String = "",
    val title: String = "Unknown Title",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)