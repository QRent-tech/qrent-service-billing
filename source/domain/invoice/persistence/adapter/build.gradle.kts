dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:invoice:api:out"))
    implementation(project(":source:domain:invoice:domain"))
    implementation(project(":source:domain:invoice:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-persistence-adapter.jar")
}