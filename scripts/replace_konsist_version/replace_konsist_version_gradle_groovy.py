import os
import re
import argparse
import subprocess
import sys

# Get the absolute path to the parent directory
parent_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(parent_dir)
from get_konsist_snapshot_version import get_konsist_snapshot_version

def replace_version(file_path):
    with open(file_path, 'r') as file:
        content = file.read()

    konsist_snapshot_version = get_konsist_snapshot_version()
    if file_path.endswith(".gradle") or file_path.endswith(".kts"):
        # pattern for gradle files
        pattern = r"(com\.lemonappdev:konsist:)([\d\.]*(-SNAPSHOT)?)"
        replacement = r"\g<1>" + konsist_snapshot_version
    else:
        # pattern for pom.xml
        pattern = r"(<groupId>com\.lemonappdev</groupId>\s*<artifactId>konsist</artifactId>\s*<version>)([\d\.]*(-SNAPSHOT)?)(</version>)"
        replacement = r"\g<1>" + konsist_snapshot_version + r"\g<4>"

    new_content = re.sub(pattern, replacement, content)

    with open(file_path, 'w') as file:
        file.write(new_content)

    print(f"Konsist version replaced in {file_path}")

if __name__ == "__main__":
    print("Current Directory:", os.getcwd())
    print("System Path:", sys.path)
    print("System Absolute Path:", os.path.abspath)
    print("Joined Path:", os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()
    replace_version(args.file_path)
