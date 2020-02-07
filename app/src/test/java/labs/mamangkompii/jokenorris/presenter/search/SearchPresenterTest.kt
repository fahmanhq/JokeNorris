package labs.mamangkompii.jokenorris.presenter.search

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import labs.mamangkompii.jokenorris.capture
import labs.mamangkompii.jokenorris.datastore.remote.model.JokesQueryResultResponse
import labs.mamangkompii.jokenorris.datastore.remote.model.SearchJokesResponse
import labs.mamangkompii.jokenorris.repository.JokesRepository
import labs.mamangkompii.jokenorris.view.search.SearchResultVModel
import labs.mamangkompii.jokenorris.view.search.SearchResultView
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {

    private val searchResultView = Mockito.mock(SearchResultView::class.java)
    private val jokesRepository = Mockito.mock(JokesRepository::class.java)
    private val compScheduler = Schedulers.trampoline()
    private val uiScheduler = Schedulers.trampoline()
    private val presenter = SearchPresenterImpl(
        searchResultView, jokesRepository, compScheduler, uiScheduler
    )

    @Captor
    private lateinit var searchResultCaptor: ArgumentCaptor<List<SearchResultVModel>>

    @Test
    fun searchJokesByKeyword_WithSuccessResponse_ShouldCallViewShowResult() {
        val sampleResponse = ArrayList<JokesQueryResultResponse>()
        sampleResponse.add(JokesQueryResultResponse(emptyList(), "Jokes1"))
        sampleResponse.add(JokesQueryResultResponse(listOf("Category1", "Category2"), "Jokes2"))
        sampleResponse.add(JokesQueryResultResponse(emptyList(), "Jokes3"))

        val successResponse = SearchJokesResponse(sampleResponse.size, sampleResponse)

        Mockito
            .`when`(jokesRepository.queryJokesByKeyword(Mockito.anyString()))
            .thenReturn(Observable.just(successResponse))

        presenter.searchJokesByKeyword("")

        Mockito.verify(searchResultView).showSearchResult(capture(searchResultCaptor))
        Assert.assertNotNull(searchResultCaptor.value)

        searchResultCaptor.value.forEachIndexed { index, searchResultVModel ->
            Assert.assertEquals(sampleResponse[index].joke, searchResultVModel.joke)
            Assert.assertEquals(sampleResponse[index].categories, searchResultVModel.categories)
        }
    }

    @Test
    fun searchJokesByKeyword_WithErrorResponse_ShouldCallViewShowErrorSearchToast() {
        Mockito
            .`when`(jokesRepository.queryJokesByKeyword(Mockito.anyString()))
            .thenReturn(Observable.error(Exception()))

        presenter.searchJokesByKeyword("")

        Mockito.verify(searchResultView).showErrorSearchToast()
    }
}