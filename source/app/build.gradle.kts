plugins {
    id("org.springframework.boot") version "3.1.1"
}
dependencies {
    implementation(project(":source:ui-thymeleaf"))
    implementation(project(":source:domain:common:config"))
    implementation(project(":source:domain:driver:config"))
    implementation(project(":source:domain:driver:persistence:flyway"))
    implementation(project(":source:domain:car:config"))
    implementation(project(":source:domain:car:persistence:flyway"))
    implementation(project(":source:domain:invoice:config"))
    implementation(project(":source:domain:invoice:persistence:flyway"))
    implementation(project(":source:domain:contract:config"))
    implementation(project(":source:domain:contract:persistence:flyway"))
    implementation(project(":source:domain:firm:config"))
    implementation(project(":source:domain:firm:persistence:flyway"))
    implementation(project(":source:domain:constant:config"))
    implementation(project(":source:domain:constant:persistence:flyway"))
    implementation(project(":source:domain:transaction:config"))
    implementation(project(":source:domain:transaction:persistence:flyway"))
    implementation(project(":source:domain:bonus:config"))
    implementation(project(":source:domain:bonus:persistence:flyway"))
    implementation(project(":source:domain:insurance:config"))
    implementation(project(":source:domain:insurance:persistence:flyway"))
    implementation(project(":source:domain:user:config"))
    implementation(project(":source:domain:user:persistence:flyway"))
    implementation(project(":source:cross:email:config"))
    implementation(project(":source:cross:task"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}