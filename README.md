# Popular TV Series App

## Overview

The Popular TV Series App is an Android application designed to display a list of movies using Jetpack Compose for modern UI development and Room for local data storage. 
The app fetches movie data from a remote API and provides a seamless experience by managing both online and offline data.

## Features

- Fetch Movies: Retrieve a list of movies from a remote API.
- Display Movies: Show movie details using Jetpack Compose.
- Local Storage: Save movie data to a local database using Room.
- Offline Mode: Fetch and display movie data even when offline.

## Architecture

- Jetpack Compose: For building a modern and responsive UI.
- Room**: For local data storage and offline capabilities.
- ViewModel: To manage UI-related data in a lifecycle-conscious way.
- Repository Pattern: To handle data operations and abstract data sources.

## Project Structure

- `MainActivity.kt`: The main entry point of the application where the `MovieScreen` composable is set up.
- `MovieViewModel.kt`: ViewModel for managing movie data.
- `MovieRepository.kt`: Repository for handling data operations from the API and local storage.
- `ApiService.kt`: Contains the network API service implementation.
- `RoomDao.kt`: Data Access Object for Room database operations.
- `Movie.kt`: Data model representing movie details.
- `RoomModel.kt`: Data model representing movie details for Room database storage.
- `MovieItem.kt`: Composable for displaying individual movie items.
- `MovieScreen.kt`: Composable that contains the main UI logic for displaying movies.

## Dependencies

- Jetpack Compose
- Room
- Lifecycle
- Kotlin Coroutines
- Retrofit (for API service)

## Setup

1. Clone the repository: bash
   git clone https://github.com/SanthoshKuma6/popular-tv-series-app.git
