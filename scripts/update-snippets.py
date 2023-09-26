import os
import subprocess
import datetime
import math
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

def format_heading_text(element):
    # Format function name as heading - capitalize letters and replace ''' into '`'
    function_name_as_heading = element.split("`")[1]
    function_name_words = function_name_as_heading.replace("'", "`").split(" ")
    function_name_words = map(lambda capitalize: capitalize.replace(capitalize, capitalize[0].upper() + capitalize[1:]), function_name_words)
    return " ".join(function_name_words)

def split_snippet_text(element):
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
def format_snippet_text(file_text):
    function_list = split_text_to_function_list(file_text)
    text = ""

    for element in function_list:
        heading = format_heading_text(element)
        snippet_name, function_body = split_snippet_text(element)

        function_body_lines = function_body.split('\n')
        formatted_lines = []

        # Iterate through lines to remove unnecessary tabs
        for line in function_body_lines:
            formatted_lines = remove_tabs(line, formatted_lines)

        # Reconstruct the function body text
        formatted_text = '\n'.join(formatted_lines[:-1])

        text += "## " + str(function_list.index(element) + 1) +  ". " + heading + "\n\n```kotlin\n@Test\nfun " + snippet_name + "{\n" + formatted_text + "```\n\n"

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
    with open(file_path, "w") as new_file:
        new_file.write(content)

# Add a missing line at the end of a Markdown file
def add_empty_line_to_md_file(md_content):
    if md_content.splitlines()[-1] != "":
        new_md_content = md_content + "\n"
        return new_md_content
    else:
        return md_content

def remove_files_from_directory_except_readme(directory_path):
    try:
        # List all files in the directory
        files = os.listdir(directory_path)

        # Iterate through the files and remove them, except "README.md"
        for file_name in files:
            if file_name.lower() != "readme.md":
                file_path = os.path.join(directory_path, file_name)
                if os.path.isfile(file_path):
                    os.remove(file_path)
        print(os.listdir(directory_path))
    except Exception as e:
        print(f"An error occurred: {e}")

# Copy content from source .kt and .md files to a destination file
def copy_content(source_kt_path, source_md_path, destination_folder):
    try:
        filename_md = os.path.basename(source_md_path)
        destination_path = construct_destination_path(destination_folder, filename_md)

        md_content = read_file(source_md_path)
        new_md_content = add_empty_line_to_md_file(md_content)

        kt_content = read_file(source_kt_path)
        kt_text = format_snippet_text(kt_content)

        content = new_md_content + kt_text

        write_file(destination_path, content)

    except Exception as e:
        print(f"Error copying content: {e}")

def create_pr():
    prTitle = "Update snippet code from " + get_current_date()
    os.system("gh pr create --title '" + prTitle + "' --body '""'")

# Script ===============================================================================================================

 # Change the current working directory
os.chdir(expanded_destination_directory)

create_git_branch()

remove_files_from_directory_except_readme(expanded_destination_directory)

# Iterate through all .md and .kt files in the source folder and copy them content
for filename_md in os.listdir(expanded_source_directory):
    if filename_md.endswith("snippets.md"):
        prefix = filename_md.split("-")[0]
        for filename_kt in os.listdir(expanded_source_directory):
            if filename_kt.lower().startswith(prefix) and filename_kt.lower().endswith("kt"):
                kt_path = os.path.join(expanded_source_directory, filename_kt)
                md_path = os.path.join(expanded_source_directory, filename_md)
                copy_content(kt_path, md_path, expanded_destination_directory)

# Commit and push changes
push_changes()

# Create a pull request
create_pr()
