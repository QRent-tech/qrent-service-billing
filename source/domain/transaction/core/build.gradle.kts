dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:common:utils"))
    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:domain:transaction:api:out"))
    implementation(project(":source:domain:transaction:domain"))

    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:user:api:in"))
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
    archiveFileName.set("transaction-core.jar")
}