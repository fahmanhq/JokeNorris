package labs.mamangkompii.jokenorris.view.details

interface SingleJokeShowcaseView {
    fun showJoke(joke: String)
    fun showErrorToast()
    fun toggleRefreshButton(isEnabled: Boolean)
    fun showRefreshingOnProgressToast()
}