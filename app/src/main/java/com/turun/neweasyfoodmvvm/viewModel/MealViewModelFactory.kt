package com.turun.neweasyfoodmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.turun.neweasyfoodmvvm.db.MealDatabase

//19 favorites save roomdb,
class MealViewModelFactory(
    private val mealDatabase:MealDatabase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModelDetail(mealDatabase) as T
    }
}
//19 favorites save roomdb, go to MealActivityDetail
