dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:driver:api:out"))
    implementation(project(":source:billing:domain:driver:domain"))
    implementation(project(":source:billing:domain:driver:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("driver-persistence-adapter.jar")
}