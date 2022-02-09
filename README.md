
# Praxis
2022 Hit Refresh! Praxis now loves ❤️ Jetpack Compose

<div align="left">
    <a href = "https://developer.android.com/jetpack/androidx/versions/all-channel#may_5_2021">
        <img src = "https://img.shields.io/badge/Jetpack%20Compose-1.0.0%20beta08-brightgreen" />
    </a>
</div>
<br>

This is a sample app written in Kotlin following clean architecture principles which uses slack app features as an example.

The purpose of this app to showcase:
- Implementation of Jetpack Android Architecture components with Dagger Hilt and Data Binding to minimize boilerplate code.
- Implementation of an architecture that will support both XML and the new Compose.
- Implementation of Modular Multi Module Navigation Architecture.
- Performing background task with Kotlin Coroutines

# Requirements
1. Android Studio : Arctic Fox | 2020.3.1	3.1 or higher
2. Android Emulator or Physical android device

# Built With 🏗

| Tools | Link |
|     :---      |          :---: |
| 🤖   Kotlin | [Kotlin](https://kotlinlang.org/) |
| 🏛   Architecture Components | [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) |
| ⛓   Data Binding | [Android Data Binding](https://developer.android.com/topic/libraries/data-binding) |
| 💉   Dagger Hilt | [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) |
| 🌐   Retrofit | [Retrofit](http://square.github.io/retrofit/) |
| 🚦   OkHttp | [OkHttp](http://square.github.io/okhttp/) |
| 📄   Gson Parsing | [Gson](https://github.com/google/gson) |
| 🌊   Coroutines | [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) |
| 🏄🏼‍♀️   Flows | [Flows](https://developer.android.com/kotlin/flow) |


## Architecture
### Praxis follows the principles of Clean Architecture with Android Architecture Components.

### Architecture's layers & boundaries:

<img src="art/architecture.jpeg" />

**Presentation Layer**  contains  _UI (Activities & Fragments)_  that are coordinated by  _ViewModels which execute 1 or multiple UseCases._  Presentation Layer depends on Domain Layer.

**Domain Layer** is the most INNER part of the circle (no dependencies with other layers) and it contains _Entities, Use cases & Repository Interfaces._ Use cases combine data from 1 or multiple Repository Interfaces.

**Data Layer**  contains  _Repository Implementations and 1 or multiple Data Sources._  Repositories are responsible to coordinate data from the different Data Sources. Data Layer depends on Domain Layer.

**Notes:** Mapping between response models and transformed models will happen via extension functions defined in transformed model file


### Conventions:
Files are suffixed with be defined Class types.
- ViewModels are suffixed with **VM**. Ex: `LoginVM`
- UseCases are suffixed with **UseCase**. Ex: `LoginUseCase`
- Sources are suffixed with **Source**. Ex: `LoginRemoteSource`, `LoginLocalSource`
- Repositories are suffixed with **Repo**. Ex: `LoginRepo`

## Conclusion
This project can be used as a template for new apps.
This project is continually evolving to integrate other libraries and techniques to keep it up to date.
