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
snippet_test_dir = os.path.join(project_root, "build", "snippet-test")
test_data_jar_file_path = snippet_test_dir + "/test-data.jar"
sample_external_library_path = project_root + "/lib/libs/sample-external-library-1.2.jar"
success = "SUCCESS"
failed = "FAILED"


# Methods =============================================================================================================

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
        print_and_flush("Compile " + test_data_jar_file_path + " " + failed)
        error_occurred = True
    else:
        print_and_flush("Compile " + test_data_jar_file_path + " " + success)

    print()


def create_snippet_test_dir():
    if os.path.exists(snippet_test_dir):
        shutil.rmtree(snippet_test_dir)

    os.makedirs(snippet_test_dir)


def copy_and_kttxt_files_and_change_extension_to_kt():
    source_dir = project_root + "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core"

    for root, dirs, files in os.walk(source_dir):
        for file in files:
            if file.endswith(".kttxt"):
                source_file_path = os.path.join(root, file)
                relative_dir = os.path.relpath(root, source_dir)
                destination_subdir = os.path.join(snippet_test_dir, relative_dir)
                os.makedirs(destination_subdir, exist_ok=True)
                destination_file_path = os.path.join(destination_subdir, file[:-6] + ".kt")
                shutil.copy2(source_file_path, destination_file_path)


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
        test_data_jar_file_path + ":" + sample_external_library_path,
        "-nowarn",
        "-d", temp_dir,
        file_path
    ]

    print_and_flush(" ".join(snippet_command))

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


def compile_snippets(snippets_changed):
    global error_occurred
    kotlin_files = []

    snippets_without_ext = [name.split('.kt')[0] for name in snippets_changed]

    for root, dirs, files in os.walk(snippet_test_dir):
        for file_name in files:
            if file_name.endswith('.kt'):
                file_name_without_ext = file_name[:-3]
                if not snippets_changed or file_name_without_ext in snippets_without_ext:
                    kotlin_files.append(os.path.join(root, file_name))

    total_files = len(kotlin_files)
    processed_files = 0

    if not os.path.exists(test_data_jar_file_path):
        print_and_flush(f"Error: The file {test_data_jar_file_path} does not exist.")
        sys.exit(1)  # Exit the script with an error code

    if not os.path.exists(sample_external_library_path):
        print_and_flush(f"Error: The file {sample_external_library_path} does not exist.")
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

    return total_files


def clean():
    shutil.rmtree(snippet_test_dir)
    subprocess.run(["git", "clean", "-f"])


# Script ===============================================================================================================

start_time = time.time()  # Capture the start time

create_snippet_test_dir()
compile_test_data()
copy_and_kttxt_files_and_change_extension_to_kt()

num_tests = 0

if __name__ == '__main__':
    snippets_changed_argument = sys.argv[1:]
    num_tests = compile_snippets(snippets_changed_argument)

clean()

end_time = time.time()  # Capture the end time to calculate the duration
duration = end_time - start_time

print()

minutes, seconds = divmod(duration, 60)

if error_occurred:
    print_and_flush(f"{failed}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
    sys.exit(1)
else:
    print_and_flush(f"{success}: Executed {num_tests} tests in {int(minutes)}m {seconds:.2f}s")
    sys.exit(0)

