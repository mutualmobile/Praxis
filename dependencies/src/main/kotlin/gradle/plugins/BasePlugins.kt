package gradle.plugins

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import implementationDependenciesFrom
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType

open class BaseAndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all {
            when (this) {
                is JavaPlugin -> {
                    project.extensions.getByType<JavaPluginExtension>().apply {
                        configureJava()
                    }
                }
                is LibraryPlugin -> {
                    project.configureRepositories()
                        .implementationDependenciesFrom(Lib.Kotlin.androidList)
                }
                is AppPlugin -> {
                    project.configureRepositories()
                        .implementationDependenciesFrom(Lib.Kotlin.androidList)
                }
            }
        }
    }
}