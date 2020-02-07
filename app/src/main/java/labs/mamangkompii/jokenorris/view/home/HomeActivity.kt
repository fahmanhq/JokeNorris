package labs.mamangkompii.jokenorris.view.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import labs.mamangkompii.jokenorris.R
import labs.mamangkompii.jokenorris.presenter.home.HomePresenter
import labs.mamangkompii.jokenorris.presenter.home.HomePresenterFactory
import labs.mamangkompii.jokenorris.view.details.SingleJokeShowcaseActivity
import labs.mamangkompii.jokenorris.view.search.SearchJokesActivity

class HomeActivity : AppCompatActivity(), JokesCategoriesView, CategoryClickListener {

    private lateinit var jokesCategoriesRecyclerAdapter: JokesCategoriesRecyclerAdapter
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupPresenter()

        setupCategoryList()

        setupListeners()
    }

    private fun setupPresenter() {
        homePresenter = HomePresenterFactory().createPresenter(this)
    }

    private fun setupCategoryList() {
        jokesCategoriesRecyclerAdapter = JokesCategoriesRecyclerAdapter(this)
        categoryListRV.layoutManager = GridLayoutManager(this, 2)
        categoryListRV.adapter = jokesCategoriesRecyclerAdapter
    }

    private fun setupListeners() {
        searchButton.setOnClickListener {
            startActivity(Intent(this, SearchJokesActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        homePresenter.fetchJokesCategories()
    }

    override fun onStop() {
        super.onStop()

        homePresenter.onStop()
    }

    override fun showJokesCategories(categories: List<String>) {
        jokesCategoriesRecyclerAdapter.setData(categories)
    }

    override fun showErrorToast() {
        Toast
            .makeText(this, "Can't get jokes category list, please try again", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPickCategory(category: String) {
        val intent = Intent(this, SingleJokeShowcaseActivity::class.java)
        intent.putExtra(SingleJokeShowcaseActivity.EXTRA_SELECTED_CATEGORY, category)
        startActivity(intent)
    }
}
