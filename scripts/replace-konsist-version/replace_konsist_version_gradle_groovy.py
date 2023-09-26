import os
import re
import argparse
import subprocess
import sys

sys.path.append('../')  # Add the parent directory to the sys.path
from get_konsist_snapshot_version import get_konsist_snapshot_version

def replace_version(file_name):
    with open(file_name, 'r') as file:
        content = file.read()

    konsist_snapshot_version = get_konsist_snapshot_version()
    if file_name.endswith(".gradle") or file_name.endswith(".kts"):
        # pattern for gradle files
        pattern = r"(com\.lemonappdev:konsist:)([\d\.]*(-SNAPSHOT)?)"
        replacement = r"\g<1>" + konsist_snapshot_version
    else:
        # pattern for pom.xml
        pattern = r"(<groupId>com\.lemonappdev</groupId>\s*<artifactId>konsist</artifactId>\s*<version>)([\d\.]*(-SNAPSHOT)?)(</version>)"
        replacement = r"\g<1>" + konsist_snapshot_version + r"\g<4>"

    new_content = re.sub(pattern, replacement, content)

    with open(file_name, 'w') as file:
        file.write(new_content)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    replace_version(args.file_path)
