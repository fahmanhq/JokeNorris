package labs.mamangkompii.jokenorris.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import labs.mamangkompii.jokenorris.R

class JokesCategoriesRecyclerAdapter(
    private val clickListener: CategoryClickListener? = null
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    private val categoryList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryList[position])

        clickListener?.let {
            holder.itemView.setOnClickListener {
                clickListener.onPickCategory(categoryList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<String>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }
}

class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(jokesCategory: String) {
        (itemView as TextView).text = jokesCategory.toUpperCase()
    }
}

interface CategoryClickListener {
    fun onPickCategory(category: String)
}