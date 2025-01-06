dependencies {
    implementation(project(":source:cross:security:api:in"))
    implementation(project(":source:domain:user:api:in"))
    implementation(project(":source:domain:common:api"))
    implementation("org.springframework.boot:spring-boot-starter-security")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("security-core.jar")
}