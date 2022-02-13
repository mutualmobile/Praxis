// Manifest version information!

plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.DAGGER_HILT)
  id(BuildPlugins.ktLint)
}

// def preDexEnabled = "true" == System.getProperty("pre-dex", "true")

android {
  compileSdk = (AppVersions.COMPILE_SDK)

  defaultConfig {
    applicationId = (AppVersions.APPLICATION_ID)
    minSdk = (AppVersions.MIN_SDK)
    targetSdk = (AppVersions.TARGET_SDK)
    versionCode = AppVersions.versionCode
    versionName = AppVersions.versionName
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true

    javaCompileOptions {
      annotationProcessorOptions {
        arguments += mapOf(
          "room.schemaLocation" to "$projectDir/schemas",
          "room.incremental" to "true",
          "room.expandProjection" to "true"
        )
      }
    }
  }

  signingConfigs {

    getByName("debug") {
      keyAlias = "praxis-debug"
      keyPassword = "utherNiC"
      storeFile = file("keystore/praxis-debug.jks")
      storePassword = "uRgeSCIt"
    }

    create("release") {
      keyAlias = "praxis-release"
      keyPassword = "ITHOmptI"
      storeFile = file("keystore/praxis-release.jks")
      storePassword = "PoTHatHR"
    }

  }
  buildTypes {
    getByName("release") {
      isDebuggable = false
      versionNameSuffix = "-release"

      isMinifyEnabled = true
      isShrinkResources = true

      proguardFiles(
        getDefaultProguardFile("proguard-android.txt"), "proguard-common.txt",
        "proguard-specific.txt"
      )
      signingConfig = signingConfigs.getByName("release")
    }
    getByName("debug") {
      isDebuggable = true
      versionNameSuffix = "-debug"
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }
  }

  buildFeatures {
    dataBinding = true
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


hilt {
  // The Hilt configuration option 'enableTransformForLocalTests'
  // is no longer necessary when com.android.tools.build:gradle:4.2.0+ is used.
  // enableTransformForLocalTests = true
  enableAggregatingTask = true

  // see
  // https://github.com/google/dagger/issues/1991
  // https://github.com/google/dagger/issues/970
  enableExperimentalClasspathAggregation = true
}

dependencies {
  

  Lib.Androidx.list.forEach(::api)
  Lib.Androidx.Compose.list.forEach(::api)
  Lib.ThirdParty.list.forEach(::api)
  Lib.Accompanist.list.forEach(::api)
  Lib.Google.list.forEach(::api)
  Lib.Kotlin.list.forEach(::api)

  api(project(":ui-onboarding"))
  api(project(":ui-authentication"))
  api(project(":navigator"))
  api(project(":data"))
  api(project(":domain"))
  api(project(":common"))
  api(project(":commonui"))

  /*DI*/
  api(Lib.Di.hilt)
  api(Lib.Di.hiltNavigationCompose)
  api(Lib.Di.viewmodel)
  kapt(Lib.Di.hiltCompiler)
  kapt(Lib.Di.hiltAndroidCompiler)

  // Room
  api(Lib.Room.roomKtx)
  api(Lib.Room.roomRuntime)
  add("kapt", Lib.Room.roomCompiler)
  testApi(Lib.Room.testing)

  UnitTesting.list.forEach(::testApi)
  DevDependencies.debugList.forEach(::debugApi)
  DevDependencies.list.forEach(::api)
}
