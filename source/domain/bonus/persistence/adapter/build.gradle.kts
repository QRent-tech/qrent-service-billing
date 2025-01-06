dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:bonus:api:out"))
    implementation(project(":source:domain:bonus:domain"))
    implementation(project(":source:domain:bonus:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("bonus-persistence-adapter.jar")
}