plugins {
    id("com.gorylenko.gradle-git-properties")
}

dependencies {
    implementation(project(":source:domain:car:api:in"))
    implementation(project(":source:cross:email:api:in"))
    implementation(project(":source:domain:driver:api:in"))
    implementation(project(":source:domain:user:api:in"))
    implementation(project(":source:domain:transaction:api:in"))
    implementation(project(":source:domain:invoice:api:in"))
    implementation(project(":source:domain:contract:api:in"))
    implementation(project(":source:domain:firm:api:in"))
    implementation(project(":source:domain:constant:api:in"))
    implementation(project(":source:domain:bonus:api:in"))
    implementation(project(":source:domain:insurance:api:in"))
    implementation(project(":source:domain:common:api"))
    implementation(project(":source:domain:common:core"))
    implementation(project(":source:domain:common:utils"))

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.2.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.webjars:bootstrap:5.3.1")
    implementation("org.webjars:webjars-locator:0.47")
    implementation("org.webjars.npm:popper.js:1.16.1")
    implementation("org.webjars:jquery:3.7.1")

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    compileOnly(libs.q.lombok)
    annotationProcessor(libs.q.lombok)
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.jar {
    archiveFileName.set("ui-thymeleaf.jar")
}