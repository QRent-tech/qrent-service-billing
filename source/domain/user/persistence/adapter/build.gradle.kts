dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:user:api:out"))
    implementation(project(":source:domain:user:domain"))
    implementation(project(":source:domain:user:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("user-persistence-adapter.jar")
}