dependencies {
    implementation(project(":source:domain:constant:core"))
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:constant:api:out"))
    implementation(project(":source:domain:constant:persistence:flyway"))
    implementation(project(":source:domain:constant:persistence:adapter"))
    implementation(project(":source:domain:constant:persistence:repository"))
    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("constant-config.jar")
}