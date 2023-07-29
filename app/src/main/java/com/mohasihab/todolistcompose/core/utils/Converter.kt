package com.mohasihab.todolistcompose.core.utils

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Date

class Converter {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromBigDecimal(value: BigDecimal):String{
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toBigDecimal(value:String):BigDecimal{
            return value.toBigDecimal()
        }

        @TypeConverter
        @JvmStatic
        fun toDate(value:Long): Date?{

            var resultDate : Date? = null
            resultDate=  if (value == null) null else Date(value)
            return resultDate
        }

        @TypeConverter
        @JvmStatic
        fun fromDate(date:Date): Long {
            return date.time
        }

    }
}