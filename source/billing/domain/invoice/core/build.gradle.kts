dependencies{
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:billing:domain:common:utils"))
    implementation(project(":source:billing:domain:invoice:api:in"))
    implementation(project(":source:billing:domain:invoice:api:out"))
    implementation(project(":source:billing:domain:invoice:domain"))

    implementation(project(":source:billing:domain:transaction:api:in"))
    implementation(project(":source:billing:domain:driver:api:in"))
    implementation(project(":source:billing:domain:firm:api:in"))
    implementation(project(":source:billing:domain:constant:api:in"))
    implementation(project(":source:cross:email:api:in"))

    implementation(libs.q.jakarta.transaction)

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)
    implementation(libs.q.librepdf.openpdf)
}

tasks.jar {
    archiveFileName.set("invoice-core.jar")
}