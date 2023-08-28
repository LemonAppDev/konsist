import os
import subprocess
import datetime
import math

# Function to format the snippet text
def format_snippet_text(file_text):
    # Split the text of the file into a list of functions
    text_without_suffix = file_text.removesuffix("}\n") + "\n\t"
    function_list = text_without_suffix.split("fun ")
    function_list.pop(0)

    text = ""

    for element in function_list:
        # Format function name as heading - capitalize letters and replace ''' into '`'
        function_name_as_heading = element.split("`")[1]
        function_name_words = function_name_as_heading.replace("'", "`").split(" ")
        function_name_words = map(lambda capitalize: capitalize.replace(capitalize, capitalize[0].upper() + capitalize[1:]), function_name_words)
        function_name_as_heading = " ".join(function_name_words)

        function_text = element.split("{", maxsplit = 1)
        function_name_as_snippet_body = function_text[0]

        function_body = function_text[1].removeprefix("\n")
        function_body_lines = function_body.split('\n')
        formatted_lines = []

        # Iterate through lines to remove unnecessary tabs
        for line in function_body_lines:
            if line.strip():  # Ignore empty lines
                indentation = len(line) - len(line.lstrip())

                number = indentation - 4*(math.floor(indentation / 4) - 1)
                formatted_lines.append(line[number:])
            else:
                # Preserve empty lines
                formatted_lines.append(line)

        # Reconstruct the function body text
        formatted_text = '\n'.join(formatted_lines[:-1])

        text += "## Snippet " + str(function_list.index(element) + 1) +  ": " + function_name_as_heading + "\n\n```kotlin\n@Test\nfun " + function_name_as_snippet_body + "{\n" + formatted_text + "```\n\n"

    return text

# Function to copy content from source file to destination file
def copy_content(source_kt_path, source_md_path, destination_folder):
    try:
        filename_md = os.path.basename(source_md_path)

        # Construct the paths for destination
        destination_path = os.path.join(destination_folder, filename_md)

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
        print(f"Error copying content: {e}")

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
try:
    # If the branch exists, switch to it
    subprocess.run(["git", "checkout", new_branch_name], check=True)
except subprocess.CalledProcessError:
    # If the branch doesn't exist, create it
    subprocess.run(["git", "checkout", "-b", new_branch_name], check=True)

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
add_command = ["git", "add", "."]
subprocess.run(add_command, check=True)

# Commit and push changes
commit_message = "Upd snippet code at docs"
commit_command = ["git", "commit", "-m", commit_message]
subprocess.run(commit_command, check=True)

command = ["git", "push", "origin", new_branch_name]
subprocess.run(command, check=True)

# Create a pull request
prTitle = "Update snippet code from " + formatted_date
os.system("gh pr create --title '" + prTitle + "' --body '""'")
