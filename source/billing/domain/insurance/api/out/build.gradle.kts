dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:billing:domain:insurance:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-api-out.jar")
}