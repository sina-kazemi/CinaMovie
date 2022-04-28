package com.sina.cinamovie.data

data class ApiResponse<T>(
    val data: T,
    val success: Boolean,
    val error: String
)