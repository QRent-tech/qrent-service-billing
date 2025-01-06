dependencies {
    implementation(project(":source:domain:contract:core"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:contract:api:out"))
    implementation(project(":source:domain:contract:persistence:flyway"))
    implementation(project(":source:domain:contract:persistence:adapter"))
    implementation(project(":source:domain:contract:persistence:repository"))

    implementation(project(":source:domain:common:api"))
    implementation(project(":source:transaction:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:insurance:api:in"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:cross:email:api:in"))

    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("contract-config.jar")
}