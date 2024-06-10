import subprocess
import shutil
import sys
import os
from concurrent.futures import ProcessPoolExecutor, as_completed
import multiprocessing
import tempfile
import time
from get_konsist_snapshot_version import get_konsist_snapshot_version
import glob
from subprocess import CalledProcessError, check_output

multiprocessing.set_start_method('fork')

# Variables ============================================================================================================
error_occurred = False
script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
user_home = os.path.expanduser("~")
gradlew_path = os.path.join(project_root, 'gradlew')
kt_temp_files_dir = tempfile.mkdtemp()
dummy_classes_path = os.path.join(project_root, "lib/src/snippet/kotlin/dummyclasses")
output_jar_path = os.path.join(kt_temp_files_dir, "all_test_data.jar")
success = "SUCCESS"
failed = "FAILED"
konsist_version = get_konsist_snapshot_version()


# Methods =============================================================================================================

def print_and_flush(message):
    print(message)
    sys.stdout.flush()


def create_snippet_test_dir():
    if os.path.exists(kt_temp_files_dir):
        shutil.rmtree(kt_temp_files_dir)

    os.makedirs(kt_temp_files_dir)


def copy_ktdoc_files_and_change_extension_to_kt(source_files, target_dir):
    # Iterate over the source files
    for source_file_path in source_files:
        # Check if the current file has a '.ktdoc' extension
        if source_file_path.endswith('.ktdoc'):
            # Extract the part of the path after project_root if it is a substring of the path
            if project_root in source_file_path:
                relative_path_part = source_file_path.split(project_root, 1)[1]
            else:
                relative_path_part = source_file_path

            # Remove leading slashes
            relative_path_part = relative_path_part.lstrip("/\\")

            # Concatenate the target directory with the relative path part
            target_file_path_with_extension = os.path.join(target_dir, relative_path_part)

            # Replace the .ktdoc extension with .kt
            target_file_path = os.path.splitext(target_file_path_with_extension)[0] + '.kt'

            # Create the target directory if it doesn't exist
            os.makedirs(os.path.dirname(target_file_path), exist_ok=True)
            # Copy the file from source to target with new extension
            shutil.copy2(source_file_path, target_file_path)


def run_gradle_publish():
    try:
        result = subprocess.run(
            ['./gradlew', 'publishToMavenLocal', '-Pkonsist.releaseTarget=local'],
            check=True,
            text=True,
            capture_output=True
        )
        print("Gradle command executed successfully.")
        print("Output:")
        print(result.stdout)
    except subprocess.CalledProcessError as e:
        print("An error occurred while running the Gradle command.")
        print("Error output:")
        print(e.stderr)

def compile_test_data_jar(package_path, output_jar_path):
    global error_occurred
    error_occurred = False

    # Include Kotlin standard library
    command = ["kotlinc", "-include-runtime", "-d", output_jar_path]

    # Get all Kotlin source files recursively
    kotlin_files = glob.glob(os.path.join(package_path, "**/*.kt"), recursive=True)

    # Add each Kotlin file as source to the command
    for file in kotlin_files:
        command.append(file)

    try:
        check_output(command, stderr=subprocess.STDOUT, encoding="utf-8")
        print(f"Compile {output_jar_path} successful")
    except CalledProcessError as e:
        error_occurred = True
        print(f"An error occurred while compiling:\n{e.output}")

    if error_occurred:
        print("Errors encountered during compilation.")


def compile_kotlin_file(file_path):
    error_occurred_local = False

    temp_dir = tempfile.mkdtemp()

    sample_konsist_library_path = user_home + f"/.m2/repository/com/lemonappdev/konsist/{konsist_version}/konsist-{konsist_version}.jar"

    snippet_command = [
        "kotlinc",
        "-cp",
        f"{sample_konsist_library_path}",
        "-nowarn",
        "-d", temp_dir,
        file_path
    ]

    try:
        subprocess.run(snippet_command, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        error_occurred_local = True
        print_and_flush(e.stderr)

    shutil.rmtree(temp_dir)

    message = "compile " + os.path.basename(file_path)

    if error_occurred_local:
        return message, failed
    else:
        return message, success


def compile_kotlin_files(kotlin_files):
    global error_occurred
    total_files = len(kotlin_files)
    processed_files = 0

    sample_konsist_library_path = user_home + f"/.m2/repository/com/lemonappdev/konsist/{konsist_version}/"

    if not os.path.exists(sample_konsist_library_path):
        print_and_flush(f"Error: The file {sample_konsist_library_path} does not exist.")
        sys.exit(1)  # Exit the script with an error code

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
    shutil.rmtree(kt_temp_files_dir)
    subprocess.run(["git", "clean", "-f"])


def get_kt_temp_file_from_ktdoc_file(ktdoc_snippet_file_path):
    # Ensure the snippet_file_path starts with the project_root
    if not ktdoc_snippet_file_path.startswith(project_root):
        ktdoc_snippet_file_path = project_root + "/" + ktdoc_snippet_file_path

    # Verify that the .ktdoc file exists
    if not os.path.isfile(ktdoc_snippet_file_path):
        raise FileNotFoundError(f"The file {ktdoc_snippet_file_path} does not exist.")

    return ktdoc_snippet_file_path


def get_all_ktdoc_files():
    ktdoc_temp_file_paths = []

    for root, dirs, files in os.walk(project_root):
        for file in files:
            if file.endswith('.ktdoc'):
                ktdoc_temp_file_paths.append(os.path.join(root, file))

    kt_temp_file_paths = [get_kt_temp_file_from_ktdoc_file(path) for path in ktdoc_temp_file_paths]
    return kt_temp_file_paths


def ensure_files_exist(file_paths):
    for file_path in file_paths:
        if not os.path.exists(file_path):
            # File does not exist, raise an exception
            raise FileNotFoundError(f"The file {file_path} does not exist.")


def count_files_in_directory(directory):
    total_files = 0
    for root, dirs, files in os.walk(directory):
        total_files += len(files)
    return total_files


def list_files(directory):
    file_list = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            file_list.append(os.path.join(root, file))
    return file_list


def print_relative_file_paths(file_list):
    for file in file_list:
        print(os.path.relpath(file, project_root))


def get_all_file_paths(directory):
    file_paths = []  # List to store file paths

    # Walking top-down from the root
    for root, _, files in os.walk(directory):
        for file in files:
            # Concatenate the root directory and the filename to get full file path
            file_path = os.path.join(root, file)
            file_paths.append(file_path)

    return file_paths


# Script ===============================================================================================================

if __name__ == '__main__':
    kotlin_ktdoc_temp_files = []

    if len(sys.argv) > 1:
        if '-all' in sys.argv[1:]:
            print_and_flush("ktdoc_snippet_file_paths not provided - checking all ktdoc files")
            kotlin_ktdoc_temp_files = get_all_ktdoc_files()
        else:
            print_and_flush("ktdoc_snippet_file_paths are provided - checking provided ktdoc files")
            kotlin_ktdoc_temp_files = sys.argv[1:]

    else:
        print("No files provided")
        print("To check all files, use the -all parameter")
        print("To check files use script.py file1 file2 ...")
        sys.exit(1)

    ensure_files_exist(kotlin_ktdoc_temp_files)

    print_relative_file_paths(kotlin_ktdoc_temp_files)

    copy_ktdoc_files_and_change_extension_to_kt(
        kotlin_ktdoc_temp_files,
        kt_temp_files_dir
    )

    kotlin_kt_temp_files = get_all_file_paths(kt_temp_files_dir)

    print("Total: " + str(count_files_in_directory(kt_temp_files_dir)))
    print()

    run_gradle_publish()

    start_time = time.time()
    compile_test_data_jar(dummy_classes_path, output_jar_path)

    compile_kotlin_files(kotlin_kt_temp_files)
    clean()
    end_time = time.time()  # Capture the end time to calculate the duration
    duration = end_time - start_time

    print()

    minutes, seconds = divmod(duration, 60)
    num_tests = len(kotlin_kt_temp_files)

    if error_occurred:
        print_and_flush(f"{failed}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
        sys.exit(1)
    else:
        print_and_flush(f"{success}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
        sys.exit(0)
