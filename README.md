  ThemoviedbTest
  Android LiveData & ViewModel
  ---------------------------------------

  A small application where the architecture based on components is implemented as recommended by the official [android developer page][developer].

  [developer]: https://developer.android.com/jetpack/guide


  This application shows a list of the most popular movies with their ranking of votes.


  ![screenshot1](https://github.com/Santi92/themoviedb_test/blob/main/images/Screenshot_1.png)



  We also have the detail of the movies where we can see their title, release date and language, with a brief description


  ![screenshot2](https://github.com/Santi92/themoviedb_test/blob/main/images/Screenshot_2.png)


  ### Architecture
  This architecture, as the 3-layer image is displayed, viewModel is responsible for cleaning and managing the data to be shown to the UI, The UI layer is considered a raw layer since it should not contain logic, it should only be based on visual components, the The bottom layer is the data layer but in this case android developer recommend using the repository pattern, this pattern complies with the as the source of truth since it always has different data providers such as MovieDao and MovieApiService in this case.

  ![Archtiture](https://github.com/kioko/android-liveData-viewModel/blob/master/art/archtiture.png)


  ### Prerequisites

  As previously mentioned that this app shows the most popular movies, for this we use [themoviedb](https://www.themoviedb.org/movie), where we follow the steps in [the documentation of the api version 3](https://developers.themoviedb.org/3/getting-started ) since it is the The only version that has the service of obtaining the most popular movies.

  For the app to make requests you require a [TMDB API key](https://developers.themoviedb.org/3/getting-started ).

  Once you have it, open the `build.gradle` file at the app level and paste your API key into the`API_KEY` variable.

  EXAMPLE
  ```groovy
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
