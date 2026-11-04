---
name: bump-konsist-version
description: Bump the Konsist version after a release - set README and starter projects to the published version, set gradle.properties and the Maven tester project to the next version, then open a PR to develop. Use whenever the user wants to bump, update, increase or change the Konsist version, set the next version, prepare develop after a release, or sync README with the released version, even if they only say "bump version" or "next version is X".
---

# Bump Konsist Version

Konsist keeps two versions in the repo:

| Version   | Meaning                                    | Files                                                                                |
|-----------|--------------------------------------------|--------------------------------------------------------------------------------------|
| Published | Latest version released to Maven Central   | `README.md` (Gradle + Maven snippets), `samples/starter-projects/*` (toml, pom.xml)  |
| Next      | Version of snapshots built from `develop`  | `gradle.properties` (`konsist.version`), `konsist-root-path-maven-tester/pom.xml`    |

README and starter projects are copied by users, so they must point to a version that exists on Maven Central. CI
replaces the starter project version with the current snapshot, so they do not need the next version.

## Steps

1. Get the next version from the user. If missing, ask - suggest the next patch/minor/pre-release of the published
   version. Never guess silently.
2. Update `develop` and create a branch:
   ```bash
   git fetch origin && git checkout -b bump-konsist-version-<next> origin/develop
   ```
3. Run the script (reads published version from Maven Central, validates both versions, fails if any file does not
   contain the expected version entry):
   ```bash
   python3 .claude/skills/bump-konsist-version/scripts/bump_konsist_version.py <next>
   ```
   Pass `--published-version X` only if the user wants a version other than the highest released one.
4. Deprecations: the script prints every `Will be removed in version X` target. If any is `OUTDATED` or `NOT SEMVER`,
   show the list and ask the user whether to move them (and to which version). Do not change them without an answer -
   moving targets changes the public deprecation contract.
5. Verify: `git diff --stat` (expect `README.md`, `gradle.properties`, Maven tester pom, starter projects; files already
   on the published version are unchanged), then `./gradlew help -q` to confirm Gradle configuration still works.
6. Commit (no AI co-author lines):
   ```bash
   git add -A && git commit -m "Bump Konsist version to <next>"
   ```
7. Push and open the PR to `develop`. Check `git branch --show-current` right before pushing - the branch name must not
   contain roadmap IDs or iteration suffixes.
   ```bash
   git push -u origin bump-konsist-version-<next>
   gh pr create --base develop --title "Bump Konsist version to <next>" --body "<body>"
   ```
   PR body: a before/after table of published and next version, plus the moved deprecation targets if any. No Claude
   or "Generated with Claude Code" mention.

## Errors

- `Expected N Konsist version(s) in '<file>', found M` - the file format changed or a new file was added. Inspect the
  file, update the script pattern or file list, and tell the user.
- `is not released to Maven Central` - the release is not published yet (Central sync can take ~30 min). Wait or pass
  `--published-version`.
