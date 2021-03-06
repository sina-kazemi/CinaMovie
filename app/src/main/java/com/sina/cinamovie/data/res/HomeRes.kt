package com.sina.cinamovie.data.res

data class HomeRes(
    val boxOffice: BoxOffice?,
    val editorPicks: List<EditorPick>?,
    val featuredToday: List<FeaturedToday>?,
    val imdbOriginals: List<ImdbOriginal>?,
    val news: List<News>?,
    val trailers: List<Trailer>?
) {
    data class BoxOffice(
        val weekendStartDate: String?,
        val weekendEndDate: String?,
        val data: List<Data>? = null
    ) {
        data class Data(
            val weekendGross: Int?,
            val currency: String?,
            val cinemas: Int?,
            val title: String?,
            val titleId: String?,

            )
    }

    data class EditorPick(
        val caption: String?,
        val cover: String?,
        val id: String?,
        val image: Boolean?,
        val link: String?,
        val rmId: String?,
        val title: String?,
        val video: Boolean?
    )

    data class FeaturedToday(
        val caption: String?,
        val cover: String?,
        val id: String?,
        val image: Boolean?,
        val link: String?,
        val rmId: String?,
        val title: String?,
        val video: Boolean?
    )

    data class ImdbOriginal(
        val caption: String?,
        val cover: String?,
        val id: String?,
        val image: Boolean?,
        val link: String?,
        val rmId: String?,
        val title: String?,
        val video: Boolean?
    )

    data class News(
        val id: String?,
        val date: String?,
        val image: String?,
        val source: String?,
        val title: String?
    )


    data class Trailer(
        val title: String?,
        val subTitle: String?,
        val cover: String?,
        val titleId: String?,
        val videoId: String?,
        val preview: String?,
        val duration: String?,
    )

}
