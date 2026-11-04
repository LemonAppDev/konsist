import argparse
import os
import re
import subprocess
import sys

# Bumps Konsist version after a release:
# - published version (README, starter projects) - version users can download from Maven Central
# - next version (gradle.properties, Maven tester project) - version of the snapshot built from 'develop'

project_root_directory = subprocess.run(
    ["git", "rev-parse", "--show-toplevel"], capture_output=True, text=True, check=True
).stdout.strip()
sys.path.append(os.path.join(project_root_directory, "misc", "scripts"))
from check_konsist_snapshot_version import get_konsist_version, get_released_versions, semver_key

STARTER_PROJECTS_DIRECTORY = os.path.join("samples", "starter-projects")
MAVEN_TESTER_POM = os.path.join("misc", "projects", "test-projects", "konsist-root-path-maven-tester", "pom.xml")
VERSION = r"[\w.+-]+"


def replace(file_path, pattern, version, expected_count):
    absolute_path = os.path.join(project_root_directory, file_path)

    with open(absolute_path, "r") as file:
        content = file.read()

    new_content, count = re.subn(pattern, r"\g<1>" + version + r"\g<2>", content)

    if count != expected_count:
        raise ValueError(f"Expected {expected_count} Konsist version(s) in '{file_path}', found {count}.")

    if new_content != content:
        with open(absolute_path, "w") as file:
            file.write(new_content)

        print(f"Updated {file_path}")


def get_starter_project_files(file_name):
    starter_projects_path = os.path.join(project_root_directory, STARTER_PROJECTS_DIRECTORY)
    paths = [
        os.path.join(STARTER_PROJECTS_DIRECTORY, project, file_name)
        for project in sorted(os.listdir(starter_projects_path))
    ]

    return [path for path in paths if os.path.isfile(os.path.join(project_root_directory, path))]


def bump_published_version(published_version):
    replace("README.md", rf'(com\.lemonappdev:konsist:){VERSION}(")', published_version, 2)
    replace(
        "README.md",
        rf"(<artifactId>konsist</artifactId>\s*<version>){VERSION}(</version>)",
        published_version,
        1,
    )

    for path in get_starter_project_files(os.path.join("gradle", "libs.versions.toml")):
        replace(path, rf'(?m)(^konsist\s*=\s*"){VERSION}(")', published_version, 1)

    for path in get_starter_project_files("pom.xml"):
        replace(
            path,
            rf"(<artifactId>konsist</artifactId>\s*<version>){VERSION}(</version>)",
            published_version,
            1,
        )


def bump_next_version(next_version):
    replace("gradle.properties", rf"(?m)(^konsist\.version=){VERSION}()$", next_version, 1)
    replace(MAVEN_TESTER_POM, rf"(<konsist\.version>){VERSION}(</konsist\.version>)", next_version, 1)


# Prints 'Will be removed in version X' targets, so outdated ones can be moved
def print_deprecation_targets(next_version):
    result = subprocess.run(
        ["git", "grep", "-ohE", r"removed in version [^\" ]+", "--", "lib/src/main"],
        capture_output=True,
        text=True,
        cwd=project_root_directory,
    )
    targets = {}

    for line in result.stdout.splitlines():
        target = line.rsplit(" ", 1)[1].rstrip(".")
        targets[target] = targets.get(target, 0) + 1

    print("\nDeprecation targets ('Will be removed in version X'):")

    for target, count in sorted(targets.items()):
        target_key = semver_key(target)

        if target_key is None:
            status = "NOT SEMVER"
        elif target_key <= semver_key(next_version):
            status = f"OUTDATED (<= {next_version})"
        else:
            status = "ok"

        print(f"  {target}: {count} usage(s) - {status}")


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("next_version", help="Version of the next release (e.g. 1.0.0-alpha.2).")
    parser.add_argument(
        "--published-version",
        help="Latest version released to Maven Central (default: highest released version).",
    )
    args = parser.parse_args()

    released_versions = [version for version in get_released_versions() if semver_key(version) is not None]
    published_version = args.published_version or max(released_versions, key=semver_key)

    if published_version not in released_versions:
        sys.exit(f"'{published_version}' is not released to Maven Central.")

    if semver_key(args.next_version) is None or "SNAPSHOT" in args.next_version:
        sys.exit(f"'{args.next_version}' is not a valid semver version (without 'SNAPSHOT').")

    if semver_key(args.next_version) <= semver_key(published_version):
        sys.exit(f"Next version '{args.next_version}' must be higher than published version '{published_version}'.")

    print(f"Current version: {get_konsist_version()}")
    print(f"Published version: {published_version}")
    print(f"Next version: {args.next_version}\n")

    bump_published_version(published_version)
    bump_next_version(args.next_version)
    print_deprecation_targets(args.next_version)


if __name__ == "__main__":
    main()
