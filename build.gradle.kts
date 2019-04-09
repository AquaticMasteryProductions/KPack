group = "com.ampro"
version = "0.0.0"
val artifactID = "kpack"

plugins {
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.0"
    id("org.jlleitschuh.gradle.ktlint") version "7.2.1"
}

repositories {
    jcenter()
}


subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        ruleSets.set(listOf(
            "/.editorconfig"
        ))
    }
}
