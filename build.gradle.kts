import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform(kotlin("bom")))
    implementation(platform("org.junit:junit-bom:5.10.1"))

    constraints {
        implementation("io.strikt:strikt-core:0.34.1")
        implementation("io.mockk:mockk:1.13.8")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.strikt:strikt-core")
    testImplementation("io.mockk:mockk")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
