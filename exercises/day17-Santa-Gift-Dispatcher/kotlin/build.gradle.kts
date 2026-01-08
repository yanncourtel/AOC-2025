plugins {
    kotlin("jvm") version "1.9.21"
    id("io.qameta.allure") version "2.11.2"
    application
}

group = "com.santa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("SantaSchedulingKt")
}
