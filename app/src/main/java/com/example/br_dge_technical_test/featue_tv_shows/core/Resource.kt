package com.example.br_dge_technical_test.featue_tv_shows.core

sealed class Resource <T>(val data:T?=null,val msg:String?=null) {
    class Loading<T>(data: T?=null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(msg: String?,data: T?=null): Resource<T>(data=data,msg=msg)
}
