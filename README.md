# ⛳ TenFore Playground — Android App (Jetpack Compose)

TenFore Playground is a fun project to play around with jetpack compose
---

## 🚀 Features

### ✅ Login Flow (MVVM + StateFlow)
- Auto-filled test credentials using `FakeAuthRepository`
- Hidden password field (`PasswordVisualTransformation`)
- Loading state + error handling
- Navigation to Home on success

### ✅ Home Screen
- Displays a list of golf products (`GolfItem`)
- Material 3 UI components
- Simple “Buy” button callback per item

### ✅ Navigation
- `Splash → Login → Home`
- Back stack cleared after login
- Uses `NavHost` + `composable` routes

### ✅ Architecture
- **MVVM** (Model–View–ViewModel)
- **StateFlow** for reactive UI updates
- **Repository Pattern** for testable data access
- **Jetpack Compose UI**
- **Material 3** design system

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Compose Material 3**
- **ViewModel**
- **StateFlow / MutableStateFlow**
- **Kotlin Coroutines**
- **Navigation Compose**
