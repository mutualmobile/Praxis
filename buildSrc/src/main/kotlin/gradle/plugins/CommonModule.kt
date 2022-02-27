package gradle.plugins

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import implementationDependenciesFrom
import annotationProcessors
import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.getByType

class CommonModule : BaseAndroidPlugin() {
  override fun apply(project: Project) {
    featureLibPlugins(project)
    super.apply(project)

    dependencies(project)

    project.extensions.getByType<LibraryExtension>().apply {
      configureAndroidLibraryBlock(project)
    }
  }

  private fun dependencies(project: Project) {
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