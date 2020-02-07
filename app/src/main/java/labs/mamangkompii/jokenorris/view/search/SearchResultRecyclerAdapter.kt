package labs.mamangkompii.jokenorris.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import labs.mamangkompii.jokenorris.R

class JokesCategoriesRecyclerAdapter() : RecyclerView.Adapter<SearchResultViewHolder>() {

    private val searchResults = ArrayList<SearchResultVModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    fun setData(searchResults: List<SearchResultVModel>) {
        this.searchResults.clear()
        this.searchResults.addAll(searchResults)
        notifyDataSetChanged()
    }
}

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val jokesContentLabel: TextView = itemView.findViewById(R.id.jokesContentLabel)
    private val jokesCategoryLabel: TextView = itemView.findViewById(R.id.jokesCategoryLabel)

    fun bind(searchResultVModel: SearchResultVModel) {
        jokesContentLabel.text = searchResultVModel.joke

        val categoriesString = searchResultVModel.getReadableCategories()
        if (categoriesString.isNotBlank()) {
            jokesCategoryLabel.visibility = View.VISIBLE
            jokesCategoryLabel.text = categoriesString
        } else {
            jokesCategoryLabel.visibility = View.GONE
        }
    }
}
