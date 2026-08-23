# Trackly

Android application for tracking regular activities with weekly goals. Create activity cards, log sessions, and monitor your weekly progress.

## Features

- Create activity cards with optional weekly goals
- Log sessions with duration and notes
- Weekly progress bar with goal tracking
- Session history per activity
- Dark theme
- English and Russian language support
- Full offline support — all data stored locally

## Tech Stack

- UI — Jetpack Compose, Material3
- Architecture — MVVM, Repository pattern
- DI — Koin
- Database — Room
- Async — Kotlin Coroutines, StateFlow
- Navigation — Navigation Compose

## Getting Started

1. Clone the repository
git clone https://github.com/fruitella1/Trackly.git

2. Open and run the project in Android Studio

## Planned Features

- Weekly streak — track how many weeks in a row you hit your goal
- Weekly report — percentage of goal completion per activity
- Smart notifications — daily reminder if no session logged for the day
- Kotlin Multiplatform version — after all features are complete, the app will be rewritten using KMP to support both Android and iOS
