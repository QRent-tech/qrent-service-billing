dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:insurance:api:out"))
    implementation(project(":source:domain:insurance:domain"))
    implementation(project(":source:domain:insurance:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-persistence-adapter.jar")
}