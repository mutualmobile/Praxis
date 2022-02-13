plugins {
  id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.DAGGER_HILT)
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
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

dependencies {
  implementation(project(":common"))
  implementation(project(":domain"))

  Lib.Kotlin.list.forEach(::implementation)
  Lib.Networking.ktorList.forEach(::implementation)
  /*DI*/
  implementation(Lib.Di.hilt)
  implementation(Lib.Di.hiltNavigationCompose)
  implementation(Lib.Di.viewmodel)
  kapt(Lib.Di.hiltCompiler)
  kapt(Lib.Di.hiltAndroidCompiler)

  // Room
  implementation(Lib.Room.roomKtx)
  implementation(Lib.Room.roomRuntime)
  add("kapt", Lib.Room.roomCompiler)
  testImplementation(Lib.Room.testing)

  UnitTesting.list.forEach(::testImplementation)
  DevDependencies.debugList.forEach(::debugImplementation)
  DevDependencies.list.forEach(::implementation)
}