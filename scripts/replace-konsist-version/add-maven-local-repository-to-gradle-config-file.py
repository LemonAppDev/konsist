import argparse

def insert_maven_local(file_name):
    with open(file_name, 'r') as file:
        lines = file.readlines()

    new_lines = []
    found = False
    for line in lines:
        new_lines.append(line)
        if 'mavenCentral()' in line:
            found = True
            # make sure the indentation of 'mavenLocal()' matches with 'mavenCentral()'
            indentation = len(line) - len(line.lstrip(' '))
            new_lines.append(' ' * indentation + 'mavenLocal()\n')

    if not found:
        print("No line found containing 'mavenCentral()'")
        
    with open(file_name, 'w') as file:
        file.writelines(new_lines)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    konsist_version = insert_maven_local(args.file_path)
