import json
import os
import re
import subprocess
import tempfile
import shutil

from common import (project_root)
from deploy_snippets_to_kotlin_documentation_repo import (deploy_snippets_to_kotlin_documentation_repo)

# Variables ============================================================================================================
gradle_properties_file = os.path.join(project_root, "gradle.properties")
read_me_file = os.path.join(project_root, "README.md")
files_with_version_to_change = [gradle_properties_file, read_me_file]

api_directory = 'lib/src/main/kotlin/com/lemonappdev/konsist/api'

konsist_documentation_repository_address = "LemonAppDev/konsist-documentation"

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

    Returns: The value of the `konsist.version` property or None if not found.
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
    return None


def get_new_konsist_version(release_option_num, old_version):
    """
    Calculates the new version based on the release option and old version.

    Args:
        release_option_num: The chosen release option number (1 or 2).
        old_version: The current version string (obtained from get_old_konsist_version).

    Returns: The new version string or None if invalid option.
    """

    if not old_version:
        print("Error: Unable to determine old version.")
        return None

    major_version, minor_version, patch_version = old_version.split('.')

    if release_option_num == 1:
        new_version = f"{major_version}.{int(minor_version) + 1}.0"
    elif release_option_num == 2:
        new_version = f"{major_version}.{minor_version}.{int(patch_version) + 1}"
    else:
        print(f"Error: Invalid release option number: {release_option_num}")
        return None

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
        subprocess.run(["git", "merge", "main", "--no-commit"], check=True)
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

    Returns: Branch title
    """

    try:
        # Check if the release branch already exists
        result = subprocess.run(["git", "branch", "--list"], check=True, capture_output=True)
        if f"Release/v{version}" in result.stdout.decode():
            print(f"Release branch 'release/v{version}' already exists.")

            # Switch to the existing branch
            subprocess.run(["git", "checkout", f"release/v{version}"], check=True)
            return

        # Create the release branch from 'development'
        branch_title = f"release/v{version}"
        subprocess.run(["git", "checkout", "-b", branch_title], check=True)
        print(f"Created release branch '{branch_title}'")
        return branch_title

    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")
        return None

def replace_konsist_version(old_version, new_version, files):
    """
    Replaces all occurrences of `old_version` with `new_version` in the provided files.

    Args:
      old_version: The old version string.
      new_version: The new version string.
      files: A list of file paths to modify.
    """

    for file_path in files:
        with open(file_path, 'r') as f:
            file_text = f.read()
            file_text = file_text.replace(old_version, new_version)

        with open(file_path, 'w') as f:
            f.write(file_text)
            print(f"Updated version in: {file_path}")

    commit_message = f"Replace Konsist version {old_version} to {new_version}"
    subprocess.run(["git", "add", "."], check=True)  # Stage all changes
    subprocess.run(["git", "commit", "-m", commit_message], check=True)  # Commit changes

def find_files_with_deprecated_annotation(directory, version):
    """
    Finds Kotlin files containing the @Deprecated annotation with the specified pattern.

    Args: directory: The directory to search.

    Returns: A list of file paths that match the criteria.
    """

    files_with_deprecated_annotation = []
    pattern = fr'@Deprecated\(".*{version}'

    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.kt'):
                file_path = os.path.join(root, file)
                with open(file_path, 'r') as f:
                    text = f.read()
                    if re.search(pattern, text):
                        files_with_deprecated_annotation.append(file_path)

    return  files_with_deprecated_annotation

def display_clickable_file_paths(file_path):
    # Construct the hyperlink URL
    hyperlink_url = f"file://{os.path.abspath(file_path)}"

    # Create the colored hyperlink text
    hyperlink_text = f"\033[34m\033]8;;{hyperlink_url}\033\\{file_path}\033]8;;\033\\\033[0m"

    print(hyperlink_text)

def create_pull_request_to_main(version):
    """
    Creates a pull request to the main branch with the specified title.

    Args:
      version: The version number to include in the title.
    """

    try:
        # Push the current branch to the remote repository, setting the upstream branch if needed
        subprocess.run(["git", "push", "--set-upstream", "origin", "HEAD"], check=True)

        # Create the pull request using the GitHub CLI
        subprocess.run(["gh", "pr", "create", "--title", f"Release/v{version}", "--body", "",  "--base", "main"], check=True)

    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")

def get_latest_commit_sha(branch):
    """
    Get the latest commit SHA from a specific branch using GitHub CLI.
    """
    try:
        result = subprocess.run(
            ['gh', 'api', f'/repos/LemonAppDev/konsist/commits/{branch}', '--jq', '.sha'],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )

        if result.returncode != 0:
            print(f"Error fetching latest commit SHA: {result.stderr}")
            return None

        return result.stdout.strip()

    except Exception as e:
        print(f"An error occurred while getting the latest commit SHA: {e}")
        return None

def check_github_checks(ref):
    """
    Check the status of GitHub checks for a specific commit.
    """
    try:
        result = subprocess.run(
            ['gh', 'api', f'/repos/LemonAppDev/konsist/commits/{ref}/check-runs', '--jq', '.check_runs'], # change to LemonAppDev!!!
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )

        if result.returncode != 0:
            print("Error fetching check runs:", result.stderr)
            return None

        check_runs = json.loads(result.stdout)

        # Flag to track if all checks passed (excluding skipped)
        # -1 - failed, 1 - success, 0 - running or neutral
        statuses = []

        for check in check_runs:
            check_name = check['name']
            check_status = check['conclusion']
            check_status_text = check.get('status', '')  # Check the status field for queued, in progress, etc.

            if check_status == 'success':
                print(f"Check '{check_name}' passed.")
                statuses.append(1)
            elif check_status == 'failure':
                print(f"Check '{check_name}' failed.")
                statuses.append(-1)
            elif check_status_text == 'queued':
                print(f"Check '{check_name}' is queued and waiting to run.")
                statuses.append(0)
            elif check_status_text == 'in_progress':
                print(f"Check '{check_name}' is currently running.")
                statuses.append(0)
            elif check_status == 'neutral':
                print(f"Check '{check_name}' skipped.")
                statuses.append(0)
            else:
                print(f"Check '{check_name}' status: {check_status_text}")

        return statuses

    except Exception as e:
        print(f"An error occurred while checking the GitHub checks: {e}")
        return None

def merge_release_pr(branch_name):
    """Merges a pull request from the given branch using GitHub CLI without squashing.

    Args: branch_name (str): The name of the branch to merge.
    """

    # Merge the branch without squashing using GitHub CLI
    try:
        subprocess.check_call(["gh", "pr", "merge", branch_name, "--merge", "--delete-branch"])
    except subprocess.CalledProcessError as e:
        print(f"Error: Failed to merge branch '{branch_name}': {e}")
        return

    print(f"Successfully merged branch '{branch_name}'.")

def generate_release_notes(tag_name):
    """
    Generate release notes for a specific tag in a GitHub repository using GitHub CLI.

    :param tag_name: The tag name for which to generate release notes
    :return: The release notes as a string or an error message
    """
    try:
        # Run GitHub CLI command to fetch release notes for a specific tag
        result = subprocess.run(
            ['gh', 'release', 'view', tag_name, '--json', 'body', '--jq', '.body'],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )

        # Check if the command was successful
        if result.returncode != 0:
            return f"Error fetching release notes: {result.stderr.strip()}"

        # Return the release notes as a string
        return result.stdout.strip()

    except Exception as e:
        return f"An error occurred while generating release notes: {e}"

def create_github_release(version):
    """Creates a new GitHub release with the given version number.

    Args: version (str): The version number for the release.

    Returns: str: The generated release notes.
    """

    # Set tag and release title
    tag = f"v{version}"
    release_title = f"v{version}"

    # Create the GitHub release
    subprocess.run(["gh", "release", "create", tag, "--title", release_title, "--generate-notes"], check=True)

    # Format release notes
    generated_release_notes = generate_release_notes(tag)
    updated_release_notes = format_and_update_github_release_notes(generated_release_notes)

    # Update the GitHub release
    subprocess.run(["gh", "release", "edit", tag, "--notes", updated_release_notes], check=True)

def format_and_update_github_release_notes(release_notes):
    print(release_notes)
    return "******Updated Release Notes******"

# needed ????
def create_or_checkout_git_branch(branch, temp_dir):
    try:
        result = subprocess.run(["git", "rev-parse", "--verify", branch], cwd=temp_dir, stderr=subprocess.PIPE)

        if result.returncode != 0:
            create_branch_result = subprocess.run(["git", "checkout", "-b", branch], cwd=temp_dir,
                                                  stderr=subprocess.PIPE)
            if create_branch_result.returncode != 0:
                print(f"Error creating branch '{branch}': {create_branch_result.stderr.decode().strip()}")
                return False
        else:
            checkout_result = subprocess.run(["git", "checkout", branch], cwd=temp_dir, stderr=subprocess.PIPE)
            if checkout_result.returncode != 0:
                print(f"Error checking out branch '{branch}': {checkout_result.stderr.decode().strip()}")
                return False
        return True
    except subprocess.CalledProcessError as e:
        print(f"Error running Git command: {e}")
    except Exception as e:
        print(f"An error occurred: {e}")
    return False

def update_version_in_konsist_documentation(repository, old_version, new_version):
    """Clones a Git repository, creates a branch, updates version numbers in Markdown files, and creates a pull request.

    Args:
        repository (str): The URL of the Git repository.
        old_version (str): The old version number to replace.
        new_version (str): The new version number to replace with.
    """

    temp_dir = tempfile.mkdtemp()

    try:
        subprocess.run(["gh", "repo", "clone", repository, temp_dir])
        subprocess.run(["git", "fetch"], cwd=temp_dir)

        branch_name = f"update_konsist_version_to_{new_version}"

        if not create_or_checkout_git_branch(branch_name, temp_dir):
            return None

        os.chdir(temp_dir)

        # Update version numbers in Markdown files
        for root, dirs, files in os.walk("."):
            for file in files:
                if file.endswith(".md"):
                    file_path = os.path.join(root, file)
                    with open(file_path, "r+") as f:
                        content = f.read()
                        content = content.replace(old_version, new_version)
                        f.seek(0)
                        f.write(content)
                        f.truncate()

        subprocess.run(["git", "add", "."], check=True)
        subprocess.run(["git", "commit", "-m", "Update version"], check=True)
        subprocess.run(["git", "push", "origin", branch_name], check=True)

        pr_title = f"Update Konsist version: {old_version} -> {new_version}"
        os.system("gh pr create --title '" + pr_title + "' --body '""'")
        os.system("gh pr merge --merge --delete-branch")

        return temp_dir
    except subprocess.CalledProcessError as e:
        print(f"Error running Git command: {e}")
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        # Cleanup: Remove the temporary directory
        shutil.rmtree(temp_dir, ignore_errors=True)

def create_release():
    chosen_option = 1  # remove!!!

    # chosen_option = choose_release_option()
    # print(f"You chose option: {chosen_option}")
    #
    old_konsist_version = get_old_konsist_version()
    # print(f"Old konsist version: {old_konsist_version}")
    #
    # # Check if old version is None
    # if old_konsist_version is None:
    #     print("Error: Unable to determine old version from `gradle.properties`.")
    #     return
    #
    new_konsist_version = get_new_konsist_version(chosen_option, old_konsist_version)
    # print(f"New konsist version: {new_konsist_version}")
    #
    # # Check if new version is None
    # if new_konsist_version is None:
    #     print("Error: Unable to determine new version.")
    #     return
    #
    # change_branch_and_merge()
    #
    # if check_for_uncommitted_changes():
    #     print("Error: There are uncommitted changes. Please commit or stash them before merging.")
    #     return
    # else:
    #     print("There are no uncommitted changes. Script continues...")
    #
    # release_branch_title = create_release_branch(new_konsist_version)
    #
    # replace_konsist_version(old_konsist_version, new_konsist_version, files_with_version_to_change)
    #
    # deprecated_files = find_files_with_deprecated_annotation(api_directory, new_konsist_version)
    #
    # # Check if list of files with deprecated annotation is not empty
    # if deprecated_files:
    #     print(f"Files contains @Deprecated annotation with {new_konsist_version} version:")
    #     for file in deprecated_files:
    #         file_path = os.path.join(project_root, file)
    #         display_clickable_file_paths(file_path)
    #     print(f"Remove deprecated declarations in the above files.")
    #     return
    # else:
    #     print(f"No files contains @Deprecated annotation with {new_konsist_version} version.")
    #
    # create_pull_request_to_main(new_konsist_version)
    #
    # # # Execute if all GitHub checks have passed
    # # while True:
    # #     # Get latest commit SHA
    # #     latest_commit_sha = get_latest_commit_sha(release_branch_title)
    # #     print(f"Latest commit SHA: {latest_commit_sha}")
    # #
    # #     time.sleep(30)
    # #     print(f"Wait for running checks...")
    # #
    # #     if not latest_commit_sha:
    # #         print(f"Error fetching commit SHA.")
    # #         break
    # #
    # #     # Check GitHub checks
    # #     check_statuses = check_github_checks(latest_commit_sha)
    # #
    # #     # Determine the status of the checks
    # #     if -1 in check_statuses:
    # #         print(f"The checks failed. Exiting script.")
    # #         sys.exit()
    # #
    # #     if 0 in check_statuses:
    # #         print(f"Checks in progress...")
    # #         time.sleep(60)  # Wait a minute before checking again
    # #         continue
    # #
    # #     if all(status == 1 for status in check_statuses):
    # #         print(f"All checks passed. Continuing script execution.")
    # #         # Add your script logic here
    # #         break  # Exit the loop if all checks passed
    #
    # merge_pr(release_branch_title)
    #
    # create_github_release(new_konsist_version)
    #
    # update_version_in_konsist_documentation(konsist_documentation_repository_address, old_konsist_version, new_konsist_version)

    deploy_snippets_to_kotlin_documentation_repo()
# Script ===============================================================================================================
create_release()
