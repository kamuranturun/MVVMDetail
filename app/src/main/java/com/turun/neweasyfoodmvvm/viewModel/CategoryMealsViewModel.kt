package com.turun.neweasyfoodmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turun.neweasyfoodmvvm.pojo.MealsByCategory
import com.turun.neweasyfoodmvvm.pojo.MealsByCategoryList
import com.turun.neweasyfoodmvvm.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//7 clickCategories
class CategoryMealsViewModel :ViewModel() {

    //8 clickCategories
    val mealsLiveData = MutableLiveData<List<MealsByCategory>>()
    //8 clickCategories

    fun getMealsByCategory(categoryName:String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList?> {
            override fun onResponse(
                call: Call<MealsByCategoryList?>,
                response: Response<MealsByCategoryList?>
            ) {
                //9 clickCategories
                response.body()?.let {
                    mealsList->
                    mealsLiveData.postValue(mealsList.meals)

                }
                //9 clickCategories

            }

            override fun onFailure(call: Call<MealsByCategoryList?>, t: Throwable) {
                //10 clickCategories
                Log.e("categoryMealsViewModel",t.message.toString())
                //10 clickCategories, go to categoryMealsActivityDetail observe edelim
            }
        })
    }

    //12 clickCategories
    fun observeMealsLiveData(): LiveData<List<MealsByCategory>>{
        return mealsLiveData
    }
    //12 clickCategories, go to CategoryMealsActivityDetail

}
//7 clickCategories


//data class--->api--->viewmodel-->aktivity