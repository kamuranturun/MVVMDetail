package com.turun.neweasyfoodmvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.db.MealDatabase
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    //31 favorites save roomdb,
  val viewModel:HomeViewModel by lazy {
      val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this,homeViewModelProviderFactory)[HomeViewModel::class.java]
    }
    //31 favorites save roomdb, go to homefragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //11 navigation, fragmentlar arası gezinmek için
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = Navigation.findNavController(this, R.id.host_fragment)
         NavigationUI.setupWithNavController(bottomNavigation,navController)
        //11 navigation bitti

    }
}
/**
 * kullanıdığım teknolojiler:
 * navigation component,retrofit,room,MVVM,liveData,coroutines,viewBinding,Glide
 * internet izni almayla başlayalım uygulamamıza
 * 1 navigation işlemleri
 * 2 tasarım, tasarım için build gradle gidelim kütüphane eklemek için
 * 3 random yemek almak için build gradle retrofit kütüphanesini eklemeyle başlayalım
 * 4 viewmodel kullanmaya başlayalım
 * 5 detay aktivity işlemleri
 * 6 popular meal listeleme
 */