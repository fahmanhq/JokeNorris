package labs.mamangkompii.jokenorris.datastore.remote

import io.reactivex.Observable
import labs.mamangkompii.jokenorris.datastore.remote.model.GetRandomJokeResponse
import labs.mamangkompii.jokenorris.datastore.remote.model.SearchJokesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesRemoteDataStore {

    @GET("random")
    fun getRandomJokes(@Query("category") category: String): Observable<GetRandomJokeResponse>

    @GET("categories")
    fun getJokesCategories(): Observable<List<String>>

    @GET("search")
    fun queryJokesByKeyword(@Query("query") keyword: String): Observable<SearchJokesResponse>

}