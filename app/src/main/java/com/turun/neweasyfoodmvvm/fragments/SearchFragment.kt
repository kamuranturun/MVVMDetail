package com.turun.neweasyfoodmvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.activities.MainActivity
import com.turun.neweasyfoodmvvm.adapters.FavoritesMealsAdapter
import com.turun.neweasyfoodmvvm.databinding.FragmentSearchBinding
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//12 SearchView
class SearchFragment : Fragment() {
    private lateinit var binding :FragmentSearchBinding
    private lateinit var viewModel:HomeViewModel

    //13 SearchView, favoritesmealsAdapteri kullanacağız ismini hoca değiştirdi
    // mealsadapter yaptı ben değiştirmedim, onun meal_itemı işimizi görecek
    private lateinit var searchRecyclerViewAdapter : FavoritesMealsAdapter
    //13 SearchView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }


    //14 SearchView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        //16 SearchView
        binding.imgSearchArrow.setOnClickListener { searchMeals() }
        //16 SearchView

        //18 SearchView
        observeSearchedMealsLiveData()
        //18 SearchView

        //22 SearchView, otomatik arama
        var searchJob : Job? =null
        binding.edSearchBox.addTextChangedListener { searchQuery->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(100)
                viewModel.searchMeals(searchQuery.toString())
            }
        }
        //22 SearchView, otomatik arama, runapp, arama işlemi bitti
        //şimdi geri gelme esnasında uygulamanın kapanmasını önlemek için ve
        //geri tuşuyla geriye geldiğimizde home fragmenta gelmesi için
        //activity_main xml fragment kısmına app:defaultNavHost ="true" kodunu ekleyelim,
        //ayrıca üstteki status barın rengini değiştirelim go to themes xml,ayrıca şeffaflaştıralım ki
        //saat vs görünsün go to themes xml, şimdi homeviewmodele git
    }
    //19 SearchView
    private fun observeSearchedMealsLiveData() {
        viewModel.observeSearchedMealsLiveData().observe(viewLifecycleOwner, Observer {
            mealsList->searchRecyclerViewAdapter.differ.submitList(mealsList)
        })
    }
    //19 SearchView, nav_grapha git ve searchfragmenti ekle, sonra homefragmente geç

    //17 SearchView
    private fun searchMeals() {

        val searchQuery = binding.edSearchBox.text.toString()
        if (searchQuery.isNotEmpty()){
            viewModel.searchMeals(searchQuery)
        }

    }
    //17 SearchView

    //15 SearchView
    private fun prepareRecyclerView() {
        searchRecyclerViewAdapter = FavoritesMealsAdapter()
        binding.rvSearchedMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter =searchRecyclerViewAdapter
        }
     }
    //15 SearchView
    //14 SearchView


}
//12 SearchView