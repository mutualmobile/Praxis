// Manifest version information!

plugins {
    id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
    apply {
        from("variants.gradle.kts")
    }
}

android {
    compileSdk = ProjectProperties.COMPILE_SDK

    defaultConfig {
        applicationId = (ProjectProperties.APPLICATION_ID)
        minSdk = ProjectProperties.MIN_SDK
        targetSdk = ProjectProperties.TARGET_SDK
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
        exclude("LICENSE.txt")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Compose.COMPOSE_VERSION
    }
}

// Required for annotation processing plugins like Dagger
kapt {
    generateStubs = true
    correctErrorTypes = true
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    /*Kotlin*/
    api(Lib.Kotlin.KTX_CORE)

    /* Android Designing and layout */
    implementation(Lib.Android.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.Google.MATERIAL)

    /* Compose */
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Compose.TOOLING)
    implementation(Lib.Compose.ACTIVITY)
    implementation(Lib.Compose.RUNTIME)

    /*DI*/
    implementation(Lib.Di.HILT)
    implementation(Lib.Di.HILT_COMPILER)
    kapt(Lib.Di.ANDROIDX_HILT_COMPILER)

    /* Logger */
    api(Lib.Logger.TIMBER)

    /* Async */
    api(Lib.Async.COROUTINES)
    api(Lib.Async.COROUTINES_ANDROID)

    /*Testing*/
    testImplementation(TestLib.JUNIT)
    testImplementation(TestLib.CORE_TEST)
    testImplementation(TestLib.ANDROID_JUNIT)
    testImplementation(TestLib.ARCH_CORE)
    testImplementation(TestLib.MOCK_WEB_SERVER)
    testImplementation(TestLib.ROBO_ELECTRIC)
    testImplementation(TestLib.COROUTINES)
    testImplementation(TestLib.MOCKITO_CORE)
}
