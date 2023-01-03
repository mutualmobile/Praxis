package gradle.plugins

import annotationProcessors
import com.android.build.gradle.LibraryExtension
import implementationDependenciesFrom
import implementationProjectsFrom
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class DataModule : BaseAndroidPlugin() {
    override fun apply(project: Project) {
        appPlugins(project)
        super.apply(project)

        dependencies(project)
    }

    private fun dependencies(project: Project) {
        project.implementationProjectsFrom(
            listOf(
                ":domain",
                ":common",
            )
        )

        project.implementationDependenciesFrom(
            roomImplDeps + hiltImplDeps + Lib.Kotlin.androidList + Lib.Kotlin.kotlinList + Lib.Networking.ktorList
        )

        project.annotationProcessors(
            hiltKapt + listOf(Lib.Room.roomCompiler)
        )

        project.extensions.getByType<LibraryExtension>().apply {
            configureAndroidLibraryBlock(project)
        }
    }

    private fun appPlugins(project: Project) {
        project.apply(plugin = BuildPlugins.KOTLIN_ANDROID_PLUGIN)
        project.apply(plugin = BuildPlugins.ANDROID_LIBRARY_PLUGIN)
        project.apply(plugin = BuildPlugins.KOTLIN_KAPT)
        project.apply(plugin = BuildPlugins.DAGGER_HILT)
        project.apply(plugin = BuildPlugins.ktLint)
    }
}