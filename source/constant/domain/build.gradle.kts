dependencies {
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("constant-domain.jar")
}