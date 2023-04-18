# Developer Readme

## Sonatype Repositories

[Nexus Repository Manager](https://s01.oss.sonatype.org/#view-repositories)

## Publish To Local Maven Repository

Use this command to publish artifacts to local maven repository (`~/.m2/repository`):

`./gradlew publishKonsistPublicationToMavenLocal `

This is useful for testing the artifacts in other projects.
