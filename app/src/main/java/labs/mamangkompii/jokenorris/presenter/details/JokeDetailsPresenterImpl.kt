package labs.mamangkompii.jokenorris.presenter.details

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import labs.mamangkompii.jokenorris.repository.JokesRepository
import labs.mamangkompii.jokenorris.view.details.SingleJokeShowcaseView

class JokeDetailsPresenterImpl(
    private val singleJokeShowcaseView: SingleJokeShowcaseView,
    private val jokesRepository: JokesRepository,
    private val selectedCategory: String,
    private val uiScheduler: Scheduler
) : JokeDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getRandomJoke() {
        compositeDisposable.add(
            jokesRepository.getRandomJokes(selectedCategory)
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        singleJokeShowcaseView.showJoke(it.joke)
                    },
                    {
                        singleJokeShowcaseView.showErrorToast()
                    },
                    {
                        singleJokeShowcaseView.toggleRefreshButton(true)
                    },
                    {
                        singleJokeShowcaseView.toggleRefreshButton(false)
                    }
                )
        )
    }

    override fun getAnotherJoke() {
        compositeDisposable.add(
            jokesRepository.getRandomJokes(selectedCategory)
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        singleJokeShowcaseView.showJoke(it.joke)
                    },
                    {
                        singleJokeShowcaseView.showErrorToast()
                    },
                    {
                        singleJokeShowcaseView.toggleRefreshButton(true)
                    },
                    {
                        singleJokeShowcaseView.showRefreshingOnProgressToast()
                        singleJokeShowcaseView.toggleRefreshButton(false)
                    }
                )
        )
    }

    override fun onStart() {
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}