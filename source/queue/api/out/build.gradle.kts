dependencies {
    implementation(project(":source:billing:domain:common:api"))
    implementation(project(":source:queue:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}