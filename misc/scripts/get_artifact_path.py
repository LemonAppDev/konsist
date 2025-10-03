# Script used to verify that the library artifact only exposes dependencies from org.jetbrains.kotlin.
import os
import sys
from get_konsist_snapshot_version import get_konsist_snapshot_version


def get_artifact_path(extension):
    konsist_version = get_konsist_snapshot_version()
    artifact_path = os.path.expanduser(
        "~/.m2/repository/com/lemonappdev/konsist/" + konsist_version + "/konsist-" + konsist_version + "." + extension)
    print(f"Artifact path: {artifact_path}")
    return artifact_path


if __name__ == "__main__":
    print(f'jar file_path path: {get_artifact_path("jar")}')
    print(f'pom file_path path: {get_artifact_path("pom")}')
