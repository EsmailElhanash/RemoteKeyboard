plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.android.gradle.plugin) apply false

    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false

}