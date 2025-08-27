pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

rootProject.name = "RichKuikly"
include(":androidApp")
include(":shared")