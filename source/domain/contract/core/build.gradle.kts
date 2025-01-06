dependencies{
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:common:core"))
    implementation(project(":source:domain:common:utils"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:contract:api:out"))
    implementation(project(":source:domain:contract:domain"))

    implementation(project(":source:transaction:api:in"))
    implementation(project(":source:insurance:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:domain:constant:api:in"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)
    implementation(libs.q.librepdf.openpdf)
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("contract-core.jar")
}