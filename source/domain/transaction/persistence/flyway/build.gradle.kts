dependencies {
    implementation(project(":source:domain:common:utils"))
    implementation(project(":source:domain:transaction:domain"))
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.jar {
    archiveFileName.set("transaction-persistence-flyway.jar")
}