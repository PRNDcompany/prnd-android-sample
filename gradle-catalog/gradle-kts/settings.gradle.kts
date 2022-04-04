rootProject.name = "groovy"
include(":app")

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            from(files("../dependencies.toml"))
        }
    }
}
