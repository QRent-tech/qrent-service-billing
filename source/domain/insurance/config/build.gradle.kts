dependencies {
    implementation(project(":source:domain:insurance:core"))
    implementation(project(":source:domain:insurance:api:in"))
    implementation(project(":source:domain:insurance:api:out"))
    implementation(project(":source:domain:insurance:persistence:flyway"))
    implementation(project(":source:domain:insurance:persistence:adapter"))
    implementation(project(":source:domain:insurance:persistence:repository"))

    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:contract:api:in"))

    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("insurance-config.jar")
}