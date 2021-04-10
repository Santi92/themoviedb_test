package com.santiago.themoviedbtest.data.local.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class MovieEntity(
    @SerializedName("id")
    val id: Long,

    @SerializedName(value = "title")
    val title: String,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName(value = "description", alternate = ["overview", "synopsis"])
    var description: String?,

    @SerializedName("release_date")
    var releaseDate: String?,

    @SerializedName("vote_average")
    var voteAverage: Double?,


    @SerializedName("original_language")
    var originalLanguage: String?


) {

    fun getMoviePath(): String? {

        posterPath?.run {
            posterPath = String.format("https://image.tmdb.org/t/p/w500%s", posterPath)
        }

        return posterPath
    }

    fun getTypeRaking():TypeRakingMovie {
        val vote =  voteAverage?: 0.0
        return when {

            vote >= 7.0 -> Success

            vote in 4.0..6.9 -> Regular

            else -> Low
        }
    }

    fun getVoteAveragePercentage(): Double{

        return ((voteAverage?:1.0) *  10.0)
    }
}

sealed class TypeRakingMovie
object Regular : TypeRakingMovie()
object Success : TypeRakingMovie()
object Low : TypeRakingMovie()
