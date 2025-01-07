dependencies{
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:firm:api:out"))
    implementation(project(":source:domain:firm:domain"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("firm-core.jar")
}