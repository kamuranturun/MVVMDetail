package com.turun.neweasyfoodmvvm.fragments.bottomsheet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.activities.MainActivity
import com.turun.neweasyfoodmvvm.activities.MealActivityDetail
import com.turun.neweasyfoodmvvm.databinding.FragmentMealBottomSheetBinding
import com.turun.neweasyfoodmvvm.fragments.HomeFragment
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel

//2 LongClickPopularItem
private const val MEAL_ID = "param1"
//2 LongClickPopularItem, fragmentmealbottomsheet xml




class MealBottomSheetFragment : BottomSheetDialogFragment() {

    //1 LongClickPopularItem
    private var mealId: String? = null
    //1 LongClickPopularItem

    //4 LongClickPopularItem
    private lateinit var binding: FragmentMealBottomSheetBinding
    //4 LongClickPopularItem


    //7 LongClickPopularItem
    private lateinit var viewModel : HomeViewModel
    //7 LongClickPopularItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }
        //8 LongClickPopularItem
        viewModel = (activity as MainActivity).viewModel
        //8 LongClickPopularItem, go to homeviewmodel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //5 LongClickPopularItem
        binding = FragmentMealBottomSheetBinding.inflate(inflater)
        return binding.root
        //5 LongClickPopularItem
    }
    //6 LongClickPopularItem
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewmodeli çağır yukarıdan

        //13 LongClickPopularItem
        mealId?.let { viewModel.getMealById(it) }
        //13 LongClickPopularItem, go to homeviewmodel

    //15 LongClickPopularItem
        observeBottomSheetMeal()
    //15 LongClickPopularItem


        //21 LongClickPopularItem,
        onBottomsheetDialogClick()
        //21 LongClickPopularItem,


    }
    //22 LongClickPopularItem,
    private fun onBottomsheetDialogClick() {
         binding.bottomSheet.setOnClickListener {

             //25 LongClickPopularItem,
             if (mealName !=null && mealThumb !=null){
                 val intent = Intent(activity,MealActivityDetail::class.java)

                 intent.apply {
                     putExtra(HomeFragment.MEAL_ID, mealId)
                     putExtra(HomeFragment.MEAL_NAME, mealName)
                     putExtra(HomeFragment.MEAL_THUMB, mealThumb)
                 }
                 startActivity(intent)

             }
             //25 LongClickPopularItem, runapp, şimdi bottomsheetin sağ üst köşesini
             //biraz kavisli yapacağız, values kalsörüne git ve styles diye xml oluştur
         }
    }
    //22 LongClickPopularItem,

    //23 LongClickPopularItem,
    private var mealName: String? = null
    private var mealThumb: String? =null
    //23 LongClickPopularItem,

    //16 LongClickPopularItem
    private fun observeBottomSheetMeal() {
     viewModel.observeBottomSheetmeal().observe(viewLifecycleOwner, Observer {
         meal->Glide.with(this).load(meal.strMealThumb).into(binding.imgBottomSheet)
         binding.tvBottomSheetArea.text = meal.strArea
         binding.tvBottomSheetCategory.text =meal.strCategory
         binding.tvBottomSheetMealName.text = meal.strMeal

         //24 LongClickPopularItem,
         mealName = meal.strMeal
         mealThumb = meal.strMealThumb
         //24 LongClickPopularItem,
     })
    }
    //16 LongClickPopularItem, go to mostpopularadapter

    //6 LongClickPopularItem

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, ) =
            MealBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(MEAL_ID, param1)

                }
            }
    }
}