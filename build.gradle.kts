plugins {
    kotlin("jvm") version "1.8.10"
    id("maven-publish")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
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
