dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:common:core"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("contract-api-in.jar")
}