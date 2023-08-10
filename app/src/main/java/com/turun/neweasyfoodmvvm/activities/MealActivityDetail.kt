package com.turun.neweasyfoodmvvm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.databinding.ActivityMealDetailBinding
import com.turun.neweasyfoodmvvm.db.MealDatabase
import com.turun.neweasyfoodmvvm.fragments.HomeFragment
import com.turun.neweasyfoodmvvm.pojo.Meal
import com.turun.neweasyfoodmvvm.viewModel.MealViewModelDetail
import com.turun.neweasyfoodmvvm.viewModel.MealViewModelFactory

//1 activityMealDetail
class MealActivityDetail : AppCompatActivity() {
    //14  activityMealDetail
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    //14  activityMealDetail

    //20  activityMealDetail
    private lateinit var mealViewModelDetail : MealViewModelDetail
    //20  activityMealDetail


    //10  activityMealDetail
    private lateinit var binding: ActivityMealDetailBinding
    //10  activityMealDetail

    //30  activityMealDetail
    private lateinit var youtubeLink:String
    //30  activityMealDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //11  activityMealDetail
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //11  activityMealDetail

        //20 favorites save roomdb,
        val mealDatabase = MealDatabase.getInstance(this)
        val viewmodelFactory = MealViewModelFactory(mealDatabase)

        //20 favorites save roomdb,

        //21  activityMealDetail
       // mealMvvmDetail = ViewModelProvider(this)[MealViewModelDetail::class.java]
        //21 favorites save roomdb, 21 activitymealdetaili değiştirdik birleştirdik fazla değişiklik
        // yok viewmodelFactory i ekledik
        mealViewModelDetail = ViewModelProvider(this,viewmodelFactory)[MealViewModelDetail::class.java]
        //21 favorites save roomdb,

        //21  activityMealDetail

        //12  activityMealDetail
        getRandomInformationFromIntent()
        //12  activityMealDetail

        //16  activityMealDetail
        setInformationInViews()
        //16  activityMealDetail

        //26  activityMealDetail
        loadingCase()
        //26  activityMealDetail

        //22  activityMealDetail
        mealViewModelDetail.getMealDetail(mealId)
        //22  activityMealDetail

        //23  activityMealDetail
        observerMealDetailsLiveData()
        //23  activityMealDetail

        //28  activityMealDetail
        //youtube düğmesine tıklayınca youtube gitsin
        onYoutubeClick()
        //28  activityMealDetail

        //22 favorites save roomdb,
        onFavoriteClick()
        //22 favorites save roomdb,


    }
    //22 favorites save roomdb,
    private fun onFavoriteClick() {
        binding.btnAddToFavorites.setOnClickListener {
            //25 favorites save roomdb,
            mealToSave?.let {
                mealViewModelDetail.insertMeal(it)
                Toast.makeText(this,"Meal Saved..",Toast.LENGTH_LONG).show()
                //yemek başarıyla kaydedildi
            }
            //25 favorites save roomdb, kayıt başarılı şimdi ise
            //kaydettiklerimizi favoritesfragment de gösterelim,
            //o zaman go to homeviewmodel
        }
    }
    //22 favorites save roomdb,

    //29  activityMealDetail
    private fun onYoutubeClick() {

        binding.imgYoutube.setOnClickListener {
            //32  activityMealDetail, youtuba git tıklanınca
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(youtubeLink))
            startActivity(intent)
            //32  activityMealDetail, ve bitti uygulamayı çalıştır,
            //şimdi ise popular yemekleri listelemek için
            //homefragment içindeki recyclerview için bir item tasarımı
            //yapacağız, yani tek bir kalıp tasarım yapacağız
            //tüm satırlarda o gözükecek,
            //şim layout klasörüne gidip popular_items isminde bir resource file oluşturalım

        }

    } //29  activityMealDetail

    //23 favorites save roomdb,
    private var mealToSave:Meal?=null
    //23 favorites save roomdb,

    //24 activityMealDetail
    private fun observerMealDetailsLiveData() {
       mealViewModelDetail.observeMealDetailsLiveData().observe(this,object : Observer<Meal?> {
           override fun onChanged(value: Meal?) {
               //27 activityMealDetail
               onResponseCase()
               //27  activityMealDetail
               val meal = value

               //24 favorites save roomdb,
                mealToSave = meal
               //24 favorites save roomdb,


               binding.tvCategory.text = "Category : ${meal!!.strCategory}"
               binding.tvArea.text = "Area : ${meal!!.strArea}"
               binding.tvInstructionSteps.text = meal.strInstructions

               //31  activityMealDetail
               youtubeLink= meal.strYoutube.toString()
               //31  activityMealDetail

           }
       })
    }
    //24  activityMealDetail

    //17  activityMealDetail
    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title=mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }
    //17  activityMealDetail, resmi çekme işlemi tamam ,
    //şimdi mealApi ye gidip id ye göre yemeği çekip detay aktivitiyde gösterelim

    //13  activityMealDetail
    private fun getRandomInformationFromIntent() {
        val intent =intent
        //15  activityMealDetail
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
        //15  activityMealDetail

    }    //13  activityMealDetail

    //25  activityMealDetail
    private fun loadingCase(){
        //görünmez yüklenirken
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFavorites.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }
    //25  activityMealDetail

    //25  activityMealDetail
    private fun onResponseCase(){
        //yüklendiğinde görünür
        binding.btnAddToFavorites.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
    //25  activityMealDetail
}
//1 activityMealDetail, goto activity_meal_detail