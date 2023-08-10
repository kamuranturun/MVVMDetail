package com.turun.neweasyfoodmvvm.retrofit

import com.turun.neweasyfoodmvvm.pojo.CategoryListTam
import com.turun.neweasyfoodmvvm.pojo.MealsByCategoryList
import com.turun.neweasyfoodmvvm.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//3 randommeal
interface MealApi {


    @GET("random.php")
    fun getRandomMeal():Call<MealList>
    //MealList kapsayan listedir

    //18  activityMealDetail
    @GET("lookup.php")
    fun getMealDetails(@Query("i") id:String):Call<MealList>
    //18  activityMealDetail, viewmodele git ve MealViewModelDetail adında class oluştur

    //6 popularitems,
    @GET("filter.php")
    fun getPopularItems(@Query("c") categoryName:String):Call<MealsByCategoryList>
    //6 popularitems, go to homeviewmodel

    //3 categoryTasarım
    @GET("categories.php")
    fun getCategories():Call<CategoryListTam>
    // 3 categoryTasarım, go to homeviewmodel

    //6 clickCategories
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName:String) : Call<MealsByCategoryList>
    //6 clickCategories, go to viewmodel, farklı bir aktivite olduğundan
    //yeni bir viewmodel tanımlayacağız CategoryMealsViewModel isminde

    //1 SearchView
    //Search meal by name api si
    //www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
    @GET("search.php")
    fun searchMeals(@Query("s") searchQuery:String):Call<MealList>
    //1 SearchView, go to homeviewmodel

}
//3 randommeal, şimdi retrofit paketine gidip bir adet object olarak
//retrofit örneği oluşturalım ismi RetrofitInstance