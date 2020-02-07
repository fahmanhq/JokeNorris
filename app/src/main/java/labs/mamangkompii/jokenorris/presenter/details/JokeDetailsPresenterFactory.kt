package labs.mamangkompii.jokenorris.presenter.details

import io.reactivex.android.schedulers.AndroidSchedulers
import labs.mamangkompii.jokenorris.repository.JokesRepositoryFactory
import labs.mamangkompii.jokenorris.view.details.SingleJokeShowcaseView

class JokeDetailsPresenterFactory {

    fun createPresenter(
        view: SingleJokeShowcaseView,
        selectedCategory: String
    ): JokeDetailsPresenter {
        val jokesRepository = JokesRepositoryFactory().createRepository()

        return JokeDetailsPresenterImpl(
            view,
            jokesRepository,
            selectedCategory,
            AndroidSchedulers.mainThread()
        )
    }
}