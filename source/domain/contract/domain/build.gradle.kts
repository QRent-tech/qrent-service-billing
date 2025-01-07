dependencies {
    implementation(project(":source:domain:common:core"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("contract-domain.jar")
}