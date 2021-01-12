/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {

  const val TOOLS_BUILD_GRADLE =
    "com.android.tools.build:gradle:${Versions.RootLibVersion.TOOLS_BUILD}"
  const val KTLINT_GRADLE_PLUGIN =
    "org.jlleitschuh.gradle:ktlint-gradle:${Versions.RootLibVersion.KT_LINT}"
  const val SAFE_ARGS_GRADLE_PLUGIN =
    "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.RootLibVersion.SAFE_ARGS}"
  const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
  const val ANDROID_LIBRARY_PLUGIN = "com.android.library"
  const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
  const val KOTLIN_ANDROID_EXTENSIONS_PLUGIN = "kotlin-android-extensions"
  const val KOTLIN_KAPT = "kotlin-kapt"
  const val KOTLINX_SERIALIZATION = "kotlinx-serialization"
  const val SAFE_ARGS_PLUGIN = "androidx.navigation.safeargs.kotlin"
}

object Lib {

  object Kotlin {
    const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.RootLibVersion.KOTLIN}"
    const val KTX_CORE = "androidx.core:core-ktx:${Versions.LibVersion.KTX_CORE}"
  }

  object Android {
    const val CONSTRAINT_LAYOUT =
      "androidx.constraintlayout:constraintlayout:${Versions.LibVersion.CONSTRAINT_LAYOUT}"
    const val MATERIAL_DESIGN =
      "com.google.android.material:material:${Versions.LibVersion.MATERIAL_DESIGN}"
    const val LIFECYCLE_VIEWMODEL_KTX =
      "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LibVersion.LIFECYCLE_VIEWMODEL_KTX}"
    const val FRAGMENT =
      "androidx.fragment:fragment-ktx:${Versions.LibVersion.FRAGMENT_VERSION}"

    object Navigation {
      const val FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.LibVersion.NAVIGATION_VERSION}"
      const val UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${Versions.LibVersion.NAVIGATION_VERSION}"
      const val RUN_TIME =
        "androidx.navigation:navigation-runtime:${Versions.LibVersion.NAVIGATION_VERSION}"
    }
  }

  object Database {
    const val ROOM = "androidx.room:room-runtime:${Versions.LibVersion.ROOM}"
    const val ROOM_DATABASE_COMPILER = "androidx.room:room-compiler:${Versions.LibVersion.ROOM}"
  }

  object Di {
    const val DAGGER = "com.google.dagger:dagger:${Versions.LibVersion.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.LibVersion.DAGGER}"
    const val DAGGER_ANDROID =
      "com.google.dagger:dagger-android-support:${Versions.LibVersion.DAGGER}"
    const val DAGGER_PROCESSOR =
      "com.google.dagger:dagger-android-processor:${Versions.LibVersion.DAGGER}"
  }

  object Async {
    const val COROUTINES =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.LibVersion.COROUTINES}"
    const val COROUTINES_ANDROID =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.LibVersion.COROUTINES}"
  }

  object Networking {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.LibVersion.RETROFIT}"
    const val RETROFIT_GSON =
      "com.squareup.retrofit2:converter-gson:${Versions.LibVersion.RETROFIT}"
    const val OK_HTTP =
      "implementation \"com.squareup.okhttp3:okhttp:${Versions.LibVersion.RETROFIT_LOGGING}"
    const val LOGGING =
      "com.squareup.okhttp3:logging-interceptor:${Versions.LibVersion.RETROFIT_LOGGING}"
  }

  object Logger {
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.LibVersion.TIMBER}"
  }
}

object TestLib {

  const val COROUTINES =
    "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.LibVersion.COROUTINES}"
  const val ROBO_ELECTRIC = "org.robolectric:robolectric:${Versions.TestLibVersion.ROBO_ELECTRIC}"
  const val MOCK_WEB_SERVER =
    "com.squareup.okhttp3:mockwebserver:${Versions.TestLibVersion.MOCK_WEB_SERVER}"
  const val CORE_TEST = "androidx.test:core-ktx:${Versions.TestLibVersion.CORE_TEST}"
  const val JUNIT = "junit:junit:${Versions.TestLibVersion.JUNIT}"
  const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.TestLibVersion.ANDROID_JUNIT}"
  const val ARCH_CORE = "androidx.arch.core:core-testing:${Versions.TestLibVersion.ARCH_CORE}"
  const val MOCKITO_CORE = "org.mockito:mockito-core:3.3.3"
}

object DebugLib {
  const val LEAK_CANARY =
    "com.squareup.leakcanary:leakcanary-android:${Versions.DebugLibVersion.LEAK_CANARY}"
}
