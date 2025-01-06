dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:car:api:out"))
    implementation(project(":source:domain:car:domain"))
    implementation(project(":source:domain:driver:api:in"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito:mockito-inline:5.2.0")
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("car-core.jar")
}