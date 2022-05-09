package com.sina.cinamovie.api

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.res.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("home/")
    suspend fun getHome(): Response<ApiResponse<HomeRes>>

    @GET("home/extra")
    suspend fun getHomeExtra(): Response<ApiResponse<HomeExtraRes>>

    @GET("chart/boxoffice")
    suspend fun getChartBoxOffice(): Response<ApiResponse<ChartBoxOfficeRes>>

    @GET("titles/{titleId}")
    suspend fun getTitleDetails(@Path("titleId") titleId: String): Response<ApiResponse<TitleDetailsRes>>

    @GET("genres/")
    suspend fun getGenres(): Response<ApiResponse<List<GenresRes>>>

    @GET("search/titles")
    suspend fun searchTitles(
        @Query("certificates") certificates: String? = "",
        @Query("colors") colors: String? = "",
        @Query("companies") companies: String? = "",
        @Query("countries") countries: String? = "",
        @Query("genres") genres: String? = "",
        @Query("groups") groups: String? = "",
        @Query("keywords") keywords: String? = "",
        @Query("languages") languages: String? = "",
        @Query("locations") locations: String? = "",
        @Query("plot") plot: String? = "",
        @Query("releaseDate") releaseDate: String? = "",
        @Query("role") role: String? = "",
        @Query("runtime") runtime: String? = "",
        @Query("sort") sort: String? = "",
        @Query("start") start: String? = "",
        @Query("title") title: String? = "",
        @Query("titleType") titleType: String? = "",
        @Query("userRating") userRating: String? = ""
    ): Response<ApiResponse<List<SearchTitlesRes>>>

    @GET("names/{nameId}")
    suspend fun getNameDetails(@Path("nameId") nameId: String): Response<ApiResponse<NameDetailRes>>

}