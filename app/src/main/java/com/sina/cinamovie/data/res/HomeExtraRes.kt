package com.sina.cinamovie.data.res

data class HomeExtraRes(
    val bornTodayList: List<BornToday>,
    val comingSoonMovies: List<ComingSoonMovie>,
    val fanPicksTitles: List<FanPicksTitle>,
    val showTimesTitles: List<ShowTimesTitle>,
    val streamingTitles: List<StreamingTitle>
) {
    data class BornToday(
        val birth: String?,
        val death: String?,
        val image: String?,
        val nameId: String?,
        val title: String?
    )

    data class ComingSoonMovie(
        val certificate: String?,
        val cover: String?,
        val rate: Float?,
        val releaseDay: String?,
        val releaseMonth: String?,
        val releaseYear: String?,
        val runtime: Int?,
        val title: String?,
        val titleId: String?,
        val videoId: String?,
        val videoName: String?,
        val videoPreview: String?,
        val videoRuntime: Int?,
        val voteCount: Int?
    )

    data class FanPicksTitle(
        val certificate: String?,
        val cover: String?,
        val rate: Float?,
        val releaseDay: String?,
        val releaseMonth: String?,
        val releaseYear: String?,
        val runtime: Int?,
        val title: String?,
        val titleId: String?,
        val videoId: String?,
        val voteCount: Int?
    )

    data class ShowTimesTitle(
        val certificate: String?,
        val cover: String?,
        val rate: Float?,
        val releaseDay: String?,
        val releaseMonth: String?,
        val releaseYear: String?,
        val runtime: Int?,
        val title: String?,
        val titleId: String?,
        val videoId: String?,
        val voteCount: Int?
    )

    data class StreamingTitle(
        val name: String?,
        val titles: List<Title>
    ) {
        data class Title(
            val certificate: String?,
            val cover: String?,
            val rate: Float?,
            val releaseDay: String?,
            val releaseMonth: String?,
            val releaseYear: String?,
            val runtime: Int?,
            val title: String?,
            val titleId: String?,
            val videoId: String?,
            val voteCount: Int?
        )
    }

}