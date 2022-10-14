package com.app.koltinpoc.db

import androidx.room.TypeConverter
import com.app.koltinpoc.model.Source

class Converter {
    @TypeConverter
    fun fromSource(source: Source) :String? {
        return source.name
    }
    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}
