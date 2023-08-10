package com.turun.neweasyfoodmvvm.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.activities.CategoryMealsActivityDetail
import com.turun.neweasyfoodmvvm.activities.MainActivity

import com.turun.neweasyfoodmvvm.activities.MealActivityDetail
import com.turun.neweasyfoodmvvm.adapters.CategoriesAdapter
import com.turun.neweasyfoodmvvm.adapters.MostPopularAdapter
import com.turun.neweasyfoodmvvm.databinding.FragmentHomeBinding
import com.turun.neweasyfoodmvvm.fragments.bottomsheet.MealBottomSheetFragment
import com.turun.neweasyfoodmvvm.pojo.MealsByCategory
import com.turun.neweasyfoodmvvm.pojo.Meal
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel
import kotlinx.coroutines.Job


class HomeFragment : Fragment() {

    //6 randommeal
    private lateinit var binding:FragmentHomeBinding
    //6 randommeal

    //6 randommealviewModel
    //homemvvm to homeViewModel
    private lateinit var viewModel:HomeViewModel
    //6 randommealviewModel

    //7  activityMealDetail
    private lateinit var randomMeal :Meal
    //7  activityMealDetail

    //16 popularitems,
    private lateinit var popularItemsAdapter:MostPopularAdapter
    //16 popularitems,

    //18 categoryTasarım,
    private lateinit var categoriesAdapter : CategoriesAdapter
    //18 categoryTasarım,

    //6  activityMealDetail
    companion object{
        const val MEAL_ID ="com.turun.neweasyfoodmvvm.fragments.idMeal"
        const val MEAL_NAME ="com.turun.neweasyfoodmvvm.fragments.nameMeal"
        const val MEAL_THUMB ="com.turun.neweasyfoodmvvm.fragments.thumbMeal"
        //6 clickCategories
        const val CATEGORY_NAME = "com.turun.neweasyfoodmvvm.fragments.categoryName"
        //6 clickCategories
    }
    //6  activityMealDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //32 favorites save roomdb,
        viewModel = (activity as MainActivity).viewModel
        //32 favorites save roomdb, go to favoritesFragment, go to favoritesfragment

        //7 randommealviewModel, inişılize
        //30 favorites save roomdb
       // homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        //30 favorites save roomdb, açıklama satırı yaptık başka yerde kullancaz,
        //main aktivity de kullanacağız go to mainaktivity

        //7 randommealviewModel

        //17 popularitems, insialize
        popularItemsAdapter = MostPopularAdapter()
        //17 popularitems,

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 7 randommeal
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
        //7 randommeal
    }

    //5 randommeal
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //14 popularitems,
        preparePopularItemsRecyclerView()
        //14 popularitems,

        /**
         *   RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
        override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
        //başarı durumu
        if (response.body() !=null){

        val randomMeal : Meal = response.body()!!.meals[0]

        //8 randommeal, gelen veriyi glide ile alalım
        Glide.with(this@HomeFragment).load(randomMeal.strMealThumb)
        .into(binding.imgRandomMeal)


        /*
        Log.d("test","meal id alacağız : ${randomMeal.idMeal}" +
        " name : ${randomMeal.strMeal}")
        */

        }else return
        }

        override fun onFailure(call: Call<MealList?>, t: Throwable) {
        //hata durumu
        Log.d("home fragment",t.message.toString())
        }
        } )
         */

        //8 randommealviewModel
        // 2 configuration, alt satırı açıklama satırı yaptık
        //viewModel.getRandomMeal()
        // 2 configuration, amaç cihaz yan çevrildiğinde veya
        // başka fragmentlara gidilince tekrar api ye istek atmasın ve random meal değişmesin, başka bir yol daha var
        //go to homeviewmodel





       //8 randommealviewModel
        viewModel.getRandomMeal()
        observerRandomMeal()
        //8 randommealviewModel

        //3 activityMealDetail
        onRandomMealClick()
        //3 activityMealDetail

        //12 popularitems,
        viewModel.getPopularItems()
        observePopularItemsLiveData()
        //12 popularitems,

        //22 popularitems,
        onPopularItemClick()
        //22 popularitems,

        //16 categoryTasarım,
        prepareCategoriesRecyclerView()
        //16 categoryTasarım,

        // 9 categoryTasarım,
        viewModel.getCategories()
        observeCategoriesLiveData()
        // 9 categoryTasarım,


        //5 clickCategories
        onCategoryClick()
        //5 clickCategories

        //19 LongClickPopularItem
        onPopularItemLongClick()
        //19 LongClickPopularItem


        //20 SearchView,
        onSearchIconClick()
        //20 SearchView,




    }

    //21 SearchView,
    private fun onSearchIconClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
    //21 SearchView, arama işlemi bitti ama biz yazarken otomatik arasın şimdi o kısmı yapalım,
    //go to searchfragment

    //20 LongClickPopularItem
    private fun onPopularItemLongClick() {

        popularItemsAdapter.onLongItemClick = {meal->
            val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(meal.idMeal)
            mealBottomSheetFragment.show(childFragmentManager,"Meal Info")
        }
    }
    //20 LongClickPopularItem, runapp, go to bottomsheetfragment

    //5 clickCategories
    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {
            categoriy->
            val intent = Intent(activity, CategoryMealsActivityDetail::class.java)
            //7 clickCategories
            intent.putExtra(CATEGORY_NAME,categoriy.strCategory)

            startActivity(intent)
            //7 clickCategories, buraya kadar tamam detaya gidiyoruz ama
            //tıkladığımızda categoryden kaçar adet olduğunu göstermemiz için
            //tekrar retrofit isteği yapmamız lazım apiden
            //o api ise şudur , filter by category
            //yani kategoriye göre filitrele
            //apimiz:www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
            //şimdi Mealapi ye gidelim ve get isteği atalım

        }
    }
    //5 clickCategories

    //17 categoryTasarım,
    private fun prepareCategoriesRecyclerView() {
        //19 categoryTasarım,
        categoriesAdapter = CategoriesAdapter()
        //19 categoryTasarım,
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            //20 categoryTasarım,
            adapter = categoriesAdapter
            //20 categoryTasarım,

        }

    }
    //17 categoryTasarım,

    // 10 categoryTasarım,
    private fun observeCategoriesLiveData() {
          viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner,
          Observer { categories->
              //21 categoryTasarım,
              categoriesAdapter.setCategoryList(categories)
              //21 categoryTasarım, runapp, uygulamayı çalıştır
              //sonraki adımda categories itemlerine tıklandığında
              //her birinden kaç adet olduğunu gösteren aktiviteye gidip
              //orda göstereceğiz, CategoryMealsActivityDetail adında aktivite oluşturarak
              //başlayalım

          })
    }
    //10 categoryTasarım, go to adapter CategoriesAdapter oluşturalım

    //23 popularitems,
    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = {
            meal->
            val intent = Intent(activity, MealActivityDetail::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }
    //23 popularitems,bitti çalıştır,
    //sonraki adımda cateories kısmını tasarlayacağız
    //layout dosyasından bir adet category_item reosurce file oluştur

    //15 popularitems,
    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            //18 popularitems,
            adapter = popularItemsAdapter
            //18 popularitems,
        }
    }
    //15 popularitems,

    //13 popularitems,
    private fun observePopularItemsLiveData() {
        viewModel.observePopularItemsLiveData().observe(viewLifecycleOwner
        ) {mealList->
            //19 popularitems,
            popularItemsAdapter.setMeals(mealList = mealList as ArrayList<MealsByCategory>)
            //19 popularitems, bitti uygulamayı çalıştır
            //şimdi popularitem lara tıklanınca ne yapılacağına bakalım
            //mostpopularadaptera gidelim

        }
    }
    //13 popularitems,

    //4 activityMealDetail
    private fun onRandomMealClick() {
    binding.randomMealCard.setOnClickListener {
        val intent = Intent(activity,MealActivityDetail::class.java)
        //9  activityMealDetail
        intent.putExtra(MEAL_ID, randomMeal.idMeal)
        intent.putExtra(MEAL_NAME, randomMeal.strMeal)
        intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
        //9  activityMealDetail, and go to MealActivityDetail
        startActivity(intent)
    }
    }
    //4 activityMealDetail detaya gitme işi bitti
    //şimdi floatingActionbutton işlemini ayarlayalım

    //9 randommealviewModel
    private fun observerRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)

            //8  activityMealDetail
            this.randomMeal= meal // yukarıda tanımladığımız randommeal e meal i atadık
            //8  activityMealDetail

        }
    }
    //9 randommealviewModel, finish runapp, bu viewmodel adımında
    //rasgele yemek çekme işlemini viewmodel ile yaptık
    //şimdi activities paketine git ve MealActivityDetail diye bir aktivite oluştur



//5 randommeal, build.gradle a gidip viewbindingi ayarlayalım sonra yukarıdan(6 dan)  tekrar devam edelim




}