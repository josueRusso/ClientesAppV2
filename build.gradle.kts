// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false //ultima version aqui: https://developer.android.com/jetpack/androidx/releases/compose-kotlin#kts
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false //aqui se busca la ultima version https://github.com/google/ksp/releases
    id("com.google.dagger.hilt.android") version "2.48" apply false

}