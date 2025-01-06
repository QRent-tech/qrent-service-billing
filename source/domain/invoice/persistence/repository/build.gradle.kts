dependencies {
    implementation(project(":source:domain:invoice:persistence:adapter"))
    implementation(project(":source:domain:invoice:persistence:entity"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.jar {
    archiveFileName.set("invoice-persistence-repository.jar")
}