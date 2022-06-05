pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "androidx.navigation" -> {
                    useModule("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
                }
            }
        }
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TMDB-PopularMovies"
include(":app")
