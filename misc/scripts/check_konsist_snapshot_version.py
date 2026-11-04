import os
import re
import sys
import urllib.request
import xml.etree.ElementTree as ET

# Checks that 'konsist.version' (base of the snapshot version) is a valid semver version higher than every Konsist
# version released to Maven Central, so the snapshot is never older than (or equal to) an existing release.

RELEASES_METADATA_URL = "https://repo1.maven.org/maven2/com/lemonappdev/konsist/maven-metadata.xml"

SEMVER_REGEX = re.compile(
    r"^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)"
    r"(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?"
    r"(?:\+[0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*)?$"
)


def get_konsist_version():
    script_directory = os.path.dirname(os.path.abspath(__file__))
    project_root_directory = os.path.dirname(os.path.dirname(script_directory))

    with open(os.path.join(project_root_directory, "gradle.properties"), "r") as file:
        for line in file:
            if line.startswith("konsist.version="):
                return line.split("=", 1)[1].strip()

    raise ValueError("'konsist.version' not found in gradle.properties")


def get_released_versions():
    with urllib.request.urlopen(RELEASES_METADATA_URL, timeout=30) as response:
        root = ET.fromstring(response.read())

    return [version.text.strip() for version in root.iter("version")]


# Returns key for comparing versions by semver precedence (https://semver.org/#spec-item-11), None if not semver
def semver_key(version):
    match = SEMVER_REGEX.match(version)

    if match is None:
        return None

    major, minor, patch, pre_release = match.groups()

    # Version without pre-release has higher precedence than version with pre-release (1.0.0-alpha.1 < 1.0.0)
    if pre_release is None:
        pre_release_key = (1,)
    else:
        # Numeric identifiers have lower precedence than alphanumeric ones, numeric ones are compared numerically
        pre_release_key = (0, tuple(
            (0, int(identifier), "") if identifier.isdigit() else (1, 0, identifier)
            for identifier in pre_release.split(".")
        ))

    return (int(major), int(minor), int(patch)), pre_release_key


def check_konsist_snapshot_version(konsist_version, released_versions):
    konsist_version_key = semver_key(konsist_version)

    if konsist_version_key is None:
        return f"'konsist.version={konsist_version}' is not a valid semver version."

    if "SNAPSHOT" in konsist_version:
        return f"'konsist.version={konsist_version}' must not contain 'SNAPSHOT' (suffix is added by the build)."

    semver_released_versions = [version for version in released_versions if semver_key(version) is not None]

    if not semver_released_versions:
        return "No released semver versions found on Maven Central."

    latest_released_version = max(semver_released_versions, key=semver_key)

    if konsist_version_key <= semver_key(latest_released_version):
        return (
            f"'konsist.version={konsist_version}' must be higher than the latest released version "
            f"'{latest_released_version}'. Bump 'konsist.version' in gradle.properties."
        )

    print(f"Konsist version '{konsist_version}' is higher than the latest released version '{latest_released_version}'.")
    return None


if __name__ == "__main__":
    error = check_konsist_snapshot_version(get_konsist_version(), get_released_versions())

    if error is not None:
        print(f"Error: {error}")
        sys.exit(1)
