# PR Check

Konsist is intended to run as a PR-level check.&#x20;

## GitHub Actions

Here is a sample [GitHub Actions](https://docs.github.com/en/actions) workflow that will run&#x20;

```
# .github/workflows/check.yml

name: Check

on:
  push:
    branches: [ main ]
  pull_request:
    types: [ opened, synchronize ]

  konsist-test:
    name: Konsist Test
    strategy:
      fail-fast: false
    steps:
      - name: checkout
        uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: 19
          distribution: 'zulu'

      - name: Konsist Test
        run: ./gradlew app:konsistTest
```
