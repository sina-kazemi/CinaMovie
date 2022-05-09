package com.sina.cinamovie.data.res

data class NameDetailRes(
    var id: String = "",
    var name: String = "",
    var avatar: String = "",
    var bioSummary: String = "",
    var birthDate: String = "",
    var birthDateMonthDay: String = "",
    var birthDateYear: String = "",
    var birthPlace: String = "",
    var filmographies: List<Filmography> = listOf(),
    var jobTitles: List<String> = listOf(),
    var trailer: List<Trailer> = listOf(),
    var photos: List<Photo> = listOf(),
    var knownForList: List<KnownFor> = listOf(),
    var relatedVideos: List<RelatedVideo> = listOf(),
    var personalDetails: List<PersonalDetails> = listOf()
) {

    data class Filmography(
        var headTitle: String = "",
        var numberOfCredits: String = "",
        var credits: List<Credits> = listOf()
    ) {

        data class Credits(
            var items: List<Items> = listOf()
        ) {

            data class Items(
                var id: String = "",
                var year: String = "",
                var subtitle: String = "",
                var title: String = ""
            )

        }

    }

    data class Trailer(
        var cover: String = "",
        var videoId: String = "",
        var caption: String = ""
    )

    data class Photo(
        var id: String = "",
        var url: String = ""
    )

    data class KnownFor(
        var cover: String = "",
        var title: String = "",
        var titleId: String = "",
        var inMovieName: String = "",
        var year: String = ""
    )

    data class RelatedVideo(
        var videoId: String = "",
        var title: String = "",
        var cover: String = ""
    )

    data class PersonalDetails(
        var title: String = "",
        var subtitle: String = "",
        var linkTexts: List<LinkText> = listOf()
    ) {

        data class LinkText(
            var text: String = "",
            var url: String = "",
        )

    }

}
