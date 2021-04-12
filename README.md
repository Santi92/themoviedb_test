ThemoviedbTest 
Android LiveData & ViewModel
---------------------------------------

A small application where the architecture based on components is implemented as recommended by the official [android developer page][developer].

[developer]: https://developer.android.com/jetpack/guide


This application shows a list of the most popular movies with their ranking of votes.


![screenshot1](https://github.com/kioko/android-liveData-viewModel/blob/master/art/archtiture.png)



We also have the detail of the movies where we can see their title, release date and language, with a brief description 


![screenshot2](https://github.com/kioko/android-liveData-viewModel/blob/master/art/archtiture.png)


### Architecture
This architecture, as the 3-layer image is displayed, viewModel is responsible for cleaning and managing the data to be shown to the UI, The UI layer is considered a raw layer since it should not contain logic, it should only be based on visual components, the The bottom layer is the data layer but in this case android developer recommend using the repository pattern, this pattern complies with the as the source of truth since it always has different data providers such as MovieDao and MovieApiService in this case. 

![Archtiture](https://github.com/kioko/android-liveData-viewModel/blob/master/art/archtiture.png)


### Prerequisites

As previously mentioned that this app shows the most popular movies, for this we use [themoviedb](https://www.themoviedb.org/movie), where we follow the steps in [the documentation of the api version 3](https://developers.themoviedb.org/3/getting-started ) since it is the The only version that has the service of obtaining the most popular movies. 

For the app to make requests you require a [TMDB API key](https://developers.themoviedb.org/3/getting-started ).

Once you have it, open the `build.gradle` file at the app level and paste your API key into the`API_KEY` variable. 

EXAMPLE 
```groovy
import foobar
android {
  buildTypes {

          release {
                    }

          debug {
            buildConfigField "String", "API_KEY", '"YOU_API_KEY"'
          }


      }
}      
```

### Libraries


* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Dagger 2][dagger2] for dependency injection
* [Retrofit][retrofit] for REST api communication
* [OkHttp][OkHttp] for adding interceptors to Retrofit
* [Glide][glide] for image loading
* [Timber][timber] for logging
* [espresso][espresso] for UI tests
* [mockito][mockito] for mocking in tests


[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[OkHttp]: http://square.github.io/okhttp/
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[timber]: https://github.com/JakeWharton/timber
[mockito]: http://site.mockito.org


### License

    Copyright 2017 Thomas Kioko


    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
