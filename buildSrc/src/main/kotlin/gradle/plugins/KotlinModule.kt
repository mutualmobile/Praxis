package gradle.plugins

import org.gradle.api.Project
import implementationDependenciesFrom
import org.gradle.kotlin.dsl.apply

class KotlinModule : BaseAndroidPlugin() {

  override fun apply(project: Project) {
    project.apply(plugin = BuildPlugins.JAVA_LIB)
    project.apply(plugin = BuildPlugins.KOTLIN_JVM)

    project.implementationDependenciesFrom(
      listOf(
        Lib.Paging.PAGING_COMMON
      ) + Lib.Kotlin.kotlinList
    )
    super.apply(project)
  }
}