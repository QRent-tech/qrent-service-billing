dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:insurance:api:out"))
    implementation(project(":source:billing:domain:insurance:domain"))
    implementation(project(":source:billing:domain:insurance:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-persistence-adapter.jar")
}