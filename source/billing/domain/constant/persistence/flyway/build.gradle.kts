dependencies {
    implementation(project(":source:common:utils"))
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.jar {
    archiveFileName.set("constant-persistence-flyway.jar")
}