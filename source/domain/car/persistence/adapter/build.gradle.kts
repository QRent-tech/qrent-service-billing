dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:car:api:out"))
    implementation(project(":source:domain:car:domain"))
    implementation(project(":source:domain:car:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}
tasks.jar {
    archiveFileName.set("car-persistence-adapter.jar")
}