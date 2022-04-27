package com.sina.cinamovie.data

data class Result<out T>(val status: Status , val data: T? , val error: Error? , val message: String?) {

    enum class Status{
        LOADING , SUCCESS , ERROR
    }

    companion object{
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS , data , null , null)
        fun <T> error(message: String , error: Error): Result<T> = Result(Status.ERROR , null , error , message)
        fun <T> loading(data: T?): Result<T> = Result(Status.LOADING , data , null ,null)
    }

}
