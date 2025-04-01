dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:common:utils"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-api-in.jar")
}