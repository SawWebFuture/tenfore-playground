# ⛳ TenFore Playground — Android App (Jetpack Compose)

TenFore Playground is a fun sandbox to explore **Jetpack Compose** patterns, navigation, and data flows—starting simple (fake login + product list) and expanding into real-world app architecture.

---

## 🚀 Features (Current)

### ✅ Login Flow (MVVM + StateFlow)
- Auto-filled test credentials using `FakeAuthRepository`
- Hidden password field (`PasswordVisualTransformation`)
- Loading state + error handling
- Navigation to Home on success

### ✅ Home Screen
- Displays a list of golf products (`GolfItem`) with images
- Material 3 UI components
- “Buy” button with modal confirmation & purchased state

### ✅ Navigation
- `Splash → Login → Home`
- Back stack cleared after login
- Uses `NavHost` + `composable` routes

### ✅ Architecture
- **MVVM** (Model–View–ViewModel) (MVI variant available for learning)
- **StateFlow** for reactive UI updates
- **Repository Pattern** for testable data access
- **Jetpack Compose UI**
- **Material 3** design system

---

## 🛠️ Tech Stack (Current)

- **Kotlin** + **Coroutines**
- **Jetpack Compose**
- **Compose Material 3**
- **ViewModel**
- **StateFlow / MutableStateFlow**
- **Navigation Compose**
- **Coil** (network images)

---

## 🌟 Good-to-Have / Roadmap

> These are optional enhancements commonly used in production apps. Pick and choose to learn or to harden the project.

### 1) Dependency Injection
- **Hilt** (recommended): component-scoped injection for ViewModels/Repos/UseCases
    - `com.google.dagger:hilt-android` + `androidx.hilt:hilt-navigation-compose`
- **Koin** (alternative): lightweight DI with Kotlin DSL
    - `io.insert-koin:koin-androidx-compose`

### 2) Networking (REST)
- **Retrofit** + **OkHttp** + **kotlinx.serialization** or **Moshi**
    - Add `AuthRepository` -> `AuthApi` interfaces, interceptors, logging, timeouts
    - Handle `Result<T>` + error mapping + retry/backoff

### 3) GraphQL (Codegen)
- **Apollo Kotlin** (GraphQL client with codegen)
    - Schema + `.graphql` ops → generated models
    - Normalized cache, queries, mutations, subscriptions
    - Works well with coroutines & Compose

### 4) Persistence / Caching
- **DataStore (Preferences/Proto)** for tokens, flags, and user prefs
- **Room** for offline product cache, cart, orders
- **SQLDelight** (alternative) for type-safe SQL + multiplatform options

### 5) Analytics & Crash Reporting
- **Firebase Analytics** (events, user properties)
- **Firebase Crashlytics** (crash reports, non-fatal logs)
- Event wrapper w/ type-safe events (sealed classes) to avoid stringly-typed calls

### 6) Observability / Logging
- **Timber** for structured logs
- **Kermit** (MPP) if exploring multiplatform
- Optional: **Sentry** or **Datadog** for traces, metrics

### 7) Feature Flags & Remote Config
- **Firebase Remote Config** or **LaunchDarkly**
    - Gate new UI, A/B tests, staged rollouts

### 8) App Theming & Design System
- **Material 3** custom color scheme & typography
- **Dynamic color** (Android 12+)
- **Design tokens** and reusable components (buttons, inputs, dialogs)

### 9) State Management Variants (for learning)
- **MVI** (Intent/State/Effect) version of Login & Home
- Snapshot tests for reducers & navigation effects

### 10) Testing
- **Unit tests**: ViewModels, Repos, UseCases (coroutines test, fake repos)
- **UI tests**: Compose testing framework (`createComposeRule`)
- **Network tests**: MockWebServer for Retrofit/Apollo
- **Baseline Profiles**: speed up cold start & scroll performance

### 11) CI/CD
- **GitHub Actions**: lint, detekt/ktlint, unit/UI tests, assemble
- **Gradle Play Publisher** (optionally) for internal track uploads
- **Versioning**: auto bump versionCode via CI

### 12) Security & Privacy
- **Encrypted DataStore** / **EncryptedSharedPreferences** for secrets
- Network TLS pinning (OkHttp CertificatePinner) if needed
- Minimal pii in logs; redact tokens

### 13) Performance
- **Baseline Profiles** & **Macrobenchmark**
- **StrictMode** for catching IO on main thread during dev
- **R8/Proguard** optimization rules

### 14) Accessibility / i18n
- Content descriptions, minimum touch targets, dynamic font size
- String resources & pluralization; RTL support