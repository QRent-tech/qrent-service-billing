dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:contract:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("contract-api-out.jar")
}