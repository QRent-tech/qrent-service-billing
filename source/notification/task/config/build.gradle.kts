dependencies {
    implementation(project(":source:common:api"))
    implementation(project(":source:notification:domain:email:api:in"))
    implementation(project(":source:notification:domain:email:core"))

    implementation("org.springframework:spring-context-support")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}