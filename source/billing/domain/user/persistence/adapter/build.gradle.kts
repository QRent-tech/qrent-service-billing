dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:user:api:out"))
    implementation(project(":source:billing:domain:user:domain"))
    implementation(project(":source:billing:domain:user:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("user-persistence-adapter.jar")
}