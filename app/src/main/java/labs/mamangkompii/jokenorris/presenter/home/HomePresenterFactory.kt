package labs.mamangkompii.jokenorris.presenter.home

import io.reactivex.android.schedulers.AndroidSchedulers
import labs.mamangkompii.jokenorris.repository.JokesRepositoryFactory
import labs.mamangkompii.jokenorris.view.home.JokesCategoriesView

class HomePresenterFactory {

    fun createPresenter(view: JokesCategoriesView): HomePresenter {
        val jokesRepository = JokesRepositoryFactory().createRepository()

        return HomePresenterImpl(
            view,
            jokesRepository,
            AndroidSchedulers.mainThread()
        )
    }
}