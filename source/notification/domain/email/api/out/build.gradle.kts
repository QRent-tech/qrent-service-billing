dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:notification:domain:email:domain"))
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}