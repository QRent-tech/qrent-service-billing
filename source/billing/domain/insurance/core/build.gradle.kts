dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:common:utils"))
    implementation(project(":source:billing:domain:insurance:api:in"))
    implementation(project(":source:billing:domain:insurance:api:out"))
    implementation(project(":source:billing:domain:insurance:domain"))

    implementation(project(":source:billing:domain:transaction:api:in"))
    implementation(project(":source:billing:domain:driver:api:in"))
    implementation(project(":source:billing:domain:constant:api:in"))
    implementation(project(":source:billing:domain:contract:api:in"))
    implementation(project(":source:billing:domain:car:api:in"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)
    implementation(libs.q.librepdf.openpdf)

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
    archiveFileName.set("insurance-core.jar")
}