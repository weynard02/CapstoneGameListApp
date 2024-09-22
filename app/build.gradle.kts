plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("jacoco")
    id("checkstyle")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.owasp.dependencycheck")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.weynard02.capstonegamelistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.weynard02.capstonegamelistapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
//        debug {
//            isMinifyEnabled = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    dynamicFeatures += setOf(":favorite")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.withType<JacocoReport> {
    dependsOn("testDebugUnitTest")
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    sourceDirectories.setFrom(files("src/main/java"))
    classDirectories.setFrom(files("build/tmp/kotlin-classes/debug")) // Or your class directory
    executionData.setFrom(fileTree(mapOf("dir" to "$buildDir", "includes" to "jacoco/testDebugUnitTest.exec")))
}

ktlint {
    version.set("0.45.2")
    android.set(true)
}


checkstyle {
    toolVersion = "10.3.3"
    configFile = file("config/checkstyle/checkstyle.xml")

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

dependencyCheck {
    failBuildOnCVSS = 7f
}



dependencies {

    implementation(project(":core"))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)



}