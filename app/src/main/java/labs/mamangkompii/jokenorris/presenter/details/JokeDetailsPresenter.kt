package labs.mamangkompii.jokenorris.presenter.details

import labs.mamangkompii.jokenorris.presenter.BasePresenter

interface JokeDetailsPresenter : BasePresenter {
    fun getRandomJoke()
    fun getAnotherJoke()
}