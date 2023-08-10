package com.turun.neweasyfoodmvvm.pojo


import com.google.gson.annotations.SerializedName
//2 randommeal
data class MealList(
    @SerializedName("meals")
    val meals: List<Meal>
    //meals=yemekler
    //meals içinde liste olarak tüm random gelebilecek yemekler var
)
//2 randommeal
////sonra retrofit paketine gidip new diyerek yeni bir interface oluştururuz ismi MealApi