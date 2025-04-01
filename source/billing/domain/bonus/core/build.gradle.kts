dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:common:core"))
    implementation(project(":source:billing:domain:common:utils"))

    implementation(project(":source:billing:domain:bonus:api:in"))
    implementation(project(":source:billing:domain:bonus:api:out"))
    implementation(project(":source:billing:domain:bonus:domain"))

    implementation(project(":source:billing:domain:car:api:in"))
    implementation(project(":source:billing:domain:contract:api:in"))
    implementation(project(":source:billing:domain:driver:api:in"))
    implementation(project(":source:billing:domain:user:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:billing:domain:constant:api:in"))
    implementation(project(":source:billing:domain:transaction:api:in"))

    implementation(libs.q.jakarta.transaction)

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)

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
    archiveFileName.set("bonus-core.jar")
}