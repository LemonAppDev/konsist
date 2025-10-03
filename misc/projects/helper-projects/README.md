# Sample External Library

Project used to generate library artifact. This artifact is added to Konsist project as `sample-external-library-1.2.jar` and used to test external parents.

How to use (in case more classes will be required in external libraries):
1. Build artifact `./gradlew build`.
2. Copy artifact from `build/libs` (this project) `lib/libs/` directory (Konsist project).
