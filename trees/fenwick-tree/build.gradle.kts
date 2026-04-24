plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.1.20")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDirs("src/main/kotlin")
        }
        test {
            kotlin.srcDirs("src/test/kotlin")
        }
    }
}