package labs.mamangkompii.jokenorris.datastore.remote.model

import com.google.gson.annotations.SerializedName

data class GetRandomJokeResponse(
    @SerializedName("value") val joke: String
)