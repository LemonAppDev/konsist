import os
import subprocess
import datetime
import math
import re
import tempfile
import shutil

# Variables ============================================================================================================
# Branches
main_branch = "main"

# Repository
repository_address = "LemonAppDev/konsist-documentation"

# Summary root
destination_snippets_path = "inspiration/snippets"


# Methods ==============================================================================================================

def split_text_to_function_list(file_text):
    text_without_suffix = file_text.removesuffix("}\n") + "\n\t"
    function_list = text_without_suffix.split("fun ")
    function_list.pop(0)
    return function_list


def split_text_to_classes_list(file_text):
    function_list = file_text.split("class ")[2:]
    return function_list


def capitalize_first_letter(word):
    if len(word) == 0:
        return word
    else:
        return word[0].upper() + word[1:]


def capitalize_all_first_letters(text):
    words = map(lambda capitalize: capitalize.replace(capitalize, capitalize[0].upper() + capitalize[1:]), text)
    return " ".join(words)


def split_words_by_capital(word):
    # Use regular expression to split words by capital letters
    words = re.findall(r'[A-Z][a-z]*', word)
    return ' '.join(words)


def format_heading_function_text(element):
    # Format function name as heading - capitalize letters and replace ''' into '`'
    function_name_as_heading = element.split("`")[1]
    function_name_words = function_name_as_heading.replace("'", "`").split(" ")
    function_name_words = capitalize_all_first_letters(function_name_words)
    return "".join(function_name_words)


def format_heading_class_text(element):
    # Format function name as heading - split class name to separate words and capitalize letters
    text = capitalize_first_letter(element)
    words = split_words_by_capital(text.split(" ")[0])
    return words


def split_function_snippet_text(element):
    snippet_parts = element.split("{", maxsplit=1)
    snippet_name = snippet_parts[0]
    function_body = snippet_parts[1].removeprefix("\n")
    return snippet_name, function_body


def remove_tabs(line, formatted_lines):
    if line.strip():  # Ignore empty lines
        indentation = len(line) - len(line.lstrip())
        number = indentation - 4 * (math.floor(indentation / 4) - 1)
        formatted_lines.append(line[number:])
    else:
        # Preserve empty lines
        formatted_lines.append(line)
    return formatted_lines


# Function to format the snippet text
def format_function_snippet_text(file_text):
    function_list = split_text_to_function_list(file_text)
    text = ""

    for element in function_list:
        heading = format_heading_function_text(element)
        snippet_name, function_body = split_function_snippet_text(element)

        function_body_lines = function_body.split('\n')
        formatted_lines = []

        # Iterate through lines to remove unnecessary tabs
        for line in function_body_lines:
            formatted_lines = remove_tabs(line, formatted_lines)

        # Reconstruct the function body text
        formatted_text = '\n'.join(formatted_lines[:-1])

        text += "## " + str(function_list.index(
            element) + 1) + ". " + heading + "\n\n```kotlin\n@Test\nfun " + snippet_name + "{\n" + formatted_text + "```\n\n"

    return text


def format_class_snippet_text(file_text):
    class_list = split_text_to_classes_list(file_text)
    text = ""

    for element in class_list:
        heading = format_heading_class_text(element)
        class_body = "class " + element

        class_body_lines = class_body.split('\n')

        formatted_lines = [class_body_lines[0]]

        # Iterate through lines to remove unnecessary tabs
        for line in class_body_lines[1:]:
            formatted_lines = remove_tabs(line, formatted_lines)

        # Reconstruct the class body text
        formatted_text = '\n'.join(formatted_lines[:-1])

        text += "## " + str(
            class_list.index(element) + 1) + ". " + heading + "\n\n```kotlin\n" + formatted_text + "```\n\n"

    return text


def get_current_date():
    current_date = datetime.datetime.now().date()
    return current_date.strftime("%Y-%m-%d")


# Read content from a file
def read_file(file_path):
    with open(file_path, "r") as source_file:
        return source_file.read()


# Write content to a file
def write_file(directory, file_path, content):
    if os.path.exists(directory):
        print(f"The directory '{directory}' exists.")
    else:
        print(f"The directory '{directory}' does not exist.")

    # Ensure the directory exists; create it if it doesn't
    os.makedirs(os.path.dirname(directory), exist_ok=True)

    with open(file_path, "w") as new_file:
        new_file.write(content)


# Add a missing line at the end of a Markdown file
def add_empty_line_to_md_file(md_content):
    if md_content.splitlines()[-1] != "":
        new_md_content = md_content + "\n"
        return new_md_content
    else:
        return md_content


def files_from_destination_directory(directory_path):
    try:
        # List all files and directories in the current directory
        for root, dirs, files in os.walk(directory_path):
            for file_name in files:
                if file_name.lower() != "readme.md":  # Skip "README.md"
                    return file_name, root
    except Exception as e:
        print(f"An error occurred: {e}")


def remove_files_recursively_except_readme(directory_path):
    try:
        file_name, root = files_from_destination_directory(directory_path)
        file_path = os.path.join(root, file_name)
        os.remove(file_path)

        print(f"All files within {directory_path} and its subdirectories, except 'README.md', have been removed.")
    except Exception as e:
        print(f"An error occurred: {e}")


# Copy content from source .kt and .md files to a destination file
def get_helper_root(root):
    try:
        text = root.split(destination_snippets_path)[1]
        return destination_snippets_path + text

    except Exception as e:
        print(f"An error occurred: {e}")


def number_of_packages(root):
    num = len(root.split("/"))
    return num - 3


def snippet_name_to_summary(root, file_text):
    try:
        first_line = file_text.split("\n")[0]
        first_line = "* [" + first_line.removeprefix("#").strip() + "]"

        # get short path to file
        helper_root = root.split(destination_snippets_path)[1]

        # add blank spaces
        content = ""
        packages = helper_root.split("/")

        packages_num = number_of_packages(root)

        num = packages_num * 2
        blank_space = " "

        if len(packages) > 2:
            for index, text in enumerate(packages):
                if index % 2 == 0:
                    count = index
                else:
                    count = index + 1

                content = blank_space * count  + "* [" + packages[index-1] + "]" + f"({destination_snippets_path}" + "/".join(packages[:index]) + ")" + "\n"

        return content, (blank_space * num + first_line + f"({destination_snippets_path}" + helper_root + ")")

    except Exception as e:
        print(f"An error occurred: {e}")


def add_blank_spaces(summary_dir):
    with open(summary_dir, "r") as file:
        file_content = file.read()

    text = f"* [Snippets]({destination_snippets_path}/README.md)"

    # Search for the search term substring
    match = re.search(re.escape(text), file_content)

    if match:
        # Get the starting position of the match
        start_pos = match.start()

        # Find the line containing the match
        line_start = file_content.rfind('\n', 0, start_pos) + 1
        line_end = file_content.find('\n', start_pos)

        # Extract the line containing the match
        line = file_content[line_start:line_end]

        # Count the number of blank spaces before the match
        num_spaces = len(re.match(r'\s*', line).group())

        return num_spaces + 2
    else:
        return -1  # Search term not found


def find_section_containing_text(sections, text):
    for index, section in enumerate(sections):
        if text in section:
            return index, section
    return None, None  # Return None if the text is not found in any section


def find_end_of_line(text, index):
    # Find the end of the line where the index occurs
    end_of_line = text.find("\n", index)
    if end_of_line == -1:
        # If no newline character is found, assume the end of the string
        end_of_line = len(text)
    return end_of_line


def complete_summary_file(root, file_text, summary_dir):
    # Read the existing content of the file
    with open(summary_dir, "r") as file:
        file_content = file.read()

    content, snippet_name = snippet_name_to_summary(root, file_text)

    name_without_tab = snippet_name.strip()

    if not name_without_tab in file_content:
        sections = file_content.split("##")

        text = f"* [Snippets]({destination_snippets_path}/README.md)"

        index, found_section = find_section_containing_text(sections, text)

        # Find the position where you want to insert the new text
        insertion_point = found_section.rfind(destination_snippets_path)

        if insertion_point != -1:
            end_of_line_index = find_end_of_line(found_section, insertion_point)
            spaces = " " * add_blank_spaces(summary_dir)

            if content != "":
                # Split the Kotlin string into lines
                lines = content.split('\n')

                # Add "&&&" prefix to each line
                prefixed_lines = [spaces + line for line in lines]

                # Join the modified lines back into a single string
                modified_content = '\n'.join(prefixed_lines)
            else:
                modified_content = content

            modified_section = (
                    found_section[:end_of_line_index]
                    + "\n"
                    + modified_content
                    + " " * add_blank_spaces(summary_dir)
                    + snippet_name
                    + found_section[end_of_line_index:]
            )

            sections[index] = modified_section

            modified_content = "##".join(sections)

            # Write the modified content back to the file
            with open(summary_dir, "w") as file:
                file.write(modified_content)


def copy_content(expanded_source_directory, expanded_destination_directory, summary_dir):
    # Iterate through all .md and .ktdoc files in the source folder and copy them content
    for root, dirs, files in os.walk(expanded_source_directory):
        for filename_md in files:
            if filename_md.endswith(".md"):
                prefix = filename_md.split(".")[0]
                for filename_ktdoc in files:
                    if filename_ktdoc.startswith(prefix) and filename_ktdoc.endswith("ktdoc"):
                        kt_path = os.path.join(root, filename_ktdoc)
                        md_path = os.path.join(root, filename_md)

                        try:
                            directory = root.split(expanded_source_directory)[1]

                            if len(directory) == 0:
                                path = expanded_destination_directory + "/"
                            else:
                                path = expanded_destination_directory + directory + "/"

                            list = [w.lower() for w in re.findall('[A-Z/][^A-Z/]*', filename_md)]
                            suffix = "-".join(list[0:])

                            destination_path = os.path.join(path, suffix)

                            md_content = read_file(md_path)

                            new_md_content = add_empty_line_to_md_file(md_content)

                            kt_content = read_file(kt_path)

                            if "io.kotest" in kt_content:
                                kt_text = format_class_snippet_text(kt_content)
                            else:
                                kt_text = format_function_snippet_text(kt_content)

                            content = new_md_content + kt_text

                            write_file(path, destination_path, content)

                            complete_summary_file(get_helper_root(destination_path), content, summary_dir)
                        except Exception as e:
                            print(f"Error copying content: {e}")


def push_changes():
    subprocess.run(["git", "add", "."], check=True)
    subprocess.run(["git", "commit", "-m", "Upd snippet code at docs"], check=True)
    subprocess.run(["git", "push", "origin", branch_name], check=True)


def create_and_merge_pr():
    pr_title = "Update snippet code from " + get_current_date()
    os.system("gh pr create --title '" + pr_title + "' --body '""'")
    # os.system("gh pr merge --merge --delete-branch")


def get_project_root():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    project_root = os.path.dirname(script_dir)
    return project_root


def create_temp_directory():
    return tempfile.mkdtemp()


def clone_git_repository(repository, temp_dir):
    subprocess.run(["gh", "repo", "clone", repository, temp_dir])


def fetch_remote_branches(temp_dir):
    subprocess.run(["git", "fetch"], cwd=temp_dir)


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


def cleanup_temp_directory(temp_dir):
    shutil.rmtree(temp_dir, ignore_errors=True)


def main(branch):
    try:
        # Take a source directory
        project_root = get_project_root()
        source_snippets_directory = os.path.expanduser(project_root + "/lib/src/snippet/kotlin/com/lemonappdev/konsist")

        # Create a temporary directory
        temp_dir = create_temp_directory()

        # Clone the Git repository into the temporary directory
        clone_git_repository(repository_address, temp_dir)

        # Fetch remote branches
        fetch_remote_branches(temp_dir)

        if not create_or_checkout_git_branch(branch, temp_dir):
            return None

        destination_snippets_directory = os.path.expanduser(temp_dir + "/inspiration/snippets")
        summary_path = temp_dir + "/SUMMARY.md"

        os.chdir(destination_snippets_directory)

        remove_files_recursively_except_readme(destination_snippets_directory)

        copy_content(source_snippets_directory, destination_snippets_directory, summary_path)

        os.chdir(temp_dir)

        push_changes()

        create_and_merge_pr()

        return temp_dir
    except subprocess.CalledProcessError as e:
        print(f"Error running Git command: {e}")
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        # Cleanup: Remove the temporary directory
        cleanup_temp_directory(temp_dir)


# Script ===============================================================================================================
branch_name = get_current_date() + "-update-snippet-code"

main(branch_name)
