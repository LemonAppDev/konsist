import os
import subprocess

def get_konsist_version():
    current_script_path = os.path.abspath(__file__)
    current_script_directory = os.path.dirname(current_script_path)
    script_file = current_script_directory + "/get-konsist-version.py"

    result = subprocess.run(['python3', script_file], stdout=subprocess.PIPE, check=True)
    return result.stdout.decode().strip()

if __name__ == "__main__":
    print(get_konsist_version())
