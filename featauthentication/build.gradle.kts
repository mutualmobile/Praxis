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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER
    }
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add( "/META-INF/{AL2.0,LGPL2.1}")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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

    api(Lib.Android.COMPOSE_UI)
    api(Lib.Android.COIL_COMPOSE)
    api(Lib.Android.COMPOSE_MATERIAL)
    api(Lib.Android.COMPOSE_UI)
    api(Lib.Android.COMPOSE_TOOLING)
    debugApi(Lib.Android.DEBUG_TOOLING)
    api(Lib.Android.ACT_COMPOSE)

    api(Lib.Android.appCompat)
    api(Lib.Kotlin.KTX_CORE)

    api(Lib.Android.ACCOMPANIST_INSETS)

    /*DI*/
    api(Lib.Di.hilt)
    api(Lib.Di.hiltNavigationCompose)
    api(Lib.Di.viewmodel)

    kapt(Lib.Di.hiltCompiler)
    kapt(Lib.Di.hiltAndroidCompiler)

    /* Logger */
    api(Lib.Logger.TIMBER)
    /* Async */
    api(Lib.Async.COROUTINES)
    api(Lib.Async.COROUTINES_ANDROID)

}