package com.turun.neweasyfoodmvvm.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.turun.neweasyfoodmvvm.activities.MainActivity
import com.turun.neweasyfoodmvvm.activities.MealActivityDetail
import com.turun.neweasyfoodmvvm.adapters.FavoritesMealsAdapter
import com.turun.neweasyfoodmvvm.databinding.FragmentFavoritesBinding
import com.turun.neweasyfoodmvvm.viewModel.HomeViewModel


class FavoritesFragment : Fragment() {
private lateinit var binding:FragmentFavoritesBinding

    //33 favorites save roomdb,
    private lateinit var viewModel : HomeViewModel
    //33 favorites save roomdb,


    //41 favorites save roomdb,
    private lateinit var favoritesAdapter :FavoritesMealsAdapter
    //41 favorites save roomdb,

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //34 favorites save roomdb,
        viewModel =(activity as MainActivity).viewModel
        //34 favorites save roomdb,

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //39 favorites save roomdb,
        prepareRecyclerView()
        //39 favorites save roomdb,

        //35 favorites save roomdb,
        observeFavorites()
        //35 favorites save roomdb,

        //44 favorites save roomdb,
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position= viewHolder.adapterPosition
                //46 favorites save roomdb,
                viewModel.deleteMeal(favoritesAdapter.differ.currentList[position])
                //46 favorites save roomdb, go to homeviewmodel

                Snackbar.make(requireView(),"Meal deleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        //48 favorites save roomdb,
                        viewModel.insertMeal(favoritesAdapter.differ.currentList[position])

                        //48 favorites save roomdb,
                    }
                ).show()
            }
        }
        //49 favorites save roomdb,
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorites)
        //49 favorites save roomdb, runapp bu kısım bitti, sonraki kısımda katogoriFragment tasarımını yapacağız,
        //go to cateories_fragment xml

       //44 favorites save roomdb, go to homeviewmodel


    }
    //40 favorites save roomdb,
    private fun prepareRecyclerView() {
//42 favorites save roomdb,
        favoritesAdapter = FavoritesMealsAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter= favoritesAdapter
        }
//42 favorites save roomdb,
    }
    //40 favorites save roomdb,

    //36 favorites save roomdb,
    private fun observeFavorites() {
        viewModel.observeFavoritesMealsLiveData().observe(requireActivity(), Observer {
            meals->
            //43 favorites save roomdb,
            favoritesAdapter.differ.submitList(meals)
            //43 favorites save roomdb, çalıştır
            //favoriye ekleme işlemi başarıyla tamamlanmıştır,
            //sonraki adımda favorilere eklediğimizi silme işlemi
            //yapacağız,


        })
    }
    //36 favorites save roomdb, go to fragment_favorites xml


}