package labs.mamangkompii.jokenorris.view.search

interface SearchResultView {
    fun showSearchResult(searchResult: List<SearchResultVModel>)
    fun showErrorSearchToast()
}