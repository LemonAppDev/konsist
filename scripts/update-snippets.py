import os
import re
import subprocess
import datetime
import math

def upd_file_text(txt):
    # formatting: function text body
    x = txt.removesuffix("}\n") + "\n\t"
    list = x.split("fun ")
    list.pop(0)

    text = ""

    for element in list:
        function_name_as_heading = element.split("`")[1]
        words = function_name_as_heading.replace("'", "`").split(" ")
        words = map(lambda capitalize: capitalize.replace(capitalize, capitalize[0].upper() + capitalize[1:]), words)

        function_text = element.split("{", maxsplit = 1)
        function_name = function_text[0]

        function_body = function_text[1].removeprefix("\n")

        # Split the input text into lines
        lines = function_body.split('\n')

        # Initialize variables
        formatted_lines = []

        # Iterate through lines
        for line in lines:
            if line.strip():  # Ignore empty lines
                indentation = len(line) - len(line.lstrip())

                number = indentation - 4*(math.floor(indentation / 4) - 1)
                formatted_lines.append(line[number:])

            else:
                # Preserve empty lines
                formatted_lines.append(line)

        # Reconstruct the formatted text
        formatted_text = '\n'.join(formatted_lines[:-1])

        name = " ".join(words)
        text += "## Snippet " + str(list.index(element) + 1) +  ": " + name + "\n\n```kotlin\n@Test\nfun " + function_name + "{\n" + formatted_text + "```\n\n"

    return text

# Function to copy content from source file to destination file
def copy_content(source_kt_path, source_md_path, destination_folder):
    try:
        filename_md = os.path.basename(source_md_path)

        # Construct the paths for source and destination
        destination_path = os.path.join(destination_folder, filename_md)

        with open(source_md_path, "r") as source_file:
            md_content = source_file.read()
            if md_content.splitlines()[-1] != "":
                md_content += "\n"

        with open(source_kt_path, "r") as source_file:
            kt_content = source_file.read()
            kt_text = upd_file_text(kt_content)


        with open(destination_path, "w") as destination_file:
            destination_file.write(md_content+kt_text)

    except Exception as e:
        print(f"Error copying content: {e}")

# Directory path
source_kt_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
source_md_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist/md"
destination_directory = "~/IdeaProjects/konsist-documentation/inspiration/snippets"

expanded_source_md_directory = os.path.expanduser(source_md_directory)
expanded_source_kt_directory = os.path.expanduser(source_kt_directory)
expanded_destination_directory = os.path.expanduser(destination_directory)

 # Change the current working directory
os.chdir(expanded_destination_directory)

current_date = datetime.datetime.now().date()
formatted_date = current_date.strftime("%Y-%m-%d")

new_branch_name = formatted_date + "-update-snippet-code"

# Check if the branch exists
try:
    # If the branch exists, switch to it, do something, and push to origin main
    subprocess.run(["git", "checkout", new_branch_name], check=True)
except subprocess.CalledProcessError:
    # If the branch doesn't exist, create it
    subprocess.run(["git", "checkout", "-b", new_branch_name], check=True)

# Iterate through all .md files in the source folder
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

commit_message = "Upd snippet code at docs"
# Run git commit command
commit_command = ["git", "commit", "-m", commit_message]
subprocess.run(commit_command, check=True)

# Run the git push command
command = ["git", "push", "origin", new_branch_name]
subprocess.run(command, check=True)

prTitle = "Update snippet code from " + formatted_date
os.system("gh pr create --title '" + prTitle + "' --body '""'")
