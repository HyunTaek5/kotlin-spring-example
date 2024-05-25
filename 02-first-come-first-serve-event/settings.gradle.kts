plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "02-first-come-first-serve-event"

include("api")
include("consumer")