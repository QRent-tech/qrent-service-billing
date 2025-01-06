dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:common:utils"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-api-in.jar")
}