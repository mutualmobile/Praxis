package gradle.plugins

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import implementationProjectsFrom
import implementationDependenciesFrom
import annotationProcessors
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class FeatureModule : BaseAndroidPlugin() {
  override fun apply(project: Project) {
    featureLibPlugins(project)
    super.apply(project)

    dependencies(project)

    project.extensions.getByType<LibraryExtension>().apply {
      configureAndroidLibraryBlock(project)
    }
  }

  private fun dependencies(project: Project) {
    project.implementationProjectsFrom(
      listOf(
        ":navigator",
        ":data",
        ":domain",
        ":common",
      )
    )

    project.implementationDependenciesFrom(
      Lib.Androidx.list +
          Lib.Androidx.Compose.list +
          Lib.ThirdParty.list +
          Lib.Accompanist.list +
          Lib.Google.list +
          hiltImplDeps
    )

    project.annotationProcessors(
      hiltKapt
    )
  }

  private fun featureLibPlugins(project: Project) {
    project.apply(plugin = BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_KAPT)
    project.apply(plugin = BuildPlugins.DAGGER_HILT)
    project.apply(plugin = BuildPlugins.ktLint)
  }
}

fun LibraryExtension.configureAndroidLibraryBlock(project: Project) {
  compileSdkVersion = AppVersions.COMPILE_SDK

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

  buildFeatures.compose = true

  composeOptions {
    kotlinCompilerExtensionVersion = Lib.Androidx.composeVersion
  }
  packagingOptions {
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  project.tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }
}
