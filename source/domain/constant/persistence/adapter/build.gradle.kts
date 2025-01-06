dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:constant:api:out"))
    implementation(project(":source:domain:constant:domain"))
    implementation(project(":source:domain:constant:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("constant-persistence-adapter.jar")
}