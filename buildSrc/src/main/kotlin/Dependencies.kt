/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {
    private const val TOOLS_BUILD = "7.1.0-alpha05"
    private const val KT_LINT = "9.2.1"
    private const val DAGGER_HILT = "2.38.1"

    const val TOOLS_BUILD_GRADLE =
            "com.android.tools.build:gradle:${TOOLS_BUILD}"
    const val KTLINT_GRADLE_PLUGIN =
            "org.jlleitschuh.gradle:ktlint-gradle:${KT_LINT}"
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${DAGGER_HILT}"
    const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
    const val ANDROID_LIBRARY_PLUGIN = "com.android.library"
    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val DAGGER_HILT_PLUGIN = "dagger.hilt.android.plugin"
    const val KOTLIN_PARCELABLE_PLUGIN = "kotlin-parcelize"
    const val KOTLIN_KAPT = "kotlin-kapt"
}

object Lib {

    object Kotlin {
        const val KOTLIN_VERSION = "1.5.10"
        private const val KTX_CORE_VERSION = "1.5.0"

        const val KTX_CORE = "androidx.core:core-ktx:${KTX_CORE_VERSION}"
    }

    object Android {
        private const val LIFECYCLE_VIEWMODEL_KTX_VERSION = "2.3.1"

        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE_VIEWMODEL_KTX_VERSION}"
    }

    object Google {
        private const val MATERIAL_VERSION = "1.5.0-alpha01"

        const val MATERIAL =
                "com.google.android.material:material:${MATERIAL_VERSION}"
    }

    object Compose {

        const val COMPOSE_VERSION = "1.0.0"
        private const val ACTIVITY_COMPOSE_VERSION = "1.3.0"

        const val UI = "androidx.compose.ui:ui:${COMPOSE_VERSION}"
        const val MATERIAL =
                "androidx.compose.material:material:${COMPOSE_VERSION}"
        const val TOOLING =
                "androidx.compose.ui:ui-tooling:${COMPOSE_VERSION}"
        const val ACTIVITY =
                "androidx.activity:activity-compose:${ACTIVITY_COMPOSE_VERSION}"
        const val RUNTIME =
                "androidx.compose.runtime:runtime:${COMPOSE_VERSION}"
    }

    object Database {
        private const val ROOM_VERSION = "2.2.5"
        const val ROOM = "androidx.room:room-runtime:${ROOM_VERSION}"
        const val ROOM_DATABASE_COMPILER = "androidx.room:room-compiler:${ROOM_VERSION}"
    }

    object Di {
        private const val DAGGER_VERSION = "2.38.1"
        const val DAGGER = "com.google.dagger:dagger:${DAGGER_VERSION}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${DAGGER_VERSION}"
        const val DAGGER_ANDROID = "com.google.dagger:dagger-android-support:${DAGGER_VERSION}"
        const val DAGGER_PROCESSOR = "com.google.dagger:dagger-android-processor:${DAGGER_VERSION}"

        const val HILT =
                "com.google.dagger:hilt-android:${DAGGER_VERSION}"
        const val HILT_COMPILER =
                "com.google.dagger:hilt-android-compiler:${DAGGER_VERSION}"
        const val ANDROIDX_HILT_COMPILER =
                "androidx.hilt:hilt-compiler:${DAGGER_VERSION}"
    }

    object Async {
        private const val COROUTINES_VERSION = "1.5.1"

        const val COROUTINES =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${COROUTINES_VERSION}"
        const val COROUTINES_ANDROID =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINES_VERSION}"
    }

    object Networking {
        private const val RETROFIT_VERSION = "2.9.0"
        private const val OKHTTP_LOGGING = "4.7.2"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${RETROFIT_VERSION}"
        const val RETROFIT_GSON =
                "com.squareup.retrofit2:converter-gson:${RETROFIT_VERSION}"
        const val LOGGING =
                "com.squareup.okhttp3:logging-interceptor:${OKHTTP_LOGGING}"
    }

    object Serialization {
        private const val GSON_VERSION = "2.8.6"
        const val GSON = "com.google.code.gson:gson:${GSON_VERSION}"
    }

    object Logger {
        private const val TIMBER_VERSION = "4.7.1"
        const val TIMBER = "com.jakewharton.timber:timber:${TIMBER_VERSION}"
    }
}

object TestLib {
    private const val COROUTINES_VERSION = "1.3.7"
    private const val ANDROID_JUNIT_VERSION = "1.0.0"
    private const val ROBO_ELECTRIC_VERSION = "4.3"
    private const val ARCH_CORE_VERSION = "2.1.0"
    private const val MOCK_WEB_SERVER_VERSION = "4.7.2"
    private const val CORE_TEST_VERSION = "1.2.0"
    private const val JUNIT_VERSION = "4.13"

    const val COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${COROUTINES_VERSION}"
    const val ROBO_ELECTRIC = "org.robolectric:robolectric:${ROBO_ELECTRIC_VERSION}"
    const val MOCK_WEB_SERVER =
            "com.squareup.okhttp3:mockwebserver:${MOCK_WEB_SERVER_VERSION}"
    const val CORE_TEST = "androidx.test:core-ktx:${CORE_TEST_VERSION}"
    const val JUNIT = "junit:junit:${JUNIT_VERSION}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${ANDROID_JUNIT_VERSION}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${ARCH_CORE_VERSION}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:3.3.3"
}

object DebugLib {
    private const val LEAK_CANARY_VERSION = "2.3"
    const val LEAK_CANARY =
            "com.squareup.leakcanary:leakcanary-android:${LEAK_CANARY_VERSION}"
}
