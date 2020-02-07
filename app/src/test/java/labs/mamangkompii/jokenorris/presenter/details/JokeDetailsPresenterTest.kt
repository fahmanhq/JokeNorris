package labs.mamangkompii.jokenorris.presenter.details

import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import labs.mamangkompii.jokenorris.capture
import labs.mamangkompii.jokenorris.datastore.remote.model.GetRandomJokeResponse
import labs.mamangkompii.jokenorris.repository.JokesRepository
import labs.mamangkompii.jokenorris.view.details.SingleJokeShowcaseView
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JokeDetailsPresenterTest {

    private val singleJokeShowcaseView = Mockito.mock(SingleJokeShowcaseView::class.java)
    private val jokesRepository = Mockito.mock(JokesRepository::class.java)
    private val selectedCategory = "random"
    private val uiScheduler = Schedulers.trampoline()
    private val presenter = JokeDetailsPresenterImpl(
        singleJokeShowcaseView, jokesRepository, selectedCategory, uiScheduler
    )

    @Test
    fun getRandomJoke_WithSuccessResponse_ShouldCallViewShowJokeAndEnableRefreshButton() {
        val jokeContent = "daJokes"
        val successResponse = GetRandomJokeResponse(jokeContent)
        val responseStream =
            PublishSubject.create<GetRandomJokeResponse>()

        Mockito
            .`when`(jokesRepository.getRandomJokes(selectedCategory))
            .thenReturn(responseStream)

        presenter.getRandomJoke()

        val inOrder = Mockito.inOrder(singleJokeShowcaseView)

        val buttonStateCaptor: ArgumentCaptor<Boolean> =
            ArgumentCaptor.forClass<Boolean, Boolean>(Boolean::class.java)
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))
        Assert.assertFalse(buttonStateCaptor.value)

        responseStream.onNext(successResponse)

        val jokeCaptor: ArgumentCaptor<String> =
            ArgumentCaptor.forClass<String, String>(String::class.java)
        inOrder.verify(singleJokeShowcaseView).showJoke(capture(jokeCaptor))

        Assert.assertEquals(jokeContent, jokeCaptor.value)

        responseStream.onComplete()
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))

        Assert.assertTrue(buttonStateCaptor.value)
    }

    @Test
    fun getRandomJoke_WithErrorResponse_ShouldCallViewShowErrorToastAndEnableRefreshButton() {
        val responseStream =
            PublishSubject.create<GetRandomJokeResponse>()

        Mockito
            .`when`(jokesRepository.getRandomJokes(selectedCategory))
            .thenReturn(responseStream)

        presenter.getRandomJoke()

        val inOrder = Mockito.inOrder(singleJokeShowcaseView)

        val buttonStateCaptor: ArgumentCaptor<Boolean> =
            ArgumentCaptor.forClass<Boolean, Boolean>(Boolean::class.java)
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))
        Assert.assertFalse(buttonStateCaptor.value)

        responseStream.onError(Exception())

        Mockito.verify(singleJokeShowcaseView).showErrorToast()
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))

        Assert.assertTrue(buttonStateCaptor.value)
    }

    @Test
    fun getAnotherJoke_WithErrorResponse_ShouldCallViewShowErrorToastAndEnableRefreshButton() {
        val responseStream =
            PublishSubject.create<GetRandomJokeResponse>()

        Mockito
            .`when`(jokesRepository.getRandomJokes(selectedCategory))
            .thenReturn(responseStream)

        presenter.getAnotherJoke()

        val inOrder = Mockito.inOrder(singleJokeShowcaseView)

        Mockito.verify(singleJokeShowcaseView).showRefreshingOnProgressToast()

        val buttonStateCaptor: ArgumentCaptor<Boolean> =
            ArgumentCaptor.forClass<Boolean, Boolean>(Boolean::class.java)
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))
        Assert.assertFalse(buttonStateCaptor.value)

        responseStream.onError(Exception())

        Mockito.verify(singleJokeShowcaseView).showErrorToast()
        inOrder.verify(singleJokeShowcaseView).toggleRefreshButton(capture(buttonStateCaptor))

        Assert.assertTrue(buttonStateCaptor.value)
    }
}