dependencies {
    implementation(project(":source:billing:domain:invoice:core"))
    implementation(project(":source:billing:domain:invoice:api:in"))
    implementation(project(":source:billing:domain:invoice:api:out"))
    implementation(project(":source:billing:domain:invoice:persistence:flyway"))
    implementation(project(":source:billing:domain:invoice:persistence:adapter"))
    implementation(project(":source:billing:domain:invoice:persistence:repository"))

    implementation(project(":source:common:api"))
    implementation(project(":source:billing:domain:transaction:api:in"))
    implementation(project(":source:billing:domain:driver:api:in"))
    implementation(project(":source:billing:domain:firm:api:in"))
    implementation(project(":source:billing:domain:constant:api:in"))

    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-config.jar")
}