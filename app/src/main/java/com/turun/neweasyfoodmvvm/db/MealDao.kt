package com.turun.neweasyfoodmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.turun.neweasyfoodmvvm.pojo.Meal

//5 favorites save roomdb,
//6 favorites save roomdb,
@Dao
//6 favorites save roomdb,
interface MealDao {

    //7 favorites save roomdb,
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal:Meal)
    //7 favorites save roomdb,onConflict:çakışma sırasında yapılacakları tanımlar


    //8 favorites save roomdb,
    @Delete
    suspend fun delete(meal:Meal)
    //8 favorites save roomdb,

    //9 favorites save roomdb,
    @Query("SELECT * FROM mealInformation")
    fun getAllMeals():LiveData<List<Meal>>
    //9 favorites save roomdb, go to db and oluştur MealDatabase class

}
//5 favorites save roomdb,