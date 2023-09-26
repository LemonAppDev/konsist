import os
import subprocess
import datetime
import math

# Variables ============================================================================================================

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

def construct_destination_path(destination_folder, filename_md):
    return os.path.join(destination_folder, filename_md)


def create_git_branch(branch_name):
    try:
        subprocess.run(["git", "checkout", branch_name], check=True)
    except subprocess.CalledProcessError:
        subprocess.run(["git", "checkout", "-b", branch_name], check=True)

def push_changes(branch_name):
    subprocess.run(["git", "add", "."], check=True)
    subprocess.run(["git", "commit", "-m", "Upd snippet code at docs"], check=True)
    subprocess.run(["git", "push", "origin", branch_name], check=True)

# Function to copy content from source file to destination file
def copy_content(source_kt_path, source_md_path, destination_folder):
    try:
        filename_md = os.path.basename(source_md_path)

        # Construct the paths for destination
        destination_path = construct_destination_path(destination_folder, filename_md)

        # Read text of .md file
        with open(source_md_path, "r") as source_file:
            md_content = source_file.read()
            # Check if .md file ends with empty line - if not add missing line
            if md_content.splitlines()[-1] != "":
                md_content += "\n"

        # Read text of .kt file
        with open(source_kt_path, "r") as source_file:
            kt_content = source_file.read()
            kt_text = format_snippet_text(kt_content)

        # Add text of .md and .kt file to destination file
        with open(destination_path, "w") as destination_file:
            destination_file.write(md_content+kt_text)

    except Exception as e:
        print(f"Error copying content: {e} ")

# Directories
source_kt_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
source_md_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist/md"
destination_directory = "~/IdeaProjects/konsist-documentation/inspiration/snippets"

# Expand user paths
expanded_source_md_directory = os.path.expanduser(source_md_directory)
expanded_source_kt_directory = os.path.expanduser(source_kt_directory)
expanded_destination_directory = os.path.expanduser(destination_directory)

 # Change the current working directory
os.chdir(expanded_destination_directory)

# Get the current date
current_date = datetime.datetime.now().date()
formatted_date = current_date.strftime("%Y-%m-%d")

# Create a new branch name
new_branch_name = formatted_date + "-update-snippet-code"

# Check if the branch exists
create_git_branch(new_branch_name)

# Iterate through all .md and .kt files in the source folder and copy them content
for filename_md in os.listdir(expanded_source_md_directory):
    if filename_md.endswith("snippets.md"):
        prefix = filename_md.split("-")[0]
        for filename_kt in os.listdir(expanded_source_kt_directory):
            if prefix in filename_kt.lower():
                kt_path = os.path.join(expanded_source_kt_directory, filename_kt)
                md_path = os.path.join(expanded_source_md_directory, filename_md)
                copy_content(kt_path, md_path, expanded_destination_directory)

# Run git add command
# Commit and push changes
push_changes(new_branch_name)

# Create a pull request
prTitle = "Update snippet code from " + formatted_date
os.system("gh pr create --title '" + prTitle + "' --body '""'")
