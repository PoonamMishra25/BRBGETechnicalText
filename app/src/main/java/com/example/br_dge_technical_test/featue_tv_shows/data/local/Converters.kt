package com.example.br_dge_technical_test.featue_tv_shows.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.data.local.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromTVShowsListToJson(json: String): List<TVShowsResponseItem> {
        return jsonParser.fromJson<ArrayList<TVShowsResponseItem>>(
            json,
            object : TypeToken<ArrayList<TVShowsResponseItem>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toTVShowsListJson(meaning: List<TVShowsResponseItem>): String {
        return jsonParser.toJson(meaning, object : TypeToken<ArrayList<TVShowsResponseItem>>() {}.type) ?: "[]"
    }
}