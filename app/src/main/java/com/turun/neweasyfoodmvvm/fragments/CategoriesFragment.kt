package com.turun.neweasyfoodmvvm.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.turun.neweasyfoodmvvm.R
import com.turun.neweasyfoodmvvm.activities.CategoryMealsActivityDetail
import com.turun.neweasyfoodmvvm.activities.MainActivity
import com.turun.neweasyfoodmvvm.adapters.CategoriesAdapter
import com.turun.neweasyfoodmvvm.databinding.FragmentCategoriesBinding
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel


class CategoriesFragment : Fragment() {
   // 2 katogoriFragment
    private lateinit var binding :FragmentCategoriesBinding
    //2 katogoriFragment

    //6 katogoriFragment
    private lateinit var categoriesAdapter : CategoriesAdapter
    //6 katogoriFragment

    //8 katogoriFragment
    private lateinit var viewModel :HomeViewModel
    //8 katogoriFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //9 katogoriFragment
        viewModel = (activity as MainActivity).viewModel
        //9 katogoriFragment

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //3 katogoriFragment
       binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
        //3 katogoriFragment

    }
    //4 katogoriFragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()

        //10 katogoriFragment
        observeCategories()
        //10 katogoriFragment

        //12 kateogriFragment
        onCategoryClick()
        //12 kateogriFragment
    }
    //13 kateogriFragment
    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {
                categoriy->
            val intent = Intent(activity, CategoryMealsActivityDetail::class.java)
            //7 clickCategories
            intent.putExtra(HomeFragment.CATEGORY_NAME,categoriy.strCategory)
            startActivity(intent)


        }
    }
    //13 kateogriFragment,runapp,sonraki adımda popularIteme uzun tıklanınca
    //altta bottomsheet açılacak , hadi onu yapalım,şimdi fragments paketin içine bir adet
    //bottomsheet paket oluştur , onun içine de bir tane fragment oluştur ismi MealBottomSheetFragment olsun

    //11 katogoriFragment
    private fun observeCategories() {
viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer {
    categories->
    categoriesAdapter.setCategoryList(categories)
    //runapp çalıştır , devam
})
    }
    //11 katogoriFragment

    //5 katogoriFragment
    private fun prepareRecyclerView() {

        //7 katogoriFragment
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
        //7 katogoriFragment

    }
    //5 katogoriFragment

//4 katogoriFragment


}