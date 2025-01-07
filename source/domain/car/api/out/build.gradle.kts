dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:car:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("car-api-out.jar")
}