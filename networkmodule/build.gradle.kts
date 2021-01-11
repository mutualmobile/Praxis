plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
}

android {
  compileSdkVersion(ProjectProperties.COMPILE_SDK)

  defaultConfig {
    applicationId = (ProjectProperties.APPLICATION_ID)
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

  /* Networking */
  api(Lib.Networking.RETROFIT)
  api(Lib.Networking.LOGGING)
  api(Lib.Networking.RETROFIT_KOTLINX)
  implementation ("com.google.code.gson:gson:2.8.6")
  //debugApi(Lib.Networking.CHUCKER)
  //releaseApi(Lib.Networking.CHUCKER_RELEASE)

  /*Kotlin*/
  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)

    //Rx
    implementation ("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar")
//
//    testImplementation "junit:junit:4.12"

}
repositories {
  mavenCentral()
}
