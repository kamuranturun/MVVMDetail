package com.turun.neweasyfoodmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turun.neweasyfoodmvvm.databinding.CategoryItemBinding
import com.turun.neweasyfoodmvvm.pojo.Categoriy

//11 categoryTasarım
class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    //12 categoryTasarım
    private var categorysList = ArrayList<Categoriy>()

    //3 clickCategories,
    var onItemClick :((Categoriy)->Unit)? = null// şuan boş
    //3 clickCategories,


    fun setCategoryList(categoriesList : List<Categoriy> ){
        this.categorysList = categoriesList as ArrayList<Categoriy>
        notifyDataSetChanged()
    }
    //12 categoryTasarım

    inner class CategoriesViewHolder(val binding:CategoryItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        //13 categoryTasarım
        return CategoriesViewHolder(CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
        //13 categoryTasarım
    }

    override fun getItemCount(): Int {
        //14 categoryTasarım
      return categorysList.size

        //14 categoryTasarım
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        //15 categoryTasarım
        Glide.with(holder.itemView).load(categorysList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categorysList[position].strCategory
        //15 categoryTasarım, go to home fragment

        //4 clickCategories,
        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(categorysList[position])//invoke onItemClicki cağırır
        }
        //4 clickCategories, go to homefragment
    }

}
//11 categoryTasarım