dependencies {
    dependencies {
        implementation(project(":source:billing:domain:common:api"))
        implementation(project(":source:billing:domain:common:core"))
        implementation("org.springframework:spring-context-support")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        compileOnly(libs.q.lombok)
        annotationProcessor(libs.q.lombok)
    }
}

tasks.jar {
    archiveFileName.set("common-config.jar")
}