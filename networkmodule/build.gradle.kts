plugins {
  id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
}

android {
  compileSdkVersion(ProjectProperties.COMPILE_SDK)

  defaultConfig {
    minSdkVersion(ProjectProperties.MIN_SDK)
    targetSdkVersion(ProjectProperties.TARGET_SDK)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  androidExtensions {
    isExperimental = true
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)
  implementation(Lib.Android.LIFECYCLE_VIEWMODEL_KTX)

  /* Networking */
  api(Lib.Networking.RETROFIT)
  api(Lib.Networking.RETROFIT_GSON)
  api(Lib.Networking.LOGGING)
  implementation("com.google.code.gson:gson:2.8.6")

  /*Kotlin*/
  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)

  //Rx
  implementation("com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar")

}
repositories {
  mavenCentral()
}
