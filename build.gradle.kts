plugins {
    kotlin("jvm") version "2.2.20"
    id("org.jetbrains.dokka") version "2.1.0"
}

group = "org.ramency"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

kotlin {
    jvmToolchain(24)
}

tasks.jar.configure {
    manifest {
        attributes(mapOf("Main-Class" to "org.ramency.kaffeemaschine.MainKt"))
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}