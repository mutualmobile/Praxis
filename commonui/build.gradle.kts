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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc02"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

// Required for annotation processing plugins like Dagger
kapt {
    generateStubs = true
    correctErrorTypes = true
}

dependencies {
    /*Kotlin*/
    implementation(Lib.Kotlin.KT_STD)
    implementation(Lib.Kotlin.KTX_CORE)
    /* Android Designing and layout */
    implementation(Lib.Android.CONSTRAINT_LAYOUT)
    implementation(Lib.Android.FRAGMENT)
    implementation(Lib.Android.MATERIAL_DESIGN)
    implementation(Lib.Android.APPCOMPAT)
    implementation(Lib.Android.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.Android.ACT_KTX)
    implementation(Lib.Android.COMPOSE_UI)
    implementation(Lib.Android.COIL_COMPOSE)
    implementation(Lib.Android.COMPOSE_MATERIAL)
    debugImplementation(Lib.Android.COMPOSE_TOOLING)
    implementation(Lib.Android.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.Android.ACT_COMPOSE)

    /* Dependency Injection */
    api(Lib.Di.DAGGER)
    kapt(Lib.Di.DAGGER_COMPILER)

    androidTestImplementation(Lib.Android.UI_TEST_JUNIT)

}