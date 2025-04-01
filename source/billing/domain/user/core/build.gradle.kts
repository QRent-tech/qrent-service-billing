dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:billing:domain:user:api:in"))
    implementation(project(":source:billing:domain:user:api:out"))
    implementation(project(":source:billing:domain:user:domain"))

    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:billing:security:api:in"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)

}

tasks.jar {
    archiveFileName.set("user-core.jar")
}