dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:common:core"))
    implementation(project(":source:billing:domain:contract:api:out"))
    implementation(project(":source:billing:domain:contract:domain"))
    implementation(project(":source:billing:domain:contract:persistence:entity"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("contract-persistence-adapter.jar")
}