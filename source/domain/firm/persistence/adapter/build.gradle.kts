dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:firm:api:out"))
    implementation(project(":source:domain:firm:domain"))
    implementation(project(":source:domain:firm:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("firm-persistence-adapter.jar")
}