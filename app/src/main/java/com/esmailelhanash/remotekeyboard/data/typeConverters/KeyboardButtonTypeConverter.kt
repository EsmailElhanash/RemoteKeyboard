package com.esmailelhanash.remotekeyboard.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton

class KeyboardButtonTypeConverter {
    @TypeConverter
    fun fromKeyboardButtonList(value: List<KeyboardButton>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<KeyboardButton>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toKeyboardButtonList(value: String): List<KeyboardButton>? {
        val gson = Gson()
        val type = object : TypeToken<List<KeyboardButton>>() {}.type
        return gson.fromJson(value, type)
    }
}