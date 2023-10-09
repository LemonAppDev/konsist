import os
import re
import argparse
import subprocess

def call_get_konsist_version_script():
    current_script_path = os.path.abspath(__file__)
    current_script_directory = os.path.dirname(current_script_path)
    script_file = current_script_directory + "/../get-konsist-version.py"

    result = subprocess.run(['python3', script_file], stdout=subprocess.PIPE, check=True)
    return result.stdout.decode().strip()

def replace_version(file_name, new_version):
    with open(file_name, 'r') as file:
        content = file.read()

    if file_name.endswith(".gradle") or file_name.endswith(".kts"):
        # pattern for gradle files
        pattern = r"(com\.lemonappdev:konsist:)([\d\.]*(-SNAPSHOT)?)"
        replacement = r"\g<1>" + new_version
    else:
        # pattern for pom.xml
        pattern = r"(<groupId>com\.lemonappdev</groupId>\s*<artifactId>konsist</artifactId>\s*<version>)([\d\.]*(-SNAPSHOT)?)(</version>)"
        replacement = r"\g<1>" + new_version + r"\g<4>"

    new_content = re.sub(pattern, replacement, content)

    with open(file_name, 'w') as file:
        file.write(new_content)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    konsist_snapshot_version = call_get_konsist_version_script()
    print(konsist_snapshot_version)

    replace_version(args.file_path, konsist_snapshot_version)
