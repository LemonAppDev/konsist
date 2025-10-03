import subprocess
import shutil
import sys
import os
import tempfile

# Variables ============================================================================================================
script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(script_dir)
user_home = os.path.expanduser("~")
kt_temp_files_dir = tempfile.mkdtemp()

# Methods =============================================================================================================

def print_and_flush(message):
    print(message)
    sys.stdout.flush()

# Function to create a temporary directory for snippet files
def create_snippet_test_dir():
    # Remove existing temporary directory if present
    if os.path.exists(kt_temp_files_dir):
        shutil.rmtree(kt_temp_files_dir)

    # Create a new temporary directory
    os.makedirs(kt_temp_files_dir)

# Function to clean up temporary files
def clean():
    shutil.rmtree(kt_temp_files_dir)
    subprocess.run(["git", "clean", "-f"])

# Function to ensure that all files in a list exist
def ensure_files_exist(file_paths):
    for file_path in file_paths:
        if not os.path.exists(file_path):
            # File does not exist, raise an exception
            raise FileNotFoundError(f"The file {file_path} does not exist.")

# Function to count the total number of files in a directory
def count_files_in_directory(directory):
    total_files = 0
    for root, dirs, files in os.walk(directory):
        total_files += len(files)
    return total_files

# Function to print relative file paths
def print_relative_file_paths(file_list):
    for file in file_list:
        print(os.path.relpath(file, project_root))

# Function to get all file paths in a directory
def get_all_file_paths(directory):
    file_paths = []

    # Walking top-down from the root
    for root, _, files in os.walk(directory):
        for file in files:
            # Concatenate the root directory and the filename to get full file path
            file_path = os.path.join(root, file)
            file_paths.append(file_path)
    return file_paths
