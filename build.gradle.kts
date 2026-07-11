import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

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
}

application {
    mainClass.set("net.bytelyplay.codeyourselfminestom.Main")
}