# Compass AI — Android

Android client for the Compass AI career companion platform.

## Architecture

**Clean Architecture + MVVM** with multi-module Gradle project.

```
app/                  ← Application entry, DI root, root NavHost
core/
  core-domain/        ← Pure Kotlin: domain models, use cases, repository interfaces
  core-data/          ← Repository implementations, Room DB, token storage
  core-network/       ← Retrofit API, DTOs, interceptors
  core-ui/            ← Compose design system, shared components
feature/
  feature-auth/       ← Login / registration screens
  feature-resume/     ← Resume upload and parse review
  feature-analysis/   ← Match result and gap analysis
build-logic/          ← Convention plugins (shared Gradle config)
```

## Key Tech Stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt |
| Navigation | Navigation Compose |
| Networking | Retrofit + OkHttp + kotlinx.serialization |
| Local DB | Room |
| Async | Kotlin Coroutines + Flow |

## Build & Run

```bash
# Clone and open in Android Studio Hedgehog or later
git clone https://github.com/quyenvu249/compass-ai-android.git

# Build debug APK
./gradlew assembleDebug

# Run unit tests
./gradlew testDebugUnitTest

# Run lint + static analysis
./gradlew ktlintCheck detekt
```

## CI

GitHub Actions runs on every PR:
- ktlint + detekt static analysis
- Unit tests with coverage
- Debug APK build
- Instrumented tests (on merge to `main` only)
