dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:transaction:domain"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}


tasks.jar {
    archiveFileName.set("transaction-api-out.jar")
}