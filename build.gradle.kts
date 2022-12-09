// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id(BuildPlugins.KOTLINTER) version BuildPlugins.KOTLINTER_PLUGIN_VERSION apply true
}

buildscript {
  repositories {
    google()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath(BuildPlugins.TOOLS_BUILD_GRADLE)
    classpath(BuildPlugins.SAFE_ARGS_GRADLE_PLUGIN)
    classpath(BuildPlugins.DAGGER_HILT_PLUGIN)
    classpath(BuildPlugins.KOTLIN_GRADLE_PLUGIN)
    classpath(kotlin("serialization", version = Lib.Kotlin.KOTLIN_VERSION))
    classpath(BuildPlugins.KTLINT_GRADLE_PLUGIN)
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    classpath(BuildPlugins.TWITTER_COMPOSE_RULES)
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

subprojects {
  apply(plugin = BuildPlugins.KOTLINTER)
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

tasks.register("clean")
    .configure {
      delete(rootProject.buildDir)
    }

apply(from = teamPropsFile("git-hooks.gradle.kts"))

fun teamPropsFile(propsFile: String): File {
  val teamPropsDir = file("team-props")
  return File(teamPropsDir, propsFile)
}

tasks.register("check").configure {
  dependsOn("installKotlinterPrePushHook")
}

kotlinter {
  ignoreFailures = false
  reporters = arrayOf("checkstyle", "html", "plain")
  experimentalRules = true
  disabledRules = emptyArray()
  // disabledRules = arrayOf("experimental:argument-list-wrapping", "no-wildcard-imports")
}