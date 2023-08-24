import os
import re

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

        # Replace capital letters with a dash and lowercase letter
        modified_string = re.sub(r'[A-Z]', replace, input_string)

        return "# " + modified_string + "\n\n"

    # to nam daje format nazwa klasy
    file_name = txt.split("class ")[1].split(" {")[0]
    upd_file_name = replace_capitals_with_blank_space(file_name)

    # to nam daje format nazwa klasy +. tekst
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

    file_text = upd_file_name + text

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
#             with open(destination_path, "w") as destination_file:
#                 destination_file.write(text)
    except Exception as e:
        print(f"Error copying content: {e}")





# Get the path of the current script
script_path = os.path.abspath(__file__)

# Get the directory containing the script
script_directory = os.path.dirname(script_path)

updated_script_directory = script_directory.removesuffix("/konsist/scripts")

 # Paths to the source and destination folders
source_folder_path = updated_script_directory + "/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
destination_folder_path = updated_script_directory.removesuffix("/konsist")  + "/konsist-documentation/inspiration/snippets"

# Iterate through all .kt files in the source folder
for filename in os.listdir(source_folder_path):
    if filename.endswith("Snippets.kt"):
        kt_path = os.path.join(source_folder_path, filename)
        copy_content(kt_path, destination_folder_path)

