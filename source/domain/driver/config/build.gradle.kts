dependencies {
    implementation(project(":source:domain:driver:core"))
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:insurance:api:in"))
    implementation(project(":source:domain:driver:api:out"))
    implementation(project(":source:domain:driver:persistence:flyway"))
    implementation(project(":source:domain:driver:persistence:adapter"))
    implementation(project(":source:domain:driver:persistence:repository"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:bonus:api:in"))
    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("driver-config.jar")
}