dependencies {
    implementation(project(":source:billing:domain:common:api"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("driver-api-in.jar")
}