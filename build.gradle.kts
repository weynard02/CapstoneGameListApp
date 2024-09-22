// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("jacoco")
    id("checkstyle")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.owasp.dependencycheck") version "10.0.4" apply false
}

ktlint {
    version.set("0.45.2")
    android.set(true)
}

checkstyle {
    toolVersion = "10.3.3"
    configFile = file("app/config/checkstyle/checkstyle.xml")
}

tasks.withType<Checkstyle>{
    source = fileTree("src/main/java").apply {
        include("**/*.java")
    }
    include("**/*.kt")
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}
