/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {
  private const val TOOLS_BUILD = "7.4.1"
  private const val KT_LINT = "9.2.1"
  private const val SAFE_ARGS = "2.3.5"

  const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${TOOLS_BUILD}"
  const val KTLINT_GRADLE_PLUGIN = "org.jlleitschuh.gradle:ktlint-gradle:${KT_LINT}"
  const val SAFE_ARGS_GRADLE_PLUGIN =
    "androidx.navigation:navigation-safe-args-gradle-plugin:${SAFE_ARGS}"
  const val DAGGER_HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:2.45"
  const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
  const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
  const val ANDROID_LIBRARY_PLUGIN = "com.android.library"
  const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
  const val KOTLIN_PARCELABLE_PLUGIN = "kotlin-parcelize"
  const val KOTLIN_KAPT = "kotlin-kapt"
  const val DAGGER_HILT = "dagger.hilt.android.plugin"
  const val ktLint = "org.jlleitschuh.gradle.ktlint"
  const val SAFE_ARGS_KOTLIN = "androidx.navigation.safeargs.kotlin"
}

object Lib {
  object Kotlin {
    const val COROUTINE_VERSION = "1.6.4"
    private const val KTX_CORE_VERSION = "1.9.0"
    private const val KTX_CORE = "androidx.core:core-ktx:${KTX_CORE_VERSION}"
    private const val DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${COROUTINE_VERSION}"
    private const val COROUTINES_ANDROID =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINE_VERSION}"

    val list = listOf(KTX_CORE, DATE_TIME, COROUTINES, COROUTINES_ANDROID)
  }

  object Google {
    const val MATERIAL_DESIGN = "com.google.android.material:material:1.8.0"

    val list = listOf(
      MATERIAL_DESIGN,
    )

  }

  object Androidx {
    // Compose
    const val composeVersion = "1.4.2"
    private const val SPLASH_SCREEN_API = "androidx.core:core-splashscreen:1.0.0-beta01"
    private const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"

    val list = listOf(
      APP_COMPAT,
      SPLASH_SCREEN_API
    )

    object Compose {
      const val composeCompilerVersion = "1.4.2"
      const val bomVersion = "2023.01.00"

      const val COMPOSE_BOM = "androidx.compose:compose-bom:$bomVersion"
      private const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose"
      private const val CONSTRAINT_LAYOUT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:1.0.1"
      private const val COMPOSE_UI = "androidx.compose.ui:ui"
      private const val COMPOSE_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata"
      private const val COMPOSE_MATERIAL = "androidx.compose.material:material"
      private const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling-preview"
      private const val COMPOSE_DEBUG_TOOLING = "androidx.compose.ui:ui-tooling"
      private const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:2.5.3"

      val list = listOf(
        CONSTRAINT_LAYOUT_COMPOSE,
        COMPOSE_UI,
        ACTIVITY_COMPOSE,
        COMPOSE_LIVEDATA,
        COMPOSE_MATERIAL,
        COMPOSE_TOOLING,
        COMPOSE_DEBUG_TOOLING,
        COMPOSE_NAVIGATION,
      )
    }
  }


  object Accompanist {
    const val insets = "com.google.accompanist:accompanist-insets:0.28.0"
    const val pager = "com.google.accompanist:accompanist-pager:0.28.0"
    const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:0.28.0"
    const val systemuicontroller =
      "com.google.accompanist:accompanist-systemuicontroller:0.28.0"
    val list = listOf(insets, pager, pagerIndicators, systemuicontroller)
  }

  object Di {
    private const val DAGGER_VERSION = "2.45"
    const val hilt = "com.google.dagger:hilt-android:${DAGGER_VERSION}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${DAGGER_VERSION}"

//    const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"
  }

  object Paging {
    private const val PAGING_VERSION = "3.1.0"
    const val PAGING_3 = "androidx.paging:paging-runtime:${PAGING_VERSION}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:1.0.0-alpha14"
    const val PAGING_COMMON = "androidx.paging:paging-common-ktx:${PAGING_VERSION}"
  }

  object Room {
    private const val roomVersion = "2.5.0"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomPaging = "androidx.room:room-paging:2.4.1"
    const val testing = "androidx.room:room-testing:$roomVersion"
  }

  object Networking {
    const val KTOR = "io.ktor:ktor-client-android:1.6.7"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging-jvm:1.6.7"

    val ktorList = listOf(KTOR, KTOR_LOGGING)
  }

  object ThirdParty {
    private const val COIL_COMPOSE = "io.coil-kt:coil-compose:1.4.0"

    val list = listOf(COIL_COMPOSE)
  }

}

object UnitTesting {
  const val junit = "junit:junit:4.13.2"
  const val MOCKK = "io.mockk:mockk:1.13.4"
  const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Lib.Kotlin.COROUTINE_VERSION}"
  const val turbin = "app.cash.turbine:turbine:0.12.1"

  val list = listOf(junit, MOCKK, coroutineTest, turbin)
}

object DevDependencies {
  private const val LEAK_CANARY_VERSION = "2.10"
  const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LEAK_CANARY_VERSION}"
  private const val TIMBER_VERSION = "5.0.1"
  const val TIMBER = "com.jakewharton.timber:timber:${TIMBER_VERSION}"
  val debugList = listOf(LEAK_CANARY)
  val list = listOf(TIMBER)
}
