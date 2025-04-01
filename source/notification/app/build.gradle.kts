plugins {
    id("org.springframework.boot") version "3.1.1"
}
dependencies {

    implementation(project(":source:common:config"))


    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}