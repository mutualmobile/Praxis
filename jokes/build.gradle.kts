plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    id("org.jlleitschuh.gradle.ktlint")
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":navigator"))
    implementation(project(":commonui"))
    api(Lib.Kotlin.KT_STD)

    /* Android Designing and layout */
    implementation(Lib.Android.CONSTRAINT_LAYOUT)
    implementation(Lib.Android.FRAGMENT)
    implementation(Lib.Android.MATERIAL_DESIGN)
    implementation(Lib.Android.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.Android.ACT_KTX)

    /*DI*/
    implementation(Lib.Di.DAGGER)
    kapt(Lib.Di.DAGGER_COMPILER)
    kaptTest(Lib.Di.DAGGER_COMPILER)

    /* Logger */
    api(Lib.Logger.TIMBER)

    /* Async */
    api(Lib.Async.COROUTINES)
    api(Lib.Async.COROUTINES_ANDROID)

}