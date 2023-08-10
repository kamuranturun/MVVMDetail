package com.turun.neweasyfoodmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turun.neweasyfoodmvvm.databinding.PopularItemsBinding
import com.turun.neweasyfoodmvvm.pojo.MealsByCategory

//2 popularitems
class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {

    //20 popularitems,
    lateinit var onItemClick:((MealsByCategory) -> Unit)
    //20 popularitems,

    //4 popularitems
    private var mealsList = ArrayList<MealsByCategory>()
    fun setMeals(mealList:ArrayList<MealsByCategory>){
        this.mealsList = mealList
        notifyDataSetChanged()
    }
    //4 popularitems

    //17 LongClickPopularItem,
     var onLongItemClick : ((MealsByCategory)->Unit)?= null
    //17 LongClickPopularItem,

    class PopularMealViewHolder(var binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root) {

    }
    //5 popularitems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),
        parent,false))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        //21 popularitems,
        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
        //21 popularitems, go to homefragment

        //18 LongClickPopularItem,
        holder.itemView.setOnLongClickListener {
            onLongItemClick?.invoke(mealsList[position])
            true
        }
        //18 LongClickPopularItem, go back to home fragment
    }
    //5 popularitems, go to retrofit MealApi

}
//2 popularitems, şimdi apiye gidip
//Filter by Category
//www.themealdb.com/api/json/v1/1/filter.php?c=Seafood kısmını alıp pojo içinde data class
// from json oluşturalım , burdaki yemekleri listeleyeceğiz