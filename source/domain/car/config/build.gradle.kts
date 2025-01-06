dependencies {
    implementation(project(":source:domain:car:core"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:car:api:out"))
    implementation(project(":source:domain:car:persistence:flyway"))
    implementation(project(":source:domain:car:persistence:adapter"))
    implementation(project(":source:domain:car:persistence:repository"))

    implementation(project(":source:domain:common:api"))
    implementation(project(":source:driver:api:in"))

    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("car-config.jar")
}