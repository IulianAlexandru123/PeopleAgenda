package com.raiffeisen.peopleagenda.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal val gson: Gson =
    GsonBuilder().registerTypeAdapter(
        ZonedDateTime::class.java,
        object : JsonDeserializer<ZonedDateTime> {
            private val formatter = DateTimeFormatter.ISO_DATE_TIME

            override fun deserialize(
                json: JsonElement,
                typeOfT: Type,
                context: JsonDeserializationContext
            ): ZonedDateTime {
                return ZonedDateTime.parse(json.asString, formatter)
            }
        }
    ).create()