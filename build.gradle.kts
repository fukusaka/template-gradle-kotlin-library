plugins {
    kotlin("jvm") version "1.6.0"
    id("maven-publish")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        register<MavenPublication>("Jar") {
            from(components["java"])
            artifactId = "hello"
        }
    }

    repositories {
        val uploadMavenUser = project.findPropertyAsString("uploadMavenUser")
        val uploadMavenPassword = project.findPropertyAsString("uploadMavenPassword")

        if (uploadMavenUser != null && uploadMavenPassword != null) {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/fukusaka/template-gradle-kotlin-library")
                credentials {
                    username = uploadMavenUser
                    password = uploadMavenPassword
                }
            }
        }
    }
}
