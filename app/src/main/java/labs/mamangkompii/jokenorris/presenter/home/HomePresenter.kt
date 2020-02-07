package labs.mamangkompii.jokenorris.presenter.home

import labs.mamangkompii.jokenorris.presenter.BasePresenter

interface HomePresenter :
    BasePresenter {

    fun fetchJokesCategories()
}