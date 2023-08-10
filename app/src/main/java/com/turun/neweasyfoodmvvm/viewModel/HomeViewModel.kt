package com.turun.neweasyfoodmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turun.neweasyfoodmvvm.db.MealDatabase
import com.turun.neweasyfoodmvvm.pojo.*
import com.turun.neweasyfoodmvvm.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//2 randommealviewModel
class HomeViewModel
//26 favorites save roomdb
    (private val mealDatabase: MealDatabase)
//26 favorites save roomdb

    :ViewModel() {

    //3 randommealviewModel
    private var randomMealLiveData = MutableLiveData<Meal>()
    //3 randommealviewModel

    //8 popularitems
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    //8 popularitems

    // 5 categoryTasarım,
    private var categoriesLiveData = MutableLiveData<List<Categoriy>>()
    // 5 categoryTasarım,

    //27 favorites save roomdb
    private var favoritesMealsLiveData = mealDatabase.mealDao().getAllMeals()
    //27 favorites save roomdb

    //10 LongClickPopularItem
    private var bottomSheetMealLiveData = MutableLiveData<Meal>()
    //10 LongClickPopularItem

    //2 SearchView,
    private val searchMealsLiveData = MutableLiveData<List<Meal>>()
    //2 SearchView,


    //1 configuration, init hemen çalışır ve ekran döndürüldüğünde ekrandaki yemek değişmez
    //init {
      //  getRandomMeal()
    //}
    //1 configuration, go to homefragment, ancak başka bir yol daha var, burayı da açıklama satırı yapalım

    //1 configuration2
    private var saveStateRandomMeal :Meal? =null
    //1 configuration2

    fun getRandomMeal(){
        //2 configuration2
        saveStateRandomMeal?.let {
            randomMeal->randomMealLiveData.postValue(randomMeal)
            return
        }
        //2 configuration2


        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                //başarı durumu
                if (response.body() !=null){

                    val randomMeal : Meal = response.body()!!.meals[0]
                    //4 randommealviewModel
                    randomMealLiveData.value = randomMeal
                    //4 randommealviewModel

                    //3 configuration2
                    saveStateRandomMeal = randomMeal
                    //3 configuration2


                }else return
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                //hata durumu
                Log.d("home fragment",t.message.toString())
            }
        } )
    }

    //7 popularitems
    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList?> {
            override fun onResponse(call: Call<MealsByCategoryList?>, response: Response<MealsByCategoryList?>) {
                //9 popularitems
                if(response.body() !=null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
                //9 popularitems
            }

            override fun onFailure(call: Call<MealsByCategoryList?>, t: Throwable) {
                //10 popularitems
                Log.d("homefragmentpopularitem",t.message.toString())
                //10 popularitems
            }
        })
    }
    //7 popularitems


    // 4 categoryTasarım,
    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryListTam?> {
            override fun onResponse(
                call: Call<CategoryListTam?>,
                response: Response<CategoryListTam?>
            ) {
                // 6 categoryTasarım,
                response.body()?.let {
                    categoryList->
                    categoriesLiveData.postValue(categoryList.categories)
                }
                // 6 categoryTasarım,

            }

            override fun onFailure(call: Call<CategoryListTam?>, t: Throwable) {
                // 7 categoryTasarım,
                Log.e("homeviewmodelcategorytasarım",t.message.toString())
                // 7 categoryTasarım,
            }
        })
    }
    // 4 categoryTasarım,


    //9 LongClickPopularItem
    fun getMealById(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                val meal = response.body()?.meals?.first()
                //yukarıdan livedata tanımla

                //11 LongClickPopularItem
                meal.let {meal->
                    bottomSheetMealLiveData.postValue(meal)
                }
                //11 LongClickPopularItem
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                //12 LongClickPopularItem
                 Log.e("homeviewmodel",t.message.toString())
                //12 LongClickPopularItem, go ot bottomsheetfragment
            }
        })
    }
    //9 LongClickPopularItem


    //45 favorites save roomdb,
    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }
    //45 favorites save roomdb,go back favortesfragment

    //47 favorites save roomdb,
    fun insertMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }
    //47 favorites save roomdb, go to favorites fragment

    //3 SearchView,
    fun searchMeals(searchQuery:String) = RetrofitInstance.api.searchMeals(searchQuery).enqueue(
        object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                val mealList = response.body()?.meals
                mealList?.let {
                    searchMealsLiveData.postValue(it)
                }
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
             Log.e("homeviewmodel",t.message.toString())
            }
        }
    )
    //3 SearchView,

    //4 SearchView,
    fun observeSearchedMealsLiveData():LiveData<List<Meal>> = searchMealsLiveData
    //4 SearchView,go to fragments şimdi fragment oluştur SearchFragment
    //ve go to search fragment xml

    //11 popularitems
    fun observePopularItemsLiveData():LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }
    //11 popularitems, go to homefragment


    //5 randommealviewModel, UI da veri değişimi ve gözlemleme
    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
    //5 randommealviewModel, go to homefragment

    // 8 categoryTasarım,
    fun observeCategoriesLiveData():LiveData<List<Categoriy>>{
        return categoriesLiveData
    }
    // 8 categoryTasarım, go to homefragment


    //28 favorites save roomdb
    fun observeFavoritesMealsLiveData():LiveData<List<Meal>>{
        return favoritesMealsLiveData
    }
    //28 favorites save roomdb,go to viewmodel and create HomeViewModelFactory class

    //14 LongClickPopularItem
    fun observeBottomSheetmeal():LiveData<Meal> = bottomSheetMealLiveData
    //14 LongClickPopularItem, go to mealbottomsheetfragment
}
//2 randommealviewModel