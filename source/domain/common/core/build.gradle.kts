dependencies {
    dependencies {
        implementation(project(":source:domain:common:api"))
        compileOnly(libs.q.lombok)
        annotationProcessor(libs.q.lombok)

        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.mockito:mockito-core")
        testImplementation("org.mockito:mockito-junit-jupiter")
        testImplementation("org.mockito:mockito-inline:5.2.0")
    }
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("common-core.jar")
}