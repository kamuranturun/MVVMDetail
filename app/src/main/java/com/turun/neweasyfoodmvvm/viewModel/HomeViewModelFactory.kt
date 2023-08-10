package com.turun.neweasyfoodmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.turun.neweasyfoodmvvm.db.MealDatabase

//29 favorites save roomdb,
class HomeViewModelFactory(
    private val mealDatabase:MealDatabase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealDatabase) as T
    }
}
//29 favorites save roomdb, go to Homefragment
