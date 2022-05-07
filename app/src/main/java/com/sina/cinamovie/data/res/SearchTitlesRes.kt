package com.sina.cinamovie.data.res

data class SearchTitlesRes(
    val certificate: String?,
    val cover: String?,
    val directors: List<Director>?,
    val genres: String?,
    val imdbRating: String?,
    val numberOfVotes: String?,
    val position: String?,
    val runtime: String?,
    val stars: List<Star>?,
    val summary: String?,
    val title: String?,
    val titleId: String?,
    val year: String?
) {

    data class Director(
        val name: String?,
        val nameId: String?
    )

    data class Star(
        val name: String?,
        val nameId: String?
    )

}
