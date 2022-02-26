plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdk = AppVersions.COMPILE_SDK

    defaultConfig {
        minSdk = (AppVersions.MIN_SDK)
        targetSdk = (AppVersions.TARGET_SDK)
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
        kotlinCompilerExtensionVersion = Lib.Androidx.composeVersion
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


    Lib.Androidx.list.forEach(::implementation)
    Lib.Androidx.Compose.list.forEach(::implementation)
    Lib.ThirdParty.list.forEach(::implementation)
    Lib.Accompanist.list.forEach(::implementation)
    Lib.Google.list.forEach(::implementation)
    Lib.Kotlin.list.forEach(::implementation)

    /*DI*/
    implementation(Lib.Di.hilt)
    implementation(Lib.Di.hiltNavigationCompose)
    implementation(Lib.Di.viewmodel)
    kapt(Lib.Di.hiltCompiler)
    kapt(Lib.Di.hiltAndroidCompiler)

    // Room
    implementation(Lib.Room.roomKtx)
    implementation(Lib.Room.roomRuntime)
    kapt(Lib.Room.roomCompiler)
    testImplementation(Lib.Room.testing)

    UnitTesting.list.forEach(::testImplementation)
    DevDependencies.debugList.forEach(::debugImplementation)
    DevDependencies.list.forEach(::implementation)
}