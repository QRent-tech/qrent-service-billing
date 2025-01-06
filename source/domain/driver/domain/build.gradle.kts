dependencies {
    implementation(project(":source:domain:common:api"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("driver-domain.jar")
}