package com.example.br_dge_technical_test.featue_tv_shows.data.local.util

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJson(json:String,type:Type):T?
    fun <T> toJson(obj:T,type:Type):String?

}