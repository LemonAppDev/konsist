# Project Root Path Resolver Maven

This project is used to test root project folder resolution with
[Maven](https://maven.apache.org/) build system.

Test project contains [root](.) module and [app](./app) submodule.

Konsist artifact is retrieved from `mavenLocal` repository. Run tests with the current Konsist snapshot version:

```bash
./mvnw test -N -Dkonsist.version=<version>-SNAPSHOT  # root module
./mvnw test -pl app -Dkonsist.version=<version>-SNAPSHOT  # app module
```

For Konsist sample projects see
the [konsist-sample-projects](https://github.com/LemonAppDev/konsist-sample-projects/) repository.
