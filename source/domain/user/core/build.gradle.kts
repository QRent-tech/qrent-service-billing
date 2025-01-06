dependencies {
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:user:api:in"))
    implementation(project(":source:domain:user:api:out"))
    implementation(project(":source:domain:user:domain"))

    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:cross:security:api:in"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
    implementation(libs.q.jakarta.transaction)

}

tasks.jar {
    archiveFileName.set("user-core.jar")
}