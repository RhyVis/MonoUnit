import com.github.gradle.node.npm.task.NpmTask

group = "vis.rhynia"
version = "2.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
}

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.hibernate.orm") version "6.6.1.Final"
    id("org.graalvm.buildtools.native") version "0.10.3"
    id("com.github.node-gradle.node") version "7.1.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("cn.hutool:hutool-all:5.8.32")
    implementation("com.github.houbb:opencc4j:1.8.1")
    implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

hibernate {
    enhancement {
        enableAssociationManagement = true
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.processResources {
    dependsOn("copyView")
}

tasks.jar {
    archiveFileName.set("mono-unit-plain.jar")
}

tasks.bootJar {
    archiveFileName.set("mono-unit.jar")
}

tasks.register<NpmTask>("installNpm") {
    workingDir = file("../Monologue")
    args = listOf("install")
}

tasks.register<NpmTask>("buildView") {
    dependsOn("installNpm")
    workingDir = file("../Monologue")
    args = listOf("run", "publish")
}

tasks.register<Delete>("cleanView") {
    delete("src/main/resources/static")
}

tasks.register<Copy>("copyView") {
    dependsOn("buildView", "cleanView")
    from("../Monologue/dist")
    into("src/main/resources/static")
}

tasks.register("publish") {
    dependsOn("copyView")
    finalizedBy("build")
}
