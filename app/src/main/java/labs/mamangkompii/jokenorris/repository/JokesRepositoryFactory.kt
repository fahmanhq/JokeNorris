package labs.mamangkompii.jokenorris.repository

import io.reactivex.schedulers.Schedulers
import labs.mamangkompii.jokenorris.datastore.remote.JokesRemoteDataStore
import labs.mamangkompii.jokenorris.datastore.remote.ServiceGenerator

class JokesRepositoryFactory {

    fun createRepository(): JokesRepository {
        val jokesRemoteDataStore =
            ServiceGenerator.createService(JokesRemoteDataStore::class.java)

        return JokesRepositoryImpl(
            jokesRemoteDataStore,
            Schedulers.io()
        )
    }
}