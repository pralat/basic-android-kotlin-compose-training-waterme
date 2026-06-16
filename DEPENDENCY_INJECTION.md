# Dependency Injection in Water Me

This document explains how Dependency Injection (DI) is implemented in this project using the **Manual Dependency Injection** pattern.

## Overview

Dependency Injection is a pattern where an object's dependencies are provided to it rather than the object creating them itself. In this project, we use a "Container" to manage these dependencies.

## Key Components

### 1. The Container (`AppContainer.kt`)
The `AppContainer` is a central place to manage and instantiate dependencies.
- **`AppContainer` (Interface)**: Defines the dependencies that must be provided (e.g., `waterRepository`).
- **`DefaultAppContainer` (Implementation)**: The actual class that creates the repository instance. It takes a `Context` to initialize the repository.


### 2. The Application (`WaterMeApplication.kt`)
The custom `Application` class acts as the global host for the container. Since the `Application` instance exists for the entire duration of the app process, the container and its dependencies also persist.


### 3. The ViewModel Factory (`WaterViewModel.kt`)
This is where dependencies are "injected" into the UI layer. The `WaterViewModel` does not create the repository; it receives it via its constructor. The `Factory` handles the retrieval from the application container.


## The Injection Flow
1.  App Start: Android OS starts the process and instantiates WaterMeApplication.
2.  Container Init: WaterMeApplication.onCreate() creates the DefaultAppContainer.
3.  Dependency Ready: DefaultAppContainer instantiates the WorkManagerWaterRepository.
4.  UI Launch: The user opens the app, and MainActivity starts.
5.  Injection: When WaterViewModel is needed, the Factory retrieves the repository from the Application's container and passes it into the ViewModel's constructor.

## Benefits of this Pattern
* Testability: You can swap the real repository for a mock version in unit tests by providing a different implementation of AppContainer.
* Separation of Concerns: The ViewModel focuses on UI logic, while the Container focuses on object creation.
* Scalability: As the app grows, you can add more repositories to the AppContainer without cluttering your Application class.


