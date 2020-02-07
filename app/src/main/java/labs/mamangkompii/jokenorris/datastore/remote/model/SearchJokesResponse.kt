package labs.mamangkompii.jokenorris.datastore.remote.model

data class SearchJokesResponse(
    val total: Int,
    val result: List<JokesQueryResultResponse>
)