package gradle.plugins

import com.android.build.gradle.*
import implementationDependenciesFrom
import annotationProcessors
import implementationProjectsFrom
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppModule : BaseAndroidPlugin() {

  override fun apply(project: Project) {
    project.apply(plugin = BuildPlugins.ANDROID_APPLICATION_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    project.apply(plugin = BuildPlugins.KOTLIN_KAPT)
    project.apply(plugin = BuildPlugins.DAGGER_HILT)
    project.apply(plugin = BuildPlugins.ktLint)

    super.apply(project)

    project.implementationProjectsFrom(
      listOf(
        ":ui-onboarding",
        ":ui-authentication",
        ":navigator",
        ":data",
        ":domain",
        ":common",
        ":commonui",
      )
    )

    project.implementationDependenciesFrom(
      Lib.Androidx.list +
          Lib.Androidx.Compose.list +
          Lib.ThirdParty.list +
          Lib.Accompanist.list + roomImplDeps +
          Lib.Google.list + hiltImplDeps
    )

    project.annotationProcessors(
      hiltKapt + listOf(Lib.Room.roomCompiler)
    )

    project.extensions.getByType<AppExtension>().apply {
      configureAndroidAppBlock(project)
    }
  }


}

fun AppExtension.configureAndroidAppBlock(project: Project) {
  buildToolsVersion = (AppVersions.BUILD_TOOLS)
  compileSdkVersion = AppVersions.COMPILE_SDK

  defaultConfig {
    applicationId = (AppVersions.APPLICATION_ID)
    minSdk = (AppVersions.MIN_SDK)
    targetSdk = (AppVersions.TARGET_SDK)
    versionCode = (AppVersions.versionCode)
    versionName = (AppVersions.versionName)
    testInstrumentationRunner = ("android.support.test.runner.AndroidJUnitRunner")
    vectorDrawables.useSupportLibrary = true

    javaCompileOptions {
      annotationProcessorOptions {
        arguments += mapOf(
          "room.schemaLocation" to "${project.projectDir}/schemas",
          "room.incremental" to "true",
          "room.expandProjection" to "true"
        )
      }
    }
  }

  project.tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }

  signingConfigs {
    getByName("debug") {
      keyAlias = "praxis-debug"
      keyPassword = "utherNiC"
      storeFile = project.file("keystore/praxis-debug.jks")
      storePassword = "uRgeSCIt"
    }

    create("release") {
      keyAlias = "praxis-release"
      keyPassword = "ITHOmptI"
      storeFile = project.file("keystore/praxis-release.jks")
      storePassword = "PoTHatHR"
    }
  }

  buildTypes.apply {
    this.getByName("release").apply {
      isDebuggable = false
      versionNameSuffix = "-release"
      isMinifyEnabled = true
      isShrinkResources = true
      signingConfig = signingConfigs.getByName("release")
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
    this.getByName("debug").apply {
      isMinifyEnabled = false
      isShrinkResources = false
      isDebuggable = true
      versionNameSuffix = "-debug"
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }
  }

  buildFeatures.viewBinding = true
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

  dataBinding.isEnabled = true
}

val hiltKapt = listOf(
  Lib.Di.hiltCompiler,
  Lib.Di.hiltAndroidCompiler,
)

val hiltImplDeps = listOf(
  Lib.Di.hilt,
  Lib.Di.hiltNavigationCompose,
  Lib.Di.hiltViewModel,
)

val roomImplDeps = listOf(
  Lib.Room.roomKtx,
  Lib.Room.roomRuntime
)