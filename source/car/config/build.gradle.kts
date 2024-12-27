dependencies {
    implementation(project(":source:car:core"))
    implementation(project(":source:car:api:in"))
    implementation(project(":source:car:api:out"))
    implementation(project(":source:car:persistence:flyway"))
    implementation(project(":source:car:persistence:adapter"))
    implementation(project(":source:car:persistence:repository"))

    implementation(project(":source:common:api"))
    implementation(project(":source:common:core"))
    implementation(project(":source:driver:api:in"))

    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("car-config.jar")
}