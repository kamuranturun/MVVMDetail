package com.turun.neweasyfoodmvvm.pojo


import com.google.gson.annotations.SerializedName
//1 categoryTasarım
data class Categoriy(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)
//1 categoryTasarım