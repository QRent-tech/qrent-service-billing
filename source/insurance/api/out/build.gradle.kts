dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:insurance:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-api-out.jar")
}