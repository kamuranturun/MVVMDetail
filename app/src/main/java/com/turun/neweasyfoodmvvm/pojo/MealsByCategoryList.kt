package com.turun.neweasyfoodmvvm.pojo


import com.google.gson.annotations.SerializedName
//3 popularitems
data class MealsByCategoryList(
    @SerializedName("meals")
    val meals: List<MealsByCategory>
)
//3 popularitems, şimdi go to mostpopularadapter