dependencies {
    implementation(project(":source:domain:invoice:core"))
    implementation(project(":source:domain:invoice:api:in"))
    implementation(project(":source:domain:invoice:api:out"))
    implementation(project(":source:domain:invoice:persistence:flyway"))
    implementation(project(":source:domain:invoice:persistence:adapter"))
    implementation(project(":source:domain:invoice:persistence:repository"))

    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:cross:email:api:in"))

    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-config.jar")
}