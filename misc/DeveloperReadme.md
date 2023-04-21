# Developer Readme

## Sonatype Repositories

[Nexus Repository Manager](https://s01.oss.sonatype.org/#view-repositories)

## Publish To Maven Repository

- `./gradlew publish` publish to `local` (default repository target), located in `~/.m2/repository` 
- `./gradlew publish -Pkonsist.releaseTarget=snapshot` publish to
[snapshot repository](https://s01.oss.sonatype.org/content/repositories/snapshots/com/lemonappdev/konsist/)
- `./gradlew publish -Pkonsist.releaseTarget=release` publish to
[release repository](https://s01.oss.sonatype.org/content/repositories/releases/com/lemonappdev/konsist/). This 
artefact will be transferred to [maven central](https://central.sonatype.com/artifact/com.lemonappdev/konsist) 
repository after some time.

## Links
- [Sonatype Jira](https://issues.sonatype.org/secure/Dashboard.jspa)

### Repository Links
- [Nexus Repository Manager](https://s01.oss.sonatype.org/#nexus-search;quick~konsist) 
- [mvnrepository.com](https://mvnrepository.com/artifact/com.lemonappdev/konsist/)
- [central.sonatype.com](https://central.sonatype.com/artifact/com.lemonappdev/konsist/)
