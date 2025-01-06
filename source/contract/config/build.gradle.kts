dependencies {
    implementation(project(":source:contract:core"))
    implementation(project(":source:contract:api:in"))
    implementation(project(":source:contract:api:out"))
    implementation(project(":source:contract:persistence:flyway"))
    implementation(project(":source:contract:persistence:adapter"))
    implementation(project(":source:contract:persistence:repository"))

    implementation(project(":source:domain:common:api"))
    implementation(project(":source:transaction:api:in"))
    implementation(project(":source:constant:api:in"))
    implementation(project(":source:driver:api:in"))
    implementation(project(":source:insurance:api:in"))
    implementation(project(":source:firm:api:in"))
    implementation(project(":source:cross:email:api:in"))

    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("contract-config.jar")
}