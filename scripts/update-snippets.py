import os

# Get the path of the current script
script_path = os.path.abspath(__file__)
print(script_path)

# Get the directory containing the script
script_directory = os.path.dirname(script_path)
print(script_directory)

updated_script_directory = script_directory.removesuffix("/konsist/konsist/scripts")

 # Paths to the source and destination folders
source_folder_path = updated_script_directory + "/konsist/lib/src/snippet/kotlin/com/lemonappdev/konsist"
destination_folder_path = updated_script_directory + "/konsist-documentatio/inspiration/snippets"

# Function to copy content from source file to destination file
def copy_content(source_path, destination_path):
    try:
        with open(source_path, "r") as source_file:
            content = source_file.read()
            with open(destination_path, "w") as destination_file:
                destination_file.write(content)
    except Exception as e:
        print(f"Error copying content: {e}")

# Iterate through all .kt files in the source folder
for filename in os.listdir(source_folder_path):
    if filename.endswith(".kt"):
        kt_path = os.path.join(source_folder_path, filename)
        md_filename = filename.replace(".kt", ".md")
        md_path = os.path.join(destination_folder_path, md_filename)
        copy_content(kt_path, md_path)

os.system("git push -u origin " + "Update snippets")
os.system("gh pr create --draft --title '" + "Update snippets" + "' --body '""'")
