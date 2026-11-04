import os
import sys
import argparse

# Get the absolute path to the parent directory
parent_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(parent_dir)
from get_published_konsist_snapshot_version import SNAPSHOTS_REPOSITORY_URL
from replace_konsist_version_gradle_groovy import replace_version

# Test projects retrieve Konsist from mavenLocal (latest version). This script switches given Gradle test project to
# Konsist snapshot published to the Central snapshots repository.

def use_konsist_snapshot(project_path, konsist_version):
    for root, dirs, files in os.walk(project_path):
        dirs[:] = [directory for directory in dirs if directory not in ['build', '.gradle']]

        for file_name in files:
            if not file_name.endswith('.gradle.kts'):
                continue

            file_path = os.path.join(root, file_name)

            with open(file_path, 'r') as file:
                content = file.read()

            if 'mavenLocal()' not in content:
                continue

            with open(file_path, 'w') as file:
                file.write(content.replace('mavenLocal()', f'maven("{SNAPSHOTS_REPOSITORY_URL}")'))

            print(f"Konsist snapshot repository used in {file_path}")

    replace_version(os.path.join(project_path, 'gradle', 'libs.versions.toml'), konsist_version)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("project_path", help="The path to the Gradle test project.")
    parser.add_argument("konsist_version", help="The Konsist version to use (e.g. 1.0.0-alpha.1-20261013.101530-1).")
    args = parser.parse_args()

    use_konsist_snapshot(args.project_path, args.konsist_version)
