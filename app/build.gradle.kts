plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.dicoding.shof"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.shof"
        minSdk = 26
        targetSdk = 33
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
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    dynamicFeatures += setOf(":favorites")
}

dependencies {

    implementation(project(":core"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:3.12.4")
    testImplementation("androidx.arch.core:core-testing:2.1.0") // InstantTaskExecutorRule

    val navVersion = "2.7.6"

    api("androidx.navigation:navigation-fragment-ktx:$navVersion")
    api("androidx.navigation:navigation-ui-ktx:$navVersion")
    api("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

}