package com.turun.neweasyfoodmvvm.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters


//13 favorites save roomdb,
@TypeConverters
class MealTypeConvertor {

    @TypeConverter
    fun fromAnyToString(attribute:Any?):String{
        if (attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?) :Any{
        if (attribute ==null)
            return ""
        return attribute
    }
}
//13 favorites save roomdb, go to MealDatabase
