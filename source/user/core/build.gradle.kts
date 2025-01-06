dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:user:api:in"))
    implementation(project(":source:user:api:out"))
    implementation(project(":source:user:domain"))

    implementation(project(":source:cross:email:api:in"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)
    implementation("org.springframework.security:spring-security-crypto:6.4.2")
}

tasks.jar {
    archiveFileName.set("user-core.jar")
}