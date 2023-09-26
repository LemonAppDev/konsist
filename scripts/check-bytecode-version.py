import sys
import subprocess
import os
import tempfile
import shutil

script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
build_dir = os.path.join(project_root, "build", "check-bytecode-version")

def get_bytecode_version(class_file):
    result = subprocess.run(["javap", "-verbose", class_file], capture_output=True, text=True)
    for line in result.stdout.splitlines():
        if "major version" in line:
            return line.split(":")[-1].strip()

def main():
    if len(sys.argv) != 3:
        print("Usage: script.py <path_to_jar> <desired_bytecode_version>")
        sys.exit(1)

    jar_path = sys.argv[1]
    desired_version = sys.argv[2]

    try:
        # Unzip the jar file to the temporary directory
        subprocess.run(["unzip", "-qq", jar_path, "-d", build_dir], check=True)

        # Walk the directory to find all .class files
        for root, _, files in os.walk(build_dir):
            for file in files:
                if file.endswith(".class"):
                    file_path = os.path.join(root, file)
                    version = get_bytecode_version(file_path)
                    if version != desired_version:
                        print(f"Error: {file_path} has bytecode version {version} which doesn't match desired version {desired_version}")
                        sys.exit(1)

        print("All class files match the desired bytecode version!")
    finally:
        # Clean up the temporary directory
        shutil.rmtree(build_dir)

if __name__ == "__main__":
    main()
