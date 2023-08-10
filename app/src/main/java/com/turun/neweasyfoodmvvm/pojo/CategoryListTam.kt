package com.turun.neweasyfoodmvvm.pojo


import com.google.gson.annotations.SerializedName
//2 categoryTasarım
data class CategoryListTam(
    @SerializedName("categories")
    val categories: List<Categoriy>
)
//2 categoryTasarım, şimdi Mealapiye gidip retrofit isteği yapalım



//data class--->Api--->ViewModel-->Fragment