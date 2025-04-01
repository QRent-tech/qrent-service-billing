dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:billing:domain:bonus:api:out"))
    implementation(project(":source:billing:domain:bonus:domain"))
    implementation(project(":source:billing:domain:bonus:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("bonus-persistence-adapter.jar")
}