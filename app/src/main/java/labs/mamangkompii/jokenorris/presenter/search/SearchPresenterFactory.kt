package labs.mamangkompii.jokenorris.presenter.search

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import labs.mamangkompii.jokenorris.repository.JokesRepositoryFactory
import labs.mamangkompii.jokenorris.view.search.SearchResultView

class SearchPresenterFactory {

    fun createPresenter(view: SearchResultView): SearchPresenter {
        val jokesRepository = JokesRepositoryFactory().createRepository()

        return SearchPresenterImpl(
            view,
            jokesRepository,
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }
}