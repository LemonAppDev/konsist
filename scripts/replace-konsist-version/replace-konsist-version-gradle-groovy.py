import os
import re
import argparse
import subprocess


def call_get_konsist_version_script():
    current_script_path = os.path.abspath(__file__)
    current_script_directory = os.path.dirname(current_script_path)
    get_konsist_version_file = current_script_directory + "/get-konsist-version.py"
    print(get_konsist_version_file)

    result = subprocess.run(['python3', get_konsist_version_file], 
                            stdout=subprocess.PIPE, check=True)
    return result.stdout.decode().strip()

def replace_version(file_path, new_version):
    with open(file_path, 'r') as file:
        contents = file.read()

    pattern = r"(com\.lemonappdev:konsist:)([^']*)"
    # pattern = r"(testImplementation 'com\.lemonappdev:konsist:)([^']*)'"

    def repl(match):
        return match.group(1) + new_version

    modified_contents = re.sub(pattern, repl, contents)

    with open(file_path, 'w') as file:
        file.write(modified_contents)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    konsist_version = call_get_konsist_version_script()
    konsist_snapshot_version = konsist_version + "-SNAPSHOT"
    print(konsist_snapshot_version)
    replace_version(args.file_path, konsist_snapshot_version)
