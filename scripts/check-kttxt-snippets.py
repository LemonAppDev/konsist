import subprocess
import shutil
import sys
import os
from concurrent.futures import ProcessPoolExecutor, as_completed
import multiprocessing

multiprocessing.set_start_method('fork')

# Variables ============================================================================================================

error_occurred = False
script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
build_dir = os.path.join(project_root, "build", "snippet-test")
test_data_jar_file_path = build_dir + "/testData.jar"

# Methods =============================================================================================================

# New function to print and immediately flush the output
def print_and_flush(message):
    print(message)
    sys.stdout.flush()

def compile_test_data():
    global error_occurred
    command_converting_testdata_to_jar = [
        "kotlinc",
        project_root + "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt",
        "-include-runtime",
        "-d",
        test_data_jar_file_path
    ]

    try:
        subprocess.run(command_converting_testdata_to_jar, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        print_and_flush(f"An error occurred while running the command:\n{e.stderr}")
        print_and_flush("compile TestData.jar FAILED")
        error_occurred = True
    else:
        print_and_flush("compile TestData.jar PASSED")

def create_build_dir():
    if not os.path.exists(build_dir):
        os.makedirs(build_dir)

def copy_and_kttxt_files_and_change_extension_to_kt():
    source_dir = project_root + "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core"

    for root, dirs, files in os.walk(source_dir):
        for file in files:
            if file.endswith(".kttxt"):
                source_file_path = os.path.join(root, file)
                relative_dir = os.path.relpath(root, source_dir)
                destination_subdir = os.path.join(build_dir, relative_dir)
                os.makedirs(destination_subdir, exist_ok=True)
                destination_file_path = os.path.join(destination_subdir, file[:-6] + ".kt")
                shutil.copy2(source_file_path, destination_file_path)

def compile_kotlin_file(file_path):
    error_occurred_local = False
    with open(file_path, 'r') as file:
        file_content = file.read()

    # Ignore multiplatform snippets
    if "actual" in file_content or "expect" in file_content:
        return (os.path.basename(file_path), "SKIPPED")

    # Create and run kotlinc command which verifies valid Kotlin code
    snippet_command = [
        "kotlinc",
        "-cp",
        test_data_jar_file_path,
        "-nowarn",
        "-d", os.devnull,
        file_path
    ]

    try:
        subprocess.run(snippet_command, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        error_occurred_local = True
        # Print the error message when compilation fails
        print_and_flush(e.stderr)

    message = "compile " + os.path.basename(file_path)

    if error_occurred_local:
        return (message, "FAILED")
    else:
        return (message, "PASSED")

def compile_snippets():
    global error_occurred
    kotlin_files = []

    for root, dirs, files in os.walk(build_dir):
        for file_name in files:
            if file_name.endswith('.kt'):
                kotlin_files.append(os.path.join(root, file_name))

    total_files = len(kotlin_files)
    processed_files = 0

    with ProcessPoolExecutor() as executor:
        futures = {executor.submit(compile_kotlin_file, file_path): file_path for file_path in kotlin_files}
        for future in as_completed(futures):
            processed_files += 1
            file_name, result = future.result()
            percentage_completed = (processed_files / total_files) * 100
            print_and_flush(f"{file_name} {result} - {percentage_completed:.2f}% completed")
            if result == "FAILED":
                error_occurred = True

def clean():
    shutil.rmtree(build_dir)
    subprocess.run(["git", "clean", "-f"])

# Script ===============================================================================================================

compile_test_data()
create_build_dir()
copy_and_kttxt_files_and_change_extension_to_kt()

if __name__ == '__main__':
    compile_snippets()

clean()

if error_occurred:
    sys.exit(1)
else:
    sys.exit(0)
