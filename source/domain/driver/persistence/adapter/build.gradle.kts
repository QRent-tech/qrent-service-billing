dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:driver:api:out"))
    implementation(project(":source:domain:driver:domain"))
    implementation(project(":source:domain:driver:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("driver-persistence-adapter.jar")
}