package labs.mamangkompii.jokenorris.presenter.search

import labs.mamangkompii.jokenorris.presenter.BasePresenter

interface SearchPresenter : BasePresenter {
    fun searchJokesByKeyword(keyword: String)
}