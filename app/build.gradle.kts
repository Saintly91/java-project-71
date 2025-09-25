plugins {
    id("java")
    id("application")
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.3.1.5724"
    id("jacoco")
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

jacoco {
    toolVersion = "0.8.13"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "Saintly91_java-project-71")
        property("sonar.organization", "saintly91")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.junit.reportPaths", "build/test-results/test")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

tasks.sonar {
    dependsOn(tasks.jacocoTestReport)
}
