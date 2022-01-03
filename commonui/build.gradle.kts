plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}

android {
    compileSdk = ProjectProperties.COMPILE_SDK

    defaultConfig {
        minSdk = (ProjectProperties.MIN_SDK)
        targetSdk = (ProjectProperties.TARGET_SDK)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
    }

}

// Required for annotation processing plugins like Dagger
kapt {
    generateStubs = true
    correctErrorTypes = true
}

dependencies {
    /*Kotlin*/
    api(Lib.Kotlin.KT_STD)
    /* Dependency Injection */
    api(Lib.Di.DAGGER)
    kapt(Lib.Di.DAGGER_COMPILER)
}