
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("jacoco")
    id("checkstyle")
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.owasp.dependencycheck") version "8.1.0" apply false
}

