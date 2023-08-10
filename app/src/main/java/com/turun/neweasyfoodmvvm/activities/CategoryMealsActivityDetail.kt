package com.turun.neweasyfoodmvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.adapters.CategoryMealsAdapterDetail
import com.turun.neweasyfoodmvvm.databinding.ActivityCategoryMealsDetailBinding
import com.turun.neweasyfoodmvvm.fragments.HomeFragment
import com.turun.neweasyfoodmvvm.viewModel.CategoryMealsViewModel

//1 clickCategories
class CategoryMealsActivityDetail : AppCompatActivity() {
    //11 clickCategories
    lateinit var binding : ActivityCategoryMealsDetailBinding
    lateinit var categoryMealsViewModel :CategoryMealsViewModel

    //18 clickCategories
    lateinit var categoryMealsAdapter : CategoryMealsAdapterDetail
    //18 clickCategories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //16 clickCategories
        prepareRecyclerView()
        //16 clickCategories

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        //11 clickCategories go to CategoryMealsViewModel

        //14 clickCategories
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        //kategori ismine göre aldık
        //14 clickCategories

        //13 clickCategories
        categoryMealsViewModel.observeMealsLiveData().observe(this, Observer {
            mealsList->
            //20 clickCategories
            binding.tvCategoryCount.text = mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
            //20 clickCategories, bitti çalıştır ,
            //sonraki adımda room veritabanını kullanarak favorilere eklediğimiz
            //yemekleri veritabanında telefonumuzda kaydedeceğiz, şimdi
            //gradle ye gidelim ve room dependencyleri kütüphaneleri eklemeyle
            //başlayalım


        })
        //13 clickCategories, layouta git ve kategoriye göre listelenecek yemekler için
        //bir adet meals_item_category_detail adında resource file oluştur
    }
    //17 clickCategories
    private fun prepareRecyclerView() {
          //19 clickCategories
        categoryMealsAdapter = CategoryMealsAdapterDetail()
        binding.rvMealsCategoryCount.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
          //19 clickCategories
    }
    //17 clickCategories
}
//1 clickCategories, go to activity_category_meals_detail