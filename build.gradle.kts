// TODO: Add Gradle ShadowUp.

plugins {
    id("java")
    id("application")
}

group = "net.bytelyplay.codeyourselfminestom"
version = "1.0.0-INDEV"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom:2026.07.01-26.1.2")
    implementation("ch.qos.logback:logback-classic:1.5.38")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.4")
}

application {
    mainClass.set("net.bytelyplay.codeyourselfminestom.Main")
}