dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:firm:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("firm-api-out.jar")
}