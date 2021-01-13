plugins {
  id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
}

android {
  compileSdkVersion(ProjectProperties.COMPILE_SDK)

  defaultConfig {
    minSdkVersion(ProjectProperties.MIN_SDK)
    targetSdkVersion(ProjectProperties.TARGET_SDK)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {

  /*Kotlin*/
  api(Lib.Kotlin.KT_STD)
  api(Lib.Async.COROUTINES)

  /* Networking */
  api(Lib.Networking.RETROFIT)
  api(Lib.Networking.RETROFIT_GSON)
  api(Lib.Networking.LOGGING)

  api(Lib.Serialization.GSON)

  /* Dependency Injection */
  api(Lib.Di.DAGGER)
  kapt(Lib.Di.DAGGER_PROCESSOR)
  kapt(Lib.Di.DAGGER_COMPILER)
}