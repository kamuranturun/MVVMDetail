package com.turun.neweasyfoodmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.turun.neweasyfoodmvvm.pojo.Meal

//10 favorites save roomdb, add abrtract because we have interface
//11 favorites save roomdb,
@Database(entities = [Meal::class], version = 1)
//14 favorites save roomdb
@TypeConverters(MealTypeConvertor::class)
//14 favorites save roomdb , ve uygulamayı çalıştır
//sonraki adımda favorite fab butonuna tıkladığımızda
//favorites fragmente tıkladığımızı ekleyecek,silme işlemi de yapacağız
//şimdi buildgradle gidelim ve coroutine için viewmodelscope
//kütüphanesini ekleyelim

//11 favorites save roomdb,
abstract class MealDatabase: RoomDatabase() {
    //12 favorites save roomdb,
    abstract fun mealDao():MealDao

    companion object{
        @Volatile
        var INSTANCE : MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : MealDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }
    //12 favorites save roomdb, go to db and create MealTypeConvertor class
}
//10 favorites save roomdb,