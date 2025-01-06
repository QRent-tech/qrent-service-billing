plugins {
    id("org.springframework.boot") version "3.1.1"
}
dependencies {
    implementation(project(":source:ui-thymeleaf"))
    implementation(project(":source:domain:common:config"))
    implementation(project(":source:driver:config"))
    implementation(project(":source:driver:persistence:flyway"))
    implementation(project(":source:domain:car:config"))
    implementation(project(":source:domain:car:persistence:flyway"))
    implementation(project(":source:invoice:config"))
    implementation(project(":source:invoice:persistence:flyway"))
    implementation(project(":source:contract:config"))
    implementation(project(":source:contract:persistence:flyway"))
    implementation(project(":source:firm:config"))
    implementation(project(":source:firm:persistence:flyway"))
    implementation(project(":source:constant:config"))
    implementation(project(":source:constant:persistence:flyway"))
    implementation(project(":source:transaction:config"))
    implementation(project(":source:transaction:persistence:flyway"))
    implementation(project(":source:bonus:config"))
    implementation(project(":source:bonus:persistence:flyway"))
    implementation(project(":source:insurance:config"))
    implementation(project(":source:insurance:persistence:flyway"))
    implementation(project(":source:user:config"))
    implementation(project(":source:user:persistence:flyway"))
    implementation(project(":source:cross:email:config"))
    implementation(project(":source:cross:task"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}