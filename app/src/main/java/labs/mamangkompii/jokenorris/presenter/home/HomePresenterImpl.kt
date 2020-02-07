package labs.mamangkompii.jokenorris.presenter.home

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import labs.mamangkompii.jokenorris.repository.JokesRepository
import labs.mamangkompii.jokenorris.view.home.JokesCategoriesView

class HomePresenterImpl(
    private val jokesCategoriesView: JokesCategoriesView,
    private val jokesRepository: JokesRepository,
    private val uiScheduler: Scheduler
) : HomePresenter {

    private var disposable: Disposable? = null

    override fun fetchJokesCategories() {
        disposable = jokesRepository.getJokesCategories()
            .observeOn(uiScheduler)
            .subscribe(
                {
                    jokesCategoriesView.showJokesCategories(it)
                },
                {
                    jokesCategoriesView.showErrorToast()
                }
            )
    }

    override fun onStart() {

    }

    override fun onStop() {
        disposable?.dispose()
    }
}