import subprocess
import shutil
import sys
import os
from concurrent.futures import ProcessPoolExecutor, as_completed
import multiprocessing
import tempfile
import time

multiprocessing.set_start_method('fork')

# Variables ============================================================================================================
error_occurred = False
script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
kt_temp_files_dir = tempfile.mkdtemp()
test_data_jar_file_path = os.path.join(kt_temp_files_dir, "test-data.jar")
nested_test_data_jar_file_path = os.path.join(kt_temp_files_dir, "nested_test-data.jar")
sample_external_library_path = os.path.join(project_root, "lib/libs/sample-external-library-1.2.jar")
success = "SUCCESS"
failed = "FAILED"

# Methods =============================================================================================================
# Function to print messages and flush the output
def print_and_flush(message):
    print(message)
    sys.stdout.flush()

# Function to compile the test data JAR file
def compile_test_data_jar():
    global error_occurred
    # Command to compile test data to JAR
    command_converting_testdata_to_jar = [
        "kotlinc",
        os.path.join(project_root, "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt"),
        "-include-runtime",
        "-d",
        test_data_jar_file_path
    ]

    try:
        # Execute the compilation command
        subprocess.run(command_converting_testdata_to_jar, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        # Handle errors during compilation
        print_and_flush(f"An error occurred while running the command:\n{e.stderr}")
        print_and_flush("Compile " + test_data_jar_file_path + " " + failed)
        error_occurred = True
    else:
        print_and_flush("Compile test-data.jar " + success)

def compile_nested_test_data_jar():
    global error_occurred
    # Command to compile test data to JAR
    command_converting_testdata_to_jar = [
        "kotlinc",
        os.path.join(project_root, "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/testpackage/TestNestedData.kt"),
        "-include-runtime",
        "-d",
        nested_test_data_jar_file_path
    ]

    try:
        # Execute the compilation command
        subprocess.run(command_converting_testdata_to_jar, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        # Handle errors during compilation
        print_and_flush(f"An error occurred while running the command:\n{e.stderr}")
        print_and_flush("Compile " + nested_test_data_jar_file_path + " " + failed)
        error_occurred = True
    else:
        print_and_flush("Compile nested-test-data.jar " + success)

# Function to create a temporary directory for snippet files
def create_snippet_test_dir():
    # Remove existing temporary directory if present
    if os.path.exists(kt_temp_files_dir):
        shutil.rmtree(kt_temp_files_dir)

    # Create a new temporary directory
    os.makedirs(kt_temp_files_dir)

# Function to copy .kttest files and change their extension to .kt
def copy_kttest_files_and_change_extension_to_kt(source_files, target_dir):
    # Iterate over the source files
    for source_file_path in source_files:
        # Check if the file has .kttest extension
        if source_file_path.endswith('.kttest'):
            # Get the relative path of the file
            relative_path_part = os.path.relpath(source_file_path, project_root)
            # Generate the target file path with .kt extension
            target_file_path_with_extension = os.path.join(target_dir, relative_path_part)
            target_file_path = os.path.splitext(target_file_path_with_extension)[0] + '.kt'
            # Create necessary directories in the target path
            os.makedirs(os.path.dirname(target_file_path), exist_ok=True)
            # Copy the file with the new extension
            shutil.copy2(source_file_path, target_file_path)

# Function to compile a Kotlin file
def compile_kotlin_file(file_path):
    error_occurred_local = False
    # Read the content of the file
    with open(file_path, 'r') as file:
        file_content = file.read()

    # Check if the file contains certain keywords
    if "actual" in file_content or "expect" in file_content:
        return os.path.basename(file_path), "SKIPPED"

    # Create a temporary directory for compilation
    temp_dir = tempfile.mkdtemp()

    # Command to compile the Kotlin file
    snippet_command = [
        "kotlinc",
        "-cp",
        f"{test_data_jar_file_path}:{nested_test_data_jar_file_path}:{sample_external_library_path}",
        "-nowarn",
        "-d", temp_dir,
        file_path
    ]

    try:
        # Execute the compilation command
        subprocess.run(snippet_command, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        # Handle compilation errors
        error_occurred_local = True
        print_and_flush(e.stderr)

    # Remove the temporary directory
    shutil.rmtree(temp_dir)

    # Prepare the message based on compilation result
    message = "compile " + os.path.basename(file_path)
    if error_occurred_local:
        return message, failed
    else:
        return message, success

# Function to compile a list of Kotlin files
def compile_kotlin_files(kotlin_files):
    global error_occurred
    total_files = len(kotlin_files)
    processed_files = 0

    # Check if necessary files exist
    if not os.path.exists(test_data_jar_file_path):
        print_and_flush(f"Error: The file {test_data_jar_file_path} does not exist.")
        sys.exit(1)

    if not os.path.exists(nested_test_data_jar_file_path):
        print_and_flush(f"Error: The file {nested_test_data_jar_file_path} does not exist.")
        sys.exit(1)

    if not os.path.exists(sample_external_library_path):
        print_and_flush(f"Error: The file {sample_external_library_path} does not exist.")
        sys.exit(1)

    # Use concurrent processing to compile Kotlin files
    with ProcessPoolExecutor() as executor:
        futures = {executor.submit(compile_kotlin_file, file_path): file_path for file_path in kotlin_files}
        for future in as_completed(futures):
            processed_files += 1
            file_name, result = future.result()
            percentage_completed = (processed_files / total_files) * 100
            print_and_flush(f"{file_name} {result} - {percentage_completed:.2f}% completed")
            if result == "FAILED":
                error_occurred = True

# Function to clean up temporary files
def clean():
    shutil.rmtree(kt_temp_files_dir)
    subprocess.run(["git", "clean", "-f"])

# Function to get the .kt file path from a .kttest file path
def get_kt_temp_file_from_kttest_file(kttest_snippet_file_path):
    # Check if the file path starts with the project root
    if not kttest_snippet_file_path.startswith(project_root):
        kttest_snippet_file_path = os.path.join(project_root, kttest_snippet_file_path)

    # Verify that the .kttest file exists
    if not os.path.isfile(kttest_snippet_file_path):
        raise FileNotFoundError(f"The file {kttest_snippet_file_path} does not exist.")

    return kttest_snippet_file_path

# Function to get all .kttest files in the project
def get_all_kttest_files():
    kttest_temp_file_paths = []

    for root, dirs, files in os.walk(project_root):
        for file in files:
            if file.endswith('.kttest'):
                kttest_temp_file_paths.append(os.path.join(root, file))

    return kttest_temp_file_paths

# Function to ensure that all files in a list exist
def ensure_files_exist(file_paths):
    for file_path in file_paths:
        if not os.path.exists(file_path):
            raise FileNotFoundError(f"The file {file_path} does not exist.")

# Function to count the total number of files in a directory
def count_files_in_directory(directory):
    total_files = 0
    for root, dirs, files in os.walk(directory):
        total_files += len(files)
    return total_files

# Function to list all files in a directory
def list_files(directory):
    file_list = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            file_list.append(os.path.join(root, file))
    return file_list

# Function to print relative file paths
def print_relative_file_paths(file_list):
    for file in file_list:
        print(os.path.relpath(file, project_root))

# Function to get all file paths in a directory
def get_all_file_paths(directory):
    file_paths = []
    for root, _, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            file_paths.append(file_path)
    return file_paths

# Script ===============================================================================================================
if __name__ == '__main__':
    kotlin_kttest_temp_files = []

    # Check if command line arguments are provided
    if len(sys.argv) > 1:
        # Extract input file paths from command line arguments
        input_files = sys.argv[1:]

        # Check if the input files are valid
        temp_files = [f for f in input_files if os.path.isfile(f)]
        kttest_files = [f for f in input_files if f.endswith('.kttest')]

        # If multiple temporary files are provided, raise an exception
        if len(temp_files) > 1:
            raise Exception("Multiple temporary files provided. Only one is allowed.")
        # If a single temporary file is provided, read its contents
        elif len(temp_files) == 1:
            with open(temp_files[0], 'r') as file:
                kotlin_kttest_temp_files = [line.strip() for line in file.readlines()]
        # If .kttest files are provided, use them
        elif kttest_files:
            kotlin_kttest_temp_files = kttest_files
        else:
            # No valid input files provided
            print("No valid input files provided")
            sys.exit(1)
    else:
        # No files provided
        print("No files provided")
        print("To check all files, use the -all parameter")
        print("To check files use script.py <file_list_or_kttest_files>")
        sys.exit(1)

    # Ensure that all provided files exist
    ensure_files_exist(kotlin_kttest_temp_files)

    # Print the relative file paths of the provided files
    print_relative_file_paths(kotlin_kttest_temp_files)

    # Copy .kttest files and change their extension to .kt in the temporary directory
    copy_kttest_files_and_change_extension_to_kt(
        kotlin_kttest_temp_files,
        kt_temp_files_dir
    )

    # Get all file paths in the temporary directory
    kotlin_kt_temp_files = get_all_file_paths(kt_temp_files_dir)

    # Print the total number of files in the temporary directory
    print("Total: " + str(count_files_in_directory(kt_temp_files_dir)))
    print()

    # Measure the script execution time
    start_time = time.time()

    # Compile the test data JAR file
    compile_test_data_jar()
    compile_nested_test_data_jar()

    # Compile the Kotlin files in parallel
    compile_kotlin_files(kotlin_kt_temp_files)

    # Clean up temporary files
    clean()

    # Calculate script execution time
    end_time = time.time()
    duration = end_time - start_time

    print()

    # Print execution summary
    minutes, seconds = divmod(duration, 60)
    num_tests = len(kotlin_kt_temp_files)
    if error_occurred:
        print_and_flush(f"{failed}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
        sys.exit(1)
    else:
        print_and_flush(f"{success}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
        sys.exit(0)
