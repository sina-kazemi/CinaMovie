package com.sina.cinamovie.data.res

data class ChartBoxOfficeRes(
    val weekendDate: String,
    val boxOfficeTitles: List<BoxOfficeTitle>
) {
    data class BoxOfficeTitle(
        val titleId: String,
        val title: String,
        val cover: String,
        val weekend: String,
        val gross: String,
        val weeks: String,
    )

}