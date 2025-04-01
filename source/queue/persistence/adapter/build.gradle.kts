dependencies {

    implementation(project(":source:billing:domain:common:api"))

    implementation(project(":source:queue:api:out"))
    implementation(project(":source:queue:domain"))
    implementation(project(":source:queue:persistence:entity"))

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}