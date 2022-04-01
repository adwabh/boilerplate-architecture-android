package com.example.myapplication.data.models

data class Either<T, E>(var data:T? = null, var error:E? = null) {
    fun isError() : Boolean  = error!=null
}
