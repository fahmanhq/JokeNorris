package labs.mamangkompii.jokenorris.presenter.search

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import labs.mamangkompii.jokenorris.repository.JokesRepository
import labs.mamangkompii.jokenorris.view.search.SearchResultVModel
import labs.mamangkompii.jokenorris.view.search.SearchResultView

class SearchPresenterImpl(
    private val searchResultView: SearchResultView,
    private val jokesRepository: JokesRepository,
    private val compScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : SearchPresenter {

    private var disposable: Disposable? = null

    override fun searchJokesByKeyword(keyword: String) {
        disposable = jokesRepository.queryJokesByKeyword(keyword)
            .observeOn(compScheduler)
            .map { response ->
                val resultList = ArrayList<SearchResultVModel>()

                response.result.forEach {
                    resultList.add(
                        SearchResultVModel(it.joke, it.categories)
                    )
                }

                return@map resultList
            }
            .observeOn(uiScheduler)
            .subscribe(
                {
                    searchResultView.showSearchResult(it)
                },
                {
                    searchResultView.showErrorSearchToast()
                }
            )
    }

    override fun onStart() {

    }

    override fun onStop() {
        disposable?.dispose()
    }

}