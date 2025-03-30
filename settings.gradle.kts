rootProject.name = "inventory-qa"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.5"
}

// tests
include(
    "tests:simple",
    "tests:inventory"
)