package com.santiago.themoviedbtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.santiago.themoviedbtest.data.local.entity.MovieEntity

import com.santiago.themoviedbtest.data.remote.model.MovieApiResponse
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


object MockTestUtil {

    fun mockMovie(): MovieEntity {

        return MovieEntity(
            description = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            id = 399566,
            originalLanguage = "en",
            posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            releaseDate = " 2021-03-24",
            title = "Godzilla vs. Kong",
            voteAverage = 8.2
        )
    }

    // Copied from stackoverflow
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}