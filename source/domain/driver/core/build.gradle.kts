dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:bonus:api:in"))
    implementation(project(":source:domain:insurance:api:in"))
    implementation(project(":source:domain:driver:api:out"))
    implementation(project(":source:domain:driver:domain"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:common:utils"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation(project(":source:domain:common:core"))
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("driver-core.jar")
}