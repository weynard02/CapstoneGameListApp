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
    id("org.owasp.dependencycheck") version "8.4.0" apply false
}

ktlint {
    version.set("0.45.2")
    android.set(true)
    ignoreFailures = true
    disabledRules.set(listOf(
        "final-newline",
        "no-wildcard-imports",
        "max-line-length",
        "no-multi-spaces",
        "no-empty-line-before",
        "missing-newline-after-opening-parenthesis", // Missing newline after "("
        "missing-newline-before-closing-parenthesis" // Missing newline before ")"
    ))
}

// Move this block right after configuring ktlint
tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask>().configureEach {
    onlyIf { !project.hasProperty("skipKtlint") }
}

checkstyle {
    toolVersion = "10.3.3"
    configFile = file("app/config/checkstyle/checkstyle.xml")
}

tasks.withType<Checkstyle> {
    source = fileTree("src/main/java").apply {
        include("**/*.java")
    }
    include("**/*.kt")
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}

