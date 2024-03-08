import sys
import subprocess
import os
import shutil
from get_artifact_path import get_artifact_path

script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
build_dir = os.path.join(project_root, "build", "check-bytecode-version")

# Ensure the directory exists
os.makedirs(build_dir, exist_ok=True)

print("build_dir", build_dir)


def get_bytecode_version(class_file):
    result = subprocess.run(["javap", "-verbose", class_file], capture_output=True, text=True)

    for line in result.stdout.splitlines():
        if "major version" in line:
            return line.split(":")[-1].strip()


def check_artifact_bytecode(desired_java_version, desired_bytecode_version):
    jar_path = get_artifact_path("jar")

    print( f"Verify if the first class in {jar_path} is compiled to bytecode {desired_bytecode_version} (Java {desired_java_version})" )

    checked = False  # Variable to track if we've already checked a .class file

    try:
        # Unzip the get_artifact_path jar file to the temporary directory
        subprocess.run(["unzip", "-qq", jar_path, "-d", build_dir], check=True)

        # Walk the directory to find all .class files
        for root, _, files in os.walk(build_dir):
            for file in files:
                if file.endswith(".class") and not checked:
                    file_path = os.path.join(root, file)
                    version = get_bytecode_version(file_path)
                    if version != desired_bytecode_version:
                        print(f"ERROR {jar_path} has incorrect bytecode version {version}")
                        sys.exit(1)
                    checked = True
                    break  # Exit the inner loop after checking the first .class file

            if checked:
                break  # Exit the outer loop if we've checked a .class file

        if checked:
            print(f"SUCCESS: First class has correct bytecode version: {desired_bytecode_version}")
        else:
            print("ERROR: No .class file found to check the artifact.")
            sys.exit(1)

    finally:
        # Clean up the temporary directory
        shutil.rmtree(build_dir)


# Java 8 == bytecode version 52.0 (defined in the local.javalibrary.gradle.kts)
# https://javaalmanac.io/bytecode/versions/
check_artifact_bytecode("8", "52")
