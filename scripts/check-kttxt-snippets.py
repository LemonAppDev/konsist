import subprocess
import shutil
import sys
import os
import concurrent.futures

script_path = os.path.abspath(__file__)
project_root = os.path.dirname(os.path.dirname(script_path))

# Convert TestData to jar extension
command_converting_testdata_to_jar = [
    "kotlinc",
    project_root + "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt",
    "-include-runtime",
    "-d",
    "test.jar"
]

isFailed = False

try:
    subprocess.run(command_converting_testdata_to_jar, check=True, text=True, capture_output=True)
except subprocess.CalledProcessError as e:
    print(f"An error occurred while running the command:\n{e.stderr}")
    isFailed = True

# Add snippet-test package
source_dir = project_root + "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core"
destination_dir = project_root + "/build/snippet-test"

def copy_and_rename_file(file):
    if file.endswith(".kttxt"):
        source_file_path = os.path.join(root, file)
        relative_dir = os.path.relpath(root, source_dir)
        destination_subdir = os.path.join(destination_dir, relative_dir)
        os.makedirs(destination_subdir, exist_ok=True)
        destination_file_path = os.path.join(destination_subdir, file[:-6] + ".kt")
        shutil.copy2(source_file_path, destination_file_path)

# Copy and rename files in parallel
with concurrent.futures.ThreadPoolExecutor() as executor:
    for root, dirs, files in os.walk(source_dir):
        executor.map(copy_and_rename_file, files)

def check_file(file_path):
    with open(file_path, 'r') as file:
        file_content = file.read()

    # Remove ignoring multiplatform and 1.9 kotlin version on the snippets - ticket KON-216
    if "actual" in file_content or "expect" in file_content or "data object" in file_content:
        return

    # Create and run kotlinc command which verifies valid Kotlin code
    snippet_command = [
        "kotlinc",
        "-cp",
        "test.jar",
        "-nowarn",
        file_path
    ]
    try:
        subprocess.run(snippet_command, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        print(f"An error occurred while running the command:\n{e.stderr}")
        isFailed = True

# Check files concurrently
with concurrent.futures.ThreadPoolExecutor() as executor:
    for root, dirs, files in os.walk(destination_dir):
        file_paths = [os.path.join(root, file_name) for file_name in files]
        executor.map(check_file, file_paths)

# Delete the "com" directory and its contents
com_directory = project_root + "/scripts/com"
try:
    shutil.rmtree(com_directory)
except FileNotFoundError:
    print(f"Directory '{com_directory}' does not exist.")
except OSError as e:
    print(f"An error occurred while deleting the directory '{com_directory}': {e}")

# Delete the "META-INF" directory and its contents
meta_inf_directory = project_root + "/scripts/META-INF"
try:
    shutil.rmtree(meta_inf_directory)
except FileNotFoundError:
    print(f"Directory '{meta_inf_directory}' does not exist.")
except OSError as e:
    print(f"An error occurred while deleting the directory '{meta_inf_directory}': {e}")

# Delete the "snippet-test" package and its contents
shutil.rmtree(destination_dir)

# Execute the Git command "git clean -f"
subprocess.run(["git", "clean", "-f"])

if isFailed is False:
    sys.exit(0)
else:
    sys.exit(1)
