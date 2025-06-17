rootProject.name = "at-java"
include("src:main:test")
findProject(":src:main:test")?.name = "test"
