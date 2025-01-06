dependencies {
    implementation(project(":source:domain:user:core"))
    implementation(project(":source:domain:user:api:in"))
    implementation(project(":source:domain:user:api:out"))
    implementation(project(":source:domain:user:persistence:flyway"))
    implementation(project(":source:domain:user:persistence:adapter"))
    implementation(project(":source:domain:user:persistence:repository"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.security:spring-security-crypto:6.4.2")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("user-config.jar")
}