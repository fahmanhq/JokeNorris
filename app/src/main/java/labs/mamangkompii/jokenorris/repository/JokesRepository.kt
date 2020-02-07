package labs.mamangkompii.jokenorris.repository

import io.reactivex.Observable
import labs.mamangkompii.jokenorris.datastore.remote.model.GetRandomJokeResponse
import labs.mamangkompii.jokenorris.datastore.remote.model.SearchJokesResponse

interface JokesRepository {

    fun getRandomJokes(category: String): Observable<GetRandomJokeResponse>

    fun getJokesCategories(): Observable<List<String>>

    fun queryJokesByKeyword(keyword: String): Observable<SearchJokesResponse>
}