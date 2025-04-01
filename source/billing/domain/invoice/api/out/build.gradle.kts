dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:billing:domain:invoice:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-api-out.jar")
}