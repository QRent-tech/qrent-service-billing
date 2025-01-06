rootProject.name = "qrental"

pluginManagement {
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositories.mavenCentral()
}

include("source:app")
include("source:cross:task")

//Common:
include("source:domain:common:api")
include("source:domain:common:core")
include("source:domain:common:utils")
include("source:domain:common:config")

//Driver domain:
include("source:driver:api:in")
include("source:driver:api:out")
include("source:driver:domain")
include("source:driver:core")
include("source:driver:persistence:adapter")
include("source:driver:persistence:entity")
include("source:driver:persistence:repository")
include("source:driver:persistence:flyway")
include("source:driver:config")

//Car domain:
include("source:domain:car:api:in")
include("source:domain:car:api:out")
include("source:domain:car:domain")
include("source:domain:car:core")
include("source:domain:car:persistence:adapter")
include("source:domain:car:persistence:entity")
include("source:domain:car:persistence:repository")
include("source:domain:car:persistence:flyway")
include("source:domain:car:config")

//Firm domain:
include("source:firm:api:in")
include("source:firm:api:out")
include("source:firm:domain")
include("source:firm:core")
include("source:firm:persistence:adapter")
include("source:firm:persistence:entity")
include("source:firm:persistence:repository")
include("source:firm:persistence:flyway")
include("source:firm:config")

//Bonus domain:
include("source:bonus:api:in")
include("source:bonus:api:out")
include("source:bonus:domain")
include("source:bonus:core")
include("source:bonus:persistence:adapter")
include("source:bonus:persistence:entity")
include("source:bonus:persistence:repository")
include("source:bonus:persistence:flyway")
include("source:bonus:config")

//Constant domain:
include("source:constant:api:in")
include("source:constant:api:out")
include("source:constant:domain")
include("source:constant:core")
include("source:constant:persistence:adapter")
include("source:constant:persistence:entity")
include("source:constant:persistence:repository")
include("source:constant:persistence:flyway")
include("source:constant:config")

//Invoice domain:
include("source:invoice:api:in")
include("source:invoice:api:out")
include("source:invoice:domain")
include("source:invoice:core")
include("source:invoice:persistence:adapter")
include("source:invoice:persistence:entity")
include("source:invoice:persistence:repository")
include("source:invoice:persistence:flyway")
include("source:invoice:config")

//Insurance domain:
include("source:insurance:api:in")
include("source:insurance:api:out")
include("source:insurance:domain")
include("source:insurance:core")
include("source:insurance:persistence:adapter")
include("source:insurance:persistence:entity")
include("source:insurance:persistence:repository")
include("source:insurance:persistence:flyway")
include("source:insurance:config")

//Contract domain:
include("source:contract:api:in")
include("source:contract:api:out")
include("source:contract:domain")
include("source:contract:core")
include("source:contract:persistence:adapter")
include("source:contract:persistence:entity")
include("source:contract:persistence:repository")
include("source:contract:persistence:flyway")
include("source:contract:config")

//Transaction domain:
include("source:transaction:api:in")
include("source:transaction:api:out")
include("source:transaction:domain")
include("source:transaction:core")
include("source:transaction:persistence:adapter")
include("source:transaction:persistence:entity")
include("source:transaction:persistence:repository")
include("source:transaction:persistence:flyway")
include("source:transaction:config")

//Bonus domain:
include("source:bonus:api:in")
include("source:bonus:api:out")
include("source:bonus:domain")
include("source:bonus:core")
include("source:bonus:persistence:adapter")
include("source:bonus:persistence:entity")
include("source:bonus:persistence:repository")
include("source:bonus:persistence:flyway")
include("source:bonus:config")

//Email domain:
include("source:cross:email:api:in")
include("source:cross:email:core")
include("source:cross:email:config")

//User domain:
include("source:user:api:in")
include("source:user:api:out")
include("source:user:domain")
include("source:user:core")
include("source:user:persistence:adapter")
include("source:user:persistence:entity")
include("source:user:persistence:repository")
include("source:user:persistence:flyway")
include("source:user:config")

include("source:ui-thymeleaf")