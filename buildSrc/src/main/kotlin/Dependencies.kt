/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {
  private const val TOOLS_BUILD = "7.3.1"
  private const val SAFE_ARGS = "2.3.5"
  private const val KT_LINT = "11.0.0"
  private const val TWITTER_COMPOSE_RULES_VER = "0.0.22"
  const val KOTLINTER_PLUGIN_VERSION = "3.12.0"

  const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${TOOLS_BUILD}"
  const val KTLINT_GRADLE_PLUGIN = "org.jlleitschuh.gradle:ktlint-gradle:${KT_LINT}"
  const val SAFE_ARGS_GRADLE_PLUGIN =
    "androidx.navigation:navigation-safe-args-gradle-plugin:${SAFE_ARGS}"
  const val DAGGER_HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Lib.Di.DAGGER_VERSION}"
  const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20"
  const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
  const val ANDROID_LIBRARY_PLUGIN = "com.android.library"
  const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
  const val KOTLIN_PARCELABLE_PLUGIN = "kotlin-parcelize"
  const val KOTLIN_KAPT = "kotlin-kapt"
  const val DAGGER_HILT = "dagger.hilt.android.plugin"
  const val ktLint = "org.jlleitschuh.gradle.ktlint"
  const val SAFE_ARGS_KOTLIN = "androidx.navigation.safeargs.kotlin"
  const val TWITTER_COMPOSE_RULES = "com.twitter.compose.rules:ktlint:$TWITTER_COMPOSE_RULES_VER"
  const val KOTLINTER = "org.jmailen.kotlinter"
  const val ANDROID_TEST = "com.android.test"
  const val JETBRAINS_KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
}

object Lib {
  object Kotlin {
    const val KOTLIN_VERSION = "1.7.20"
    private const val KTX_CORE_VERSION = "1.9.0"
    private const val COROUTINES_VERSION = "1.6.4"
    const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KOTLIN_VERSION}"
    private const val KTX_CORE = "androidx.core:core-ktx:${KTX_CORE_VERSION}"
    private const val DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.2"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${COROUTINES_VERSION}"
    private const val COROUTINES_ANDROID =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINES_VERSION}"

    val list = listOf(KT_STD, KTX_CORE, DATE_TIME, COROUTINES, COROUTINES_ANDROID)
  }

  object Google {
    const val MATERIAL_DESIGN = "com.google.android.material:material:1.4.0"

    val list = listOf(
      MATERIAL_DESIGN,
    )

  }

  object Androidx {
    // Compose
    const val composeVersion = "1.3.2"
    private const val SPLASH_SCREEN_API = "androidx.core:core-splashscreen:1.0.0"
    private const val APP_COMPAT = "androidx.appcompat:appcompat:1.5.1"

    val list = listOf(
      APP_COMPAT,
      SPLASH_SCREEN_API
    )

    object Compose {

      private const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${composeVersion}"
      private const val CONSTRAINT_LAYOUT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:1.0.0"
      private const val COMPOSE_UI = "androidx.compose.ui:ui:${composeVersion}"
      private const val COMPOSE_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata:${composeVersion}"
      private const val COMPOSE_MATERIAL = "androidx.compose.material:material:1.3.1"
      private const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling-preview:${composeVersion}"
      private const val COMPOSE_DEBUG_TOOLING = "androidx.compose.ui:ui-tooling:${composeVersion}"
      private const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:2.5.0-alpha01"

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
    private const val ACCOMPANIST_VERSION = "0.27.0"
    const val insets = "com.google.accompanist:accompanist-insets:$ACCOMPANIST_VERSION"
    const val pager = "com.google.accompanist:accompanist-pager:$ACCOMPANIST_VERSION"
    const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$ACCOMPANIST_VERSION"
    const val systemuicontroller =
      "com.google.accompanist:accompanist-systemuicontroller:$ACCOMPANIST_VERSION"
    val list = listOf(insets, pager, pagerIndicators, systemuicontroller)
  }

  object Di {
    const val DAGGER_VERSION = "2.43"
    const val hilt = "com.google.dagger:hilt-android:${DAGGER_VERSION}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${DAGGER_VERSION}"

    const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
  }

  object Paging {
    private const val PAGING_VERSION = "3.1.0"
    const val PAGING_3 = "androidx.paging:paging-runtime:${PAGING_VERSION}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:1.0.0-alpha14"
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
  private const val ANDROID_JUNIT_VERSION = "1.1.4"
  private const val ESPRESSO_VERSION = "3.5.0"
  private const val UI_AUTOMATOR_VERSION = "2.2.0"
  private const val BENCHMARK_VERSION = "1.2.0-alpha08"
  private const val PROFILE_INSTALLER_VERSION = "1.2.1"

  const val junit = "junit:junit:4.13.2"
  const val MOCKK = "io.mockk:mockk:1.10.5"
  const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
  const val turbin = "app.cash.turbine:turbine:0.7.0"
  const val ESPRESSO = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
  const val ANDROID_JUNIT = "androidx.test.ext:junit:$ANDROID_JUNIT_VERSION"
  const val UI_AUTOMATOR = "androidx.test.uiautomator:uiautomator:$UI_AUTOMATOR_VERSION"
  const val BENCHMARK = "androidx.benchmark:benchmark-macro-junit4:$BENCHMARK_VERSION"
  const val PROFILE_INSTALLER = "androidx.profileinstaller:profileinstaller:$PROFILE_INSTALLER_VERSION"

  val list = listOf(junit, MOCKK, coroutineTest, turbin)
}

object DevDependencies {
  private const val LEAK_CANARY_VERSION = "2.8.1"
  const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LEAK_CANARY_VERSION}"
  private const val TIMBER_VERSION = "4.7.1"
  const val TIMBER = "com.jakewharton.timber:timber:${TIMBER_VERSION}"
  val debugList = listOf(LEAK_CANARY)
  val list = listOf(TIMBER)
}
