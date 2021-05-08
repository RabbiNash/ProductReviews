<p align="center">Min Sdk
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

# Product Review App

## Assignment for Android Engineer Role

Hello, Bonjour - This is a repository for a Product Reviews App.

The app is written 94.4% in Kotlin with all the necessary Unit and Instrumentation tests.

As such in this project i aim to demonstrate:

* Clean Architecture with MVVM (Model View ViewModel) on the presentation layer
* Use of Jetpack libraries
* Use of Kotlin's Coroutines and Flow for background execution
* Dependency Injection using Dagger Hilt
* Persistence with room.
* Clean UI with material design.
* Finally it takes a sneak peak into github actions for autobuilds and firebase crashlytics for crash logging.

## Prerequisites

In order to run this project you need the following:
- Android Studio 4.1.1 or better
- Gradle 6.5 or better
- JDK 1.8
- [Android SDK](https://developer.android.com/studio/index.html)

## How i went about it

<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg"/>
<br>

Its practially improbable to have it all in order, as disorder is the natural state of order. However in this project i tried keep a distinct seperation of. concerns, that in turn brings a level of structural sanity to the codebase. As shown in the pictorial illustration of clean architecture, this app is divided into three sub modules - Domain, Data and App.

### Domain Layer

The domain layer contains UseCase / Interactor instances that are used to collect data from the Data Layer and pass it to the Presentation Layer (App). As such in our context we have four UseCases `GetProducts`, `GetReviews`, `SearchProduct`, `RetreiveReviews`. Following the dependency inversion principle (DIP) that higher level modules should not depend on low level modules, this layer does not have any mapper classes.

All the interfaces to be used in the data layer were defined here, also in accordance with the DIP principle, this is to make sure that all the modules depend on abstractions.

### Data Layer

The data layer is responsible for the data that will be fed to the entire application, in our case we had to fetch data from a remote source and cache it locally so that we the app can still function offline. As an agreement to the contract shared by the domain layer, concrete classes implementing the interfaces reside in this layer. With the aid of mapping we are able to have an exchange in abstractions between the two layers.

The networking here is performed by `Retrofit`. A popular Http client for andrioid. Retrofit depends on `OkHttp` to make requests.
`Retrofit` also has builtin support for `Coroutines` hence my choice in the library. Caching and persistence was done via `Room`

### App (The presentation) Layer

This layer has houses all  Android Framework specific tooling i.e User Interface (UI) and  `ViewModels` which bridge the gap between the data abstractions and the UI. The architecture used in this layer was MVVM as it gives a good seperation of concerns. The availalibity of lifecycle aware components also made the decision to go with MVVM an easy one to make. The ViewModels contain a reference to `UseCase` instances, these references are passed through dependency injection.

#### Tooling Used
`Data binding` this is an amazing tool, using binding adapters reduces a lot of boiler plate, thus making views cleaner.
`LiveData` keeps you updated all the time.
`Dagger Hilt` Simple dependency management.
`Lottie Android` For the nice sneaker animation
`Firebase Crashlytics` For crash logging

## Tests

Unit Tests are available for each layer and Instrumentation tests are present in the presentation layer. I tried to cover as much as i can, but i think there could still be room for more.

## Side Note
I used Flow to reduce the number of requests to the database made by the `SearchProduct` usecase. With the aid of the debounce operator, whenever a user types into the search field, it waits for 500ms before making a request.

## What to improve

Adding a splash screen could help, so that on first run all the data can be downloaded whilst the user is entertained.


## Libraries I chose to use

* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Retrofit](http://square.github.io/retrofit/) - An http client for android
* [Okhttp](http://square.github.io/okhttp/) - For networking requests
* [Mockito](http://site.mockito.org/) - For mocking instances
* [Moshi](https://github.com/square/moshi) - For parsing JSON into Objects
* [Dagger Hilt](https://dagger.dev/hilt/) - For dependency injection
* [Lottie Android](https://github.com/airbnb/lottie-android) - For animation
* [Truth](https://truth.dev/) - For assertions during testing
* Jetpack Libraries


## Screenshots
 
<img src="http://nashe.dev/wp-content/uploads/2021/05/4.jpeg" width="250px"/> <img src="http://nashe.dev/wp-content/uploads/2021/05/2.jpeg" width="250px"/> <img src="http://nashe.dev/wp-content/uploads/2021/05/3.jpeg" width="250px"/>

