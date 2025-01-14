plugins {
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
    id("java-library")
    id("com.gorylenko.gradle-git-properties") version "2.3.2"
}
group = "ee.qrental"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("io.spring.dependency-management")
    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.1")
        }
    }
}