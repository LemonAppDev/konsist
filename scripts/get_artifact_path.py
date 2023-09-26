# Script used to verify that the library artifact only exposes dependencies from org.jetbrains.kotlin.
import os
import sys
from get_konsist_version import get_konsist_version

def get_artifact_path(extension):
    konsist_version = get_konsist_version()
    print(konsist_version)
    artifact_path = os.path.expanduser("~/.m2/repository/com/lemonappdev/konsist/" + konsist_version + "/konsist-" + konsist_version + "." + extension)
    return artifact_path

if __name__ == "__main__":
    print(f'jar file_path path: {get_jar_artifact_path()}')
    print(f'pom file_path path: {get_pom_artifact_path()}')
