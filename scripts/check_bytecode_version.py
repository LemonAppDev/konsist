import sys
import subprocess
import os
import shutil
from get_artifact_path import get_artifact_path

script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
build_dir = os.path.join(project_root, "build", "check-bytecode-version")

def get_bytecode_version(class_file):
    result = subprocess.run(["javap", "-verbose", class_file], capture_output=True, text=True)
    for line in result.stdout.splitlines():
        if "major version" in line:
            return line.split(":")[-1].strip()

def main():
    jar_path = get_artifact_path("jar")
    # Java 8 == bytecode version 52.0 (defined in the local.javalibrary.gradle.kts)
    desired_java_version = "8"
    desired_bytecode_version = "52"

    print(f"Verify in all classes in {jar_path} are compiled to bytecode {desired_bytecode_version} (Java {desired_java_version})")

    try:
        # Unzip the jar file to the temporary directory
        subprocess.run(["unzip", "-qq", jar_path, "-d", build_dir], check=True)

        # Walk the directory to find all .class files
        for root, _, files in os.walk(build_dir):
            for file in files:
                if file.endswith(".class"):
                    file_path = os.path.join(root, file)
                    version = get_bytecode_version(file_path)
                    if version != desired_bytecode_version:
                        print(f"Error: {file_path} has bytecode version {version} which doesn't match desired version {desired_bytecode_version}")
                        sys.exit(1)

        print(f"All classed in {file_path} have correct bytecode")
    finally:
        # Clean up the temporary directory
        shutil.rmtree(build_dir)

if __name__ == "__main__":
    main()
