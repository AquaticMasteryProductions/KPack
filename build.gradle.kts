plugins {
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
    id("org.jetbrains.dokka") version "0.9.18"
    id("org.jlleitschuh.gradle.ktlint") version "7.2.1"
    id("kotlin-multiplatform") version "1.3.20"
    id("kotlinx-serialization") version "1.3.20"
}

allprojects {
    group = "com.ampro"
    version = "0.0.0"
}

subprojects {
    repositories {
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://kotlin.bintray.com/kotlin-eap")
        jcenter()
    }

    val fullPath = "${rootProject.name}${project.path.replace(":", "-")}"

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    // will only run in subprojects with the maven-publish plugin already applied
    pluginManager.withPlugin("maven-publish") {
        afterEvaluate {
            publishing.publications.filterIsInstance<MavenPublication>().forEach {
                // replace project names in artifact with their module paths, ie core-jvm becomes strife-core-jvm
                it.artifactId = it.artifactId.replace(name, fullPath)
            }
        }
    }
}

bintray {
    user = "jono"
    System.getenv("BINTRAY_KEY")?.let { key = it }
    //setPublications("KPack")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "HighFriction"
        name = rootProject.name
        //licenses = listOf("Apache-2.0")
        //version.name = project.version.toString()
    })
}
