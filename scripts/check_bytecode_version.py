import sys
import subprocess
import os
import tempfile
import shutil
from get_artifact_path import get_artifact_path

def get_bytecode_version(class_file):
    result = subprocess.run(["javap", "-verbose", class_file], capture_output=True, text=True)
    for line in result.stdout.splitlines():
        if "major version" in line:
            return line.split(":")[-1].strip()

def main():
    if len(sys.argv) != 3:
        print("Usage: script.py <path_to_jar> <desired_bytecode_version>")
        sys.exit(1)

    jar_path = get_artifact_path("jar")
    # Java 8 == bytecode version 52.0 (defined in the local.javalibrary.gradle.kts)
    desired_bytecode_version = "52"

    # Create a temporary directory
    temp_dir = tempfile.mkdtemp()

    try:
        # Unzip the jar file to the temporary directory
        subprocess.run(["unzip", "-qq", jar_path, "-d", temp_dir], check=True)

        # Walk the directory to find all .class files
        for root, _, files in os.walk(temp_dir):
            for file in files:
                if file.endswith(".class"):
                    file_path = os.path.join(root, file)
                    version = get_bytecode_version(file_path)
                    if version != desired_bytecode_version:
                        print(f"Error: {file_path} has bytecode version {version} which doesn't match desired version {desired_bytecode_version}")
                        sys.exit(1)

        print("All class files match the desired bytecode version!")
    finally:
        # Clean up the temporary directory
        shutil.rmtree(temp_dir)

if __name__ == "__main__":
    main()
