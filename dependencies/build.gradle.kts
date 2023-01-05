plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
    maven("https://plugins.gradle.org/m2/")
}

gradlePlugin {
    plugins {
        register("appModule") {
            id = "AppModule"
            implementationClass = "gradle.plugins.AppModule"
        }
        register("commonModule") {
            id = "CommonModule"
            implementationClass = "gradle.plugins.CommonModule"
        }
        register("dataModule") {
            id = "DataModule"
            implementationClass = "gradle.plugins.DataModule"
        }
        register("featureModule") {
            id = "FeatureModule"
            implementationClass = "gradle.plugins.FeatureModule"
        }
        register("kotlinModule") {
            id = "KotlinModule"
            implementationClass = "gradle.plugins.KotlinModule"
        }
    }
}

dependencies {
    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.1.3")
//    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
//    implementation("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")
}
