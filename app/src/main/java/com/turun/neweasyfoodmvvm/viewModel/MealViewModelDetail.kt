package com.turun.neweasyfoodmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turun.neweasyfoodmvvm.db.MealDatabase
import com.turun.neweasyfoodmvvm.pojo.Meal
import com.turun.neweasyfoodmvvm.pojo.MealList
import com.turun.neweasyfoodmvvm.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//19  activityMealDetail
class MealViewModelDetail(
    //16 favorites save roomdb
  val  mealDatabase : MealDatabase
    //16 favorites save roomdb
):ViewModel() {

    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                if (response.body() !=null){
                    mealDetailsLiveData.value =response.body()!!.meals[0]
                } else return
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("MealDetailActivity",t.message.toString())
            }
        })
    }

    fun observeMealDetailsLiveData():LiveData<Meal>{
        return mealDetailsLiveData
    }

    //17 favorites save roomdb
    fun insertMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }
    //17 favorites save roomdb


     //18 favorites save roomdb
    //bu adımı oku geç önemli bir şey yok
    //18 favorites save roomdb, go to viewmodel package and create new class
    //name is MealViewModelFactory

}
//19  activityMealDetail, and go to mealActivityDetail