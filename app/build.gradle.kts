// Manifest version information!

plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.DAGGER_HILT)
  id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
  apply {
    from("variants.gradle.kts")
  }
}

// def preDexEnabled = "true" == System.getProperty("pre-dex", "true")

android {
  compileSdk = (ProjectProperties.COMPILE_SDK)

  defaultConfig {
    applicationId = (ProjectProperties.APPLICATION_ID)
    minSdk = (ProjectProperties.MIN_SDK)
    targetSdk = (ProjectProperties.TARGET_SDK)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }

  packagingOptions {
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
  }

  buildFeatures {
    dataBinding = true
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
  implementation(project(":data"))
  implementation(project(":domain"))
  /*Kotlin*/
  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)

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

  /* PAGING */
  implementation(Lib.Paging.PAGING_3)

  /* GLIDE */
  implementation(Lib.Glide.GLIDE)
  kapt(Lib.Glide.GLIDE_COMPILER)

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
  testImplementation(TestLib.MOCKK)
}
