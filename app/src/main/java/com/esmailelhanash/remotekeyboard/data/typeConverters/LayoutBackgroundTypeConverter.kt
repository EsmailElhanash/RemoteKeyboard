package com.esmailelhanash.remotekeyboard.data.typeConverters
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.esmailelhanash.remotekeyboard.data.model.LayoutBackground
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LayoutBackgroundTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLayoutBackground(background: LayoutBackground?): String? {
        return gson.toJson(background)
    }

    @TypeConverter
    fun toLayoutBackground(data: String?): LayoutBackground? {
        if (data == null) return null
        val type = object : TypeToken<LayoutBackground>() {}.type
        return gson.fromJson(data, type)
    }

    // Assuming Color is stored as an ARGB integer
    @TypeConverter
    fun fromColor(color: Color?): Int? {
        return color?.toArgb()
    }

    @TypeConverter
    fun toColor(value: Int?): Color? {
        return value?.let { Color(it) }
    }
}