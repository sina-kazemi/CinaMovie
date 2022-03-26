package com.sina.cinamovie.model

data class MovieModel(
    var cover: String? = "" ,
    var rate: Float? = null,
    var subTitle: String? = null,
    var summary: String = "",
    var year: String = "",
    var voteCount: Int = 0,
    var runtime: Int = 0,
    var title: String = "",
    var releaseYear: String? = null ,
    var releaseMonth: String? = null ,
    var releaseDay: String? = null ,
    var certificate: String = "",
    var titleId: String = "",
    var videoId: String? = "",
    var videoName: String? = "",
    var videoPreview: String? = "",
    var videoRuntime: String? = "",
    var budget: String = "",
    var grossUsAndCanada: String = "",
    var grossWorldwide: String = "",
    var openingWeekendUsAndCanada: String = ""
)
