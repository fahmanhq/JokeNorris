package labs.mamangkompii.jokenorris.datastore.remote.model

import com.google.gson.annotations.SerializedName

data class JokesQueryResultResponse(
    val categories: List<String>,
    @SerializedName("value") val joke: String
)