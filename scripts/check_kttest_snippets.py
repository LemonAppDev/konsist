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
sample_external_library_path = os.path.join(project_root, "lib/libs/sample-external-library-1.2.jar")
success = "SUCCESS"
failed = "FAILED"

# Methods =============================================================================================================

def print_and_flush(message):
    print(message)
    sys.stdout.flush()

def compile_test_data_jar():
    global error_occurred
    command_converting_testdata_to_jar = [
        "kotlinc",
        os.path.join(project_root, "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt"),
        "-include-runtime",
        "-d",
        test_data_jar_file_path
    ]

    try:
        subprocess.run(command_converting_testdata_to_jar, check=True, text=True, capture_output=True)
    except subprocess.CalledProcessError as e:
        print_and_flush(f"An error occurred while running the command:\n{e.stderr}")
        print_and_flush("Compile " + test_data_jar_file_path + " " + failed)
        error_occurred = True
    else:
        print_and_flush("Compile test-data.jar " + success)

def create_snippet_test_dir():
    if os.path.exists(kt_temp_files_dir):
        shutil.rmtree(kt_temp_files_dir)

    os.makedirs(kt_temp_files_dir)

def copy_kttest_files_and_change_extension_to_kt(source_files, target_dir):
    for source_file_path in source_files:
        if source_file_path.endswith('.kttest'):
            relative_path_part = os.path.relpath(source_file_path, project_root)
            target_file_path_with_extension = os.path.join(target_dir, relative_path_part)
            target_file_path = os.path.splitext(target_file_path_with_extension)[0] + '.kt'
            os.makedirs(os.path.dirname(target_file_path), exist_ok=True)
            shutil.copy2(source_file_path, target_file_path)

def compile_kotlin_file(file_path):
    error_occurred_local = False
    with open(file_path, 'r') as file:
        file_content = file.read()

    if "actual" in file_content or "expect" in file_content:
        return os.path.basename(file_path), "SKIPPED"

    temp_dir = tempfile.mkdtemp()

    snippet_command = [
        "kotlinc",
        "-cp",
        f"{test_data_jar_file_path}:{sample_external_library_path}",
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

    if not os.path.exists(test_data_jar_file_path):
        print_and_flush(f"Error: The file {test_data_jar_file_path} does not exist.")
        sys.exit(1)

    if not os.path.exists(sample_external_library_path):
        print_and_flush(f"Error: The file {sample_external_library_path} does not exist.")
        sys.exit(1)

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

def get_kt_temp_file_from_kttest_file(kttest_snippet_file_path):
    if not kttest_snippet_file_path.startswith(project_root):
        kttest_snippet_file_path = os.path.join(project_root, kttest_snippet_file_path)

    if not os.path.isfile(kttest_snippet_file_path):
        raise FileNotFoundError(f"The file {kttest_snippet_file_path} does not exist.")

    return kttest_snippet_file_path

def get_all_kttest_files():
    kttest_temp_file_paths = []

    for root, dirs, files in os.walk(project_root):
        for file in files:
            if file.endswith('.kttest'):
                kttest_temp_file_paths.append(os.path.join(root, file))

    return kttest_temp_file_paths

def ensure_files_exist(file_paths):
    for file_path in file_paths:
        if not os.path.exists(file_path):
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
    file_paths = []
    for root, _, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            file_paths.append(file_path)
    return file_paths

# Script ===============================================================================================================

if __name__ == '__main__':
    kotlin_kttest_temp_files = []

    if len(sys.argv) > 1:
        input_file_path = sys.argv[1]
        if input_file_path == '-all':
            print_and_flush("Checking all kttest files")
            kotlin_kttest_temp_files = get_all_kttest_files()
        else:
            with open(input_file_path, 'r') as file:
                kotlin_kttest_temp_files = [line.strip() for line in file.readlines()]
    else:
        print("No files provided")
        print("To check all files, use the -all parameter")
        print("To check files use script.py <file_list>")
        sys.exit(1)

    ensure_files_exist(kotlin_kttest_temp_files)

    print_relative_file_paths(kotlin_kttest_temp_files)

    copy_kttest_files_and_change_extension_to_kt(
        kotlin_kttest_temp_files,
        kt_temp_files_dir
    )

    kotlin_kt_temp_files = get_all_file_paths(kt_temp_files_dir)

    print("Total: " + str(count_files_in_directory(kt_temp_files_dir)))
    print()

    start_time = time.time()
    compile_test_data_jar()
    compile_kotlin_files(kotlin_kt_temp_files)
    clean()
    end_time = time.time()
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
