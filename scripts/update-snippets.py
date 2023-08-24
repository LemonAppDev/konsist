import os
import re
import subprocess
import datetime

def replace_capitals_with_dash_and_lowercase(input_string):
    def replace(match):
        return '-' + match.group(0).lower()

    # Replace capital letters with a dash and lowercase letter
    modified_string = re.sub(r'[A-Z]', replace, input_string)

    # Change the suffix from .kt to .md
    modified_string = modified_string.removeprefix("-") + ".md"

    return modified_string

def upd_file_text(txt):
    def replace_capitals_with_blank_space(input_string):
        def replace(match):
            return ' ' + match.group(0)

        # Replace capital letters with a blank space
        modified_string = re.sub(r'[A-Z]', replace, input_string)

        return "# " + modified_string + "\n\n"

    # formatting: # class name
    file_name = txt.split("class ")[1].split(" {")[0]
    upd_file_name = replace_capitals_with_blank_space(file_name)

    # formatting: function test body
    list = txt.removesuffix("}\n").split("fun ")
    list.pop(0)

    text = ""

    for element in list:
        function_name = element.split("`")[1]
        words = function_name.split(" ")
        words = map(lambda capitalize: capitalize.replace(capitalize, capitalize[0].upper() + capitalize[1:]), words)

        body = element

        name = " ".join(words)
        text += "## " + name + "\n\n```kotlin\n@Test\nfun " + element + "```\n\n"

    return upd_file_name + text
    # trzeba usunąć w function body \t oraz we wszystkich oprócz ostatniego pustą linię


# Function to copy content from source file to destination file
def copy_content(source_path, destination_folder):
    try:
        # Extract the source file name without extension
        source_filename = os.path.basename(source_path)
        source_filename_without_extension = os.path.splitext(source_filename)[0]

        # Transform the source file name to match the destination format
        destination_filename = replace_capitals_with_dash_and_lowercase(source_filename_without_extension)

        # Construct the paths for source and destination
        destination_path = os.path.join(destination_folder, destination_filename)

        with open(source_path, "r") as source_file:
            content = source_file.read()
            text = upd_file_text(content)
            with open(destination_path, "w") as destination_file:
                destination_file.write(text)
    except Exception as e:
        print(f"Error copying content: {e}")

# Directory path
source_directory = "~/IdeaProjects/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
destination_directory = "~/IdeaProjects/konsist-documentation/inspiration/snippets"

expanded_source_directory = os.path.expanduser(source_directory)
expanded_destination_directory = os.path.expanduser(destination_directory)

 # Change the current working directory
os.chdir(expanded_destination_directory)

current_date = datetime.datetime.now().date()
formatted_date = current_date.strftime("%Y-%m-%d")

new_branch_name = formatted_date + "-update-snippet-code"

# Run the git checkout -b command
command = ["git", "checkout", "-b", new_branch_name]
subprocess.run(command, check=True)

# Iterate through all .kt files in the source folder
for filename in os.listdir(expanded_source_directory):
    if filename.endswith("Snippets.kt"):
        kt_path = os.path.join(expanded_source_directory, filename)
        copy_content(kt_path, expanded_destination_directory)

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

prTitle = "Update snippet code from" + formatted_date
os.system("gh pr create --title '" + prTitle + "' --body '""'")
