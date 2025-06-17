plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    //Javalin
    implementation("io.javalin:javalin:6.6.0")

    testImplementation("io.javalin:javalin-testtools:6.6.0")


    // SLF4J - Logger completo (API + implementação)
    implementation("org.slf4j:slf4j-simple:2.0.9")

    // JACKSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

application  {
    mainClass  = "org.example.Main"
}