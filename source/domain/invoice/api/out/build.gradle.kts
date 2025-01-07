dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:invoice:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-api-out.jar")
}