buildscript {
    dependencies {
        classpath(deps.gradle)
        classpath(deps.kotlin)
        classpath(deps.hilt.gradle)
    }
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
