dependencies {
    implementation(project(":source:domain:common:api"))

    implementation(project(":source:domain:transaction:core"))
    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:domain:transaction:api:out"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:domain:user:api:in"))
    implementation(project(":source:domain:transaction:persistence:flyway"))
    implementation(project(":source:domain:transaction:persistence:adapter"))
    implementation(project(":source:domain:transaction:persistence:repository"))

    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:contract:api:in"))

    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("transaction-config.jar")
}