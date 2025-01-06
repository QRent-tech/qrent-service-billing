dependencies {
    implementation(project(":source:domain:common:api"))

    implementation(project(":source:domain:bonus:core"))
    implementation(project(":source:domain:bonus:api:in"))

    implementation(project(":source:domain:bonus:api:out"))
    implementation(project(":source:domain:bonus:persistence:flyway"))
    implementation(project(":source:domain:bonus:persistence:adapter"))
    implementation(project(":source:domain:bonus:persistence:repository"))


    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:user:api:in"))
    implementation(project(":source:domain:contract:api:in"))
    implementation("org.springframework:spring-context-support")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("bonus-config.jar")
}