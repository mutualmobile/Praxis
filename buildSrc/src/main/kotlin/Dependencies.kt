/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {
  private const val TOOLS_BUILD = "7.3.0"
  private const val KT_LINT = "11.0.0"
  private const val SAFE_ARGS = "2.5.2"
  const val DAGGER_VERSION = "2.44"

  const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${TOOLS_BUILD}"
  const val KTLINT_GRADLE_PLUGIN = "org.jlleitschuh.gradle:ktlint-gradle:${KT_LINT}"
  const val SAFE_ARGS_GRADLE_PLUGIN =
    "androidx.navigation:navigation-safe-args-gradle-plugin:${SAFE_ARGS}"
  const val DAGGER_HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${DAGGER_VERSION}"
  const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Lib.Kotlin.KOTLIN_VERSION}"
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
    const val KOTLIN_VERSION = "1.7.0"
    private const val KTX_CORE_VERSION = "1.2.0"
    const val KOTLINX_DATETIME_VERSION = "0.4.0"
    const val COROUTINES_VERSION = "1.6.4"
    const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KOTLIN_VERSION}"
    private const val KTX_CORE = "androidx.core:core-ktx:${KTX_CORE_VERSION}"
    private const val DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:${KOTLINX_DATETIME_VERSION}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${COROUTINES_VERSION}"
    private const val COROUTINES_ANDROID =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINES_VERSION}"

    val list = listOf(KT_STD, KTX_CORE, DATE_TIME, COROUTINES, COROUTINES_ANDROID)
  }

  object Google {
    const val MATERIAL_DESIGN_VERSION = "1.6.1"
    const val MATERIAL_DESIGN = "com.google.android.material:material:${MATERIAL_DESIGN_VERSION}"

    val list = listOf(
      MATERIAL_DESIGN,
    )

  }

  object Androidx {
    // Compose
    const val COMPOSE_VERSION = "1.2.1"
    const val COMPOSE_COMPILER_VERSION = "1.2.0"
    const val SPLASH_SCREEN_VERSION = "1.0.0"
    const val APP_COMPAT_VERSION = "1.5.1"
    private const val SPLASH_SCREEN_API = "androidx.core:core-splashscreen:${SPLASH_SCREEN_VERSION}"
    private const val APP_COMPAT = "androidx.appcompat:appcompat:${APP_COMPAT_VERSION}"

    val list = listOf(
      APP_COMPAT,
      SPLASH_SCREEN_API
    )

    object Compose {
      const val NAVIGATION_COMPOSE_VERSION = "2.5.2"
      const val CONSTRAINT_LAYOUT_COMPOSE_VERSION = "1.0.1"

      private const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${COMPOSE_VERSION}"
      private const val CONSTRAINT_LAYOUT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:${CONSTRAINT_LAYOUT_COMPOSE_VERSION}"
      private const val COMPOSE_UI = "androidx.compose.ui:ui:${COMPOSE_VERSION}"
      private const val COMPOSE_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata:${COMPOSE_VERSION}"
      private const val COMPOSE_MATERIAL = "androidx.compose.material:material:${COMPOSE_VERSION}"
      private const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling-preview:${COMPOSE_VERSION}"
      private const val COMPOSE_DEBUG_TOOLING = "androidx.compose.ui:ui-tooling:${COMPOSE_VERSION}"
      private const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${NAVIGATION_COMPOSE_VERSION}"

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
    const val ACCOMPANIST_VERSION = "0.26.4-beta"

    const val insets = "com.google.accompanist:accompanist-insets:${ACCOMPANIST_VERSION}"
    const val pager = "com.google.accompanist:accompanist-pager:${ACCOMPANIST_VERSION}"
    const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:${ACCOMPANIST_VERSION}"
    const val systemuicontroller =
      "com.google.accompanist:accompanist-systemuicontroller:${ACCOMPANIST_VERSION}"
    val list = listOf(insets, pager, pagerIndicators, systemuicontroller)
  }

  object Di {
    const val HILT_COMPILER_VERSION = "1.0.0"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"

    const val hilt = "com.google.dagger:hilt-android:${BuildPlugins.DAGGER_VERSION}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${BuildPlugins.DAGGER_VERSION}"

    const val hiltCompiler = "androidx.hilt:hilt-compiler:${HILT_COMPILER_VERSION}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${HILT_NAVIGATION_COMPOSE_VERSION}"
  }

  object Paging {
    private const val PAGING_VERSION = "3.1.1"
    private const val PAGING_COMPOSE_VERSION = "1.0.0-alpha16"

    const val PAGING_3 = "androidx.paging:paging-runtime:${PAGING_VERSION}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${PAGING_COMPOSE_VERSION}"
    const val PAGING_COMMON = "androidx.paging:paging-common-ktx:${PAGING_VERSION}"
  }

  object Room {
    private const val roomVersion = "2.4.3"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomPaging = "androidx.room:room-paging:$roomVersion"
    const val testing = "androidx.room:room-testing:$roomVersion"
  }

  object Networking {
    private const val KTOR_VERSION = "2.1.2"
    const val KTOR = "io.ktor:ktor-client-android:${KTOR_VERSION}"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging-jvm:${KTOR_VERSION}"

    val ktorList = listOf(KTOR, KTOR_LOGGING)
  }

  object ThirdParty {
    private const val COIL_COMPOSE_VERSION = "2.2.2"
    private const val COIL_COMPOSE = "io.coil-kt:coil-compose:${COIL_COMPOSE_VERSION}"

    val list = listOf(COIL_COMPOSE)
  }

}

object UnitTesting {
  private const val JUNIT_VERSION = "4.13.2"
  private const val MOCKK_VERSION = "1.13.2"
  private const val TURBINE_VERSION = "0.11.0"

  const val junit = "junit:junit:${JUNIT_VERSION}"
  const val MOCKK = "io.mockk:mockk:${MOCKK_VERSION}"
  const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Lib.Kotlin.COROUTINES_VERSION}"
  const val turbine = "app.cash.turbine:turbine:${TURBINE_VERSION}"

  val list = listOf(junit, MOCKK, coroutineTest, turbine)
}

object DevDependencies {
  private const val LEAK_CANARY_VERSION = "2.9.1"
  private const val TIMBER_VERSION = "5.0.1"

  const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LEAK_CANARY_VERSION}"
  const val TIMBER = "com.jakewharton.timber:timber:${TIMBER_VERSION}"
  val debugList = listOf(LEAK_CANARY)
  val list = listOf(TIMBER)
}
