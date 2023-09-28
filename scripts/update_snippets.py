import os
import subprocess
import datetime
import math
import re

# Variables ============================================================================================================
# Directories
source_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
destination_directory = "~/IdeaProjects/konsist-documentation/inspiration/snippets"

# Expand user paths
expanded_source_directory = os.path.expanduser(source_directory)
expanded_destination_directory = os.path.expanduser(destination_directory)

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
        number = indentation - 4*(math.floor(indentation / 4) - 1)
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

        text += "## " + str(function_list.index(element) + 1) +  ". " + heading + "\n\n```kotlin\n@Test\nfun " + snippet_name + "{\n" + formatted_text + "```\n\n"

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

        text += "## " + str(class_list.index(element) + 1) +  ". " + heading + "\n\n```kotlin\n" + formatted_text + "```\n\n"

    return text

def get_current_date():
    current_date = datetime.datetime.now().date()
    return current_date.strftime("%Y-%m-%d")

branch_name = get_current_date() + "-update-snippet-code"

def create_git_branch():
    try:
        subprocess.run(["git", "checkout", branch_name], check=True)
    except subprocess.CalledProcessError:
        subprocess.run(["git", "checkout", "-b", branch_name, "main"], check=True)

def push_changes():
    subprocess.run(["git", "add", "."], check=True)
    subprocess.run(["git", "commit", "-m", "Upd snippet code at docs"], check=True)
    subprocess.run(["git", "push", "origin", branch_name], check=True)

# Construct the paths for destination
def construct_destination_path(destination_folder, filename_md):
    return os.path.join(destination_folder, filename_md)

# Read content from a file
def read_file(file_path):
    with open(file_path, "r") as source_file:
        return source_file.read()

# Write content to a file
def write_file(file_path, content):
    # Ensure the directory exists; create it if it doesn't
    os.makedirs(os.path.dirname(file_path), exist_ok=True)

    with open(file_path, "w") as new_file:
        new_file.write(content)

# Add a missing line at the end of a Markdown file
def add_empty_line_to_md_file(md_content):
    if md_content.splitlines()[-1] != "":
        new_md_content = md_content + "\n"
        return new_md_content
    else:
        return md_content

def remove_files_except_readme(directory_path):
    try:
        os.remove(directory_path)
    except Exception as e:
        print(f"An error occurred: {e}")

# Copy content from source .kt and .md files to a destination file
def copy_content(expanded_source_directory, expanded_destination_directory):
    # Iterate through all .md and .kt files in the source folder and copy them content
    for root, dirs, files in os.walk(expanded_source_directory):
        for filename_md in files:
            if filename_md.endswith("snippets.md"):
                prefix = filename_md.split("-")[0]
                for filename_kt in files:
                    if filename_kt.lower().startswith(prefix) and filename_kt.lower().endswith("kt"):
                        kt_path = os.path.join(root, filename_kt)
                        md_path = os.path.join(root, filename_md)

                        try:
                            directory = root.split(expanded_source_directory)[1]

                            if len(directory) == 0:
                                path = expanded_destination_directory + "/" + filename_md
                            else:
                                path = expanded_destination_directory + directory + "/" + filename_md

                            remove_files_except_readme(path)

                            destination_path = construct_destination_path(path, filename_md)

                            md_content = read_file(md_path)
                            new_md_content = add_empty_line_to_md_file(md_content)

                            kt_content = read_file(kt_path)

                            if "io.kotest" in kt_content:
                                kt_text = format_class_snippet_text(kt_content)
                            else:
                                kt_text = format_function_snippet_text(kt_content)

                            content = new_md_content + kt_text

                            write_file(destination_path, content)

                        except Exception as e:
                            print(f"Error copying content: {e}")

def create_pr():
    pr_title = "Update snippet code from " + get_current_date()
    os.system("gh pr create --title '" + pr_title + "' --body '""'")

# Script ===============================================================================================================

# Change the current working directory
os.chdir(expanded_destination_directory)

create_git_branch()

copy_content(expanded_source_directory, expanded_destination_directory)

# Commit and push changes
push_changes()
#
# # Create a pull request
create_pr()
