package labs.mamangkompii.jokenorris.repository

import io.reactivex.Observable
import io.reactivex.Scheduler
import labs.mamangkompii.jokenorris.datastore.remote.JokesRemoteDataStore
import labs.mamangkompii.jokenorris.datastore.remote.model.GetRandomJokeResponse
import labs.mamangkompii.jokenorris.datastore.remote.model.SearchJokesResponse

class JokesRepositoryImpl(
    private val jokesRemoteDataStore: JokesRemoteDataStore,
    private val ioScheduler: Scheduler
) : JokesRepository {

    override fun getRandomJokes(category: String): Observable<GetRandomJokeResponse> {
        return jokesRemoteDataStore.getRandomJokes(category)
            .subscribeOn(ioScheduler)
    }

    override fun getJokesCategories(): Observable<List<String>> {
        return jokesRemoteDataStore.getJokesCategories()
            .subscribeOn(ioScheduler)
    }

    override fun queryJokesByKeyword(keyword: String): Observable<SearchJokesResponse> {
        return jokesRemoteDataStore.queryJokesByKeyword(keyword)
            .subscribeOn(ioScheduler)
    }
}