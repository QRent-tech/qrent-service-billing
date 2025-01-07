dependencies {
    implementation(project(":source:domain:firm:core"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:firm:api:out"))
    implementation(project(":source:domain:firm:persistence:flyway"))
    implementation(project(":source:domain:firm:persistence:adapter"))
    implementation(project(":source:domain:firm:persistence:repository"))
    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("firm-config.jar")
}