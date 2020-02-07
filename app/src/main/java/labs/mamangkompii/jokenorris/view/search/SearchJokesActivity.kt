package labs.mamangkompii.jokenorris.view.search

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_jokes.*
import kotlinx.android.synthetic.main.search_top_bar_layout.*
import labs.mamangkompii.jokenorris.R
import labs.mamangkompii.jokenorris.presenter.search.SearchPresenter
import labs.mamangkompii.jokenorris.presenter.search.SearchPresenterFactory

class SearchJokesActivity : AppCompatActivity(), SearchResultView {

    private lateinit var searchPresenter: SearchPresenter
    private val jokesCategoriesRecyclerAdapter = JokesCategoriesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_jokes)

        setupPresenter()
        setupListeners()
        setupResultList()
    }

    private fun setupPresenter() {
        searchPresenter = SearchPresenterFactory().createPresenter(this)
    }

    private fun setupListeners() {
        backBtn.setOnClickListener {
            finish()
        }

        searchET.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchJokesByKeyword(v.text.toString())
                closeKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun searchJokesByKeyword(keyword: String) {
        searchPresenter.searchJokesByKeyword(keyword)
    }

    private fun closeKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchET.windowToken, 0)
    }

    private fun setupResultList() {
        searchResultRV.layoutManager = LinearLayoutManager(this)
        searchResultRV.adapter = jokesCategoriesRecyclerAdapter
    }

    override fun showSearchResult(searchResult: List<SearchResultVModel>) {
        jokesCategoriesRecyclerAdapter.setData(searchResult)
    }

    override fun showErrorSearchToast() {
        Toast
            .makeText(this, "Can't get search results, please try again", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onStop() {
        super.onStop()

        searchPresenter.onStop()
    }
}
