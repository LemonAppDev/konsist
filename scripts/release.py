import os
import subprocess
from common import (project_root)

# Methods ==============================================================================================================
def choose_release_option():
    """
    Prompts the user to choose between "Main Release - Upgrade Minor" and "Hotfix Release - Upgrade Patch".

    Returns: The chosen option (1 or 2).
    """

    print(f"\033[32mWhich release option do you choose? Write:\033[0m")
    print(f"\033[35m1 - if you want to create \"Main Release - Upgrade Minor\"\033[0m")
    print(f"\033[34m2 - if you want to create \"Hotfix Release - Upgrade Patch\"\033[0m")

    while True:
        choice = input(f"\033[31;1mEnter your choice (1 or 2): \033[0m")
        if choice in ["1", "2"]:
            return int(choice)
        else:
            print(f"\033[31mInvalid choice. Please enter 1 or 2.\033[0m")

def get_old_konsist_version():
    """
    Reads the gradle.properties file and returns the value of the `konsist.version` property.

    Returns: The value of the `konsist.version` property or stop script if not found.
    """

    gradle_properties_file = os.path.join(project_root, "gradle.properties")

    try:
        with open(gradle_properties_file, 'r') as f:
            for line in f:
                line = line.strip()
                if line.startswith('konsist.version='):
                    return line.split('=')[1]
        raise ValueError("konsist.version property not found in gradle.properties")
    except FileNotFoundError:
        print(f"Error: Gradle properties file '{gradle_properties_file}' not found.")
    except Exception as e:
        print(f"Error: An unexpected error occurred: {e}")
    return

def get_new_konsist_version(release_option_num, old_version):
    """
    Calculates the new version based on the release option and old version.

    Args:
        release_option_num: The chosen release option number (1 or 2).
        old_version: The current version string (obtained from get_old_konsist_version).

    Returns: The new version string or stop script if invalid option.
    """

    if not old_version:
        print("Error: Unable to determine old version.")
        return

    major_version, minor_version, patch_version = old_version.split('.')

    if release_option_num == 1:
        new_version = f"{major_version}.{int(minor_version) + 1}.0"
    elif release_option_num == 2:
        new_version = f"{major_version}.{minor_version}.{int(patch_version) + 1}"
    else:
        print(f"Error: Invalid release option number: {release_option_num}")
        return

    return new_version

def change_branch_and_merge():
    """
    Changes branch to 'development', fetches and pulls changes and merges 'main' into 'develop'.
    """

    try:
        # Change branch to 'development'
        subprocess.run(["git", "checkout", "development"], check=True)
        print("Switched to branch 'development'")

        # Fetch and pull changes
        subprocess.run(["git", "fetch"], check=True)
        print("Fetched changes")
        subprocess.run(["git", "pull"], check=True)
        print("Pulled changes")

        # Merge 'main' into 'develop'
        subprocess.run(["git", "merge", "main"], check=True)
        print("Merged 'main' into 'develop'")

    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")

def check_for_uncommitted_changes():
    """
    Checks if there are uncommitted changes in the current Git repository.

    Returns: True if there are uncommitted changes, False otherwise.
    """

    result = subprocess.run(["git", "status", "--porcelain"], check=True, capture_output=True)
    return bool(result.stdout.strip())

def create_release_branch(version):
    """
    Checks if a release branch with the specified version exists. If not, creates it from the 'development' branch.

    Args: version: The version number to check for.
    """

    try:
        # Check if the release branch already exists
        result = subprocess.run(["git", "branch", "--list"], check=True, capture_output=True)
        if f"Release/v{version}" in result.stdout.decode():
            print(f"Release branch 'Release/v{version}' already exists.")

            # Switch to the existing branch
            subprocess.run(["git", "checkout", f"Release/v{version}"], check=True)
            return

        # Create the release branch from 'development'
        subprocess.run(["git", "checkout", "-b", f"Release/v{version}"], check=True)
        print(f"Created release branch 'Release/v{version}'")

    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")

def create_release():
    chosen_option = 1 # remove!!!

    # chosen_option = choose_release_option()
    print(f"You chose option: {chosen_option}")

    old_konsist_version = get_old_konsist_version()
    print(f"Old konsist version: {old_konsist_version}")

    new_konsist_version = get_new_konsist_version(chosen_option, old_konsist_version)
    print(f"New konsist version: {new_konsist_version}")

    change_branch_and_merge()

    if check_for_uncommitted_changes():
        print("Error: There are uncommitted changes. Please commit or stash them before merging.")
        return
    else:
        print("There are no uncommitted changes. Script continues...")

    create_release_branch(new_konsist_version)

# Script ===============================================================================================================
create_release()
