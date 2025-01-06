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
include("source:domain:driver:api:in")
include("source:domain:driver:api:out")
include("source:domain:driver:domain")
include("source:domain:driver:core")
include("source:domain:driver:persistence:adapter")
include("source:domain:driver:persistence:entity")
include("source:domain:driver:persistence:repository")
include("source:domain:driver:persistence:flyway")
include("source:domain:driver:config")

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
include("source:domain:firm:api:in")
include("source:domain:firm:api:out")
include("source:domain:firm:domain")
include("source:domain:firm:core")
include("source:domain:firm:persistence:adapter")
include("source:domain:firm:persistence:entity")
include("source:domain:firm:persistence:repository")
include("source:domain:firm:persistence:flyway")
include("source:domain:firm:config")

//Bonus domain:
include("source:domain:bonus:api:in")
include("source:domain:bonus:api:out")
include("source:domain:bonus:domain")
include("source:domain:bonus:core")
include("source:domain:bonus:persistence:adapter")
include("source:domain:bonus:persistence:entity")
include("source:domain:bonus:persistence:repository")
include("source:domain:bonus:persistence:flyway")
include("source:domain:bonus:config")

//Constant domain:
include("source:domain:constant:api:in")
include("source:domain:constant:api:out")
include("source:domain:constant:domain")
include("source:domain:constant:core")
include("source:domain:constant:persistence:adapter")
include("source:domain:constant:persistence:entity")
include("source:domain:constant:persistence:repository")
include("source:domain:constant:persistence:flyway")
include("source:domain:constant:config")

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
include("source:domain:contract:api:in")
include("source:domain:contract:api:out")
include("source:domain:contract:domain")
include("source:domain:contract:core")
include("source:domain:contract:persistence:adapter")
include("source:domain:contract:persistence:entity")
include("source:domain:contract:persistence:repository")
include("source:domain:contract:persistence:flyway")
include("source:domain:contract:config")

//Transaction domain:
include("source:domain:transaction:api:in")
include("source:domain:transaction:api:out")
include("source:domain:transaction:domain")
include("source:domain:transaction:core")
include("source:domain:transaction:persistence:adapter")
include("source:domain:transaction:persistence:entity")
include("source:domain:transaction:persistence:repository")
include("source:domain:transaction:persistence:flyway")
include("source:domain:transaction:config")

//Bonus domain:
include("source:domain:bonus:api:in")
include("source:domain:bonus:api:out")
include("source:domain:bonus:domain")
include("source:domain:bonus:core")
include("source:domain:bonus:persistence:adapter")
include("source:domain:bonus:persistence:entity")
include("source:domain:bonus:persistence:repository")
include("source:domain:bonus:persistence:flyway")
include("source:domain:bonus:config")

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