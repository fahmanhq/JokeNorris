package labs.mamangkompii.jokenorris.view.details

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_single_joke_showcase.*
import labs.mamangkompii.jokenorris.R
import labs.mamangkompii.jokenorris.presenter.details.JokeDetailsPresenter
import labs.mamangkompii.jokenorris.presenter.details.JokeDetailsPresenterFactory
import labs.mamangkompii.jokenorris.view.widget.QuotePatternDrawable

class SingleJokeShowcaseActivity : AppCompatActivity(), SingleJokeShowcaseView {

    companion object {
        const val EXTRA_SELECTED_CATEGORY = "selectedCategory"
    }

    private lateinit var jokeDetailsPresenter: JokeDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_joke_showcase)

        setupPresenter()
        setupListeners()
        setupUI()

        jokeDetailsPresenter.getRandomJoke()
    }

    private fun setupPresenter() {
        val selectedCategory =
            intent.extras?.getString(EXTRA_SELECTED_CATEGORY, "") ?: ""
        jokeDetailsPresenter = JokeDetailsPresenterFactory().createPresenter(this, selectedCategory)
    }

    private fun setupListeners() {
        refreshButton.setOnClickListener {
            jokeDetailsPresenter.getAnotherJoke()
        }
    }

    private fun setupUI() {
        val quotePatternDrawable = QuotePatternDrawable()
        contentContainer.background = quotePatternDrawable

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = quotePatternDrawable.colorScheme.colors[1]
        }
    }

    override fun onStop() {
        super.onStop()

        jokeDetailsPresenter.onStop()
    }

    override fun showJoke(joke: String) {
        jokesContentLabel.text = joke
    }

    override fun showErrorToast() {
        Toast
            .makeText(this, "Can't get a random joke, please try again", Toast.LENGTH_SHORT)
            .show()
    }

    override fun toggleRefreshButton(isEnabled: Boolean) {
        refreshButton.isEnabled = isEnabled
    }

    override fun showRefreshingOnProgressToast() {
        Toast
            .makeText(this, "Getting another joke for ya~", Toast.LENGTH_LONG)
            .show()
    }
}
