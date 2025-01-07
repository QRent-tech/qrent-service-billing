dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:insurance:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-api-out.jar")
}