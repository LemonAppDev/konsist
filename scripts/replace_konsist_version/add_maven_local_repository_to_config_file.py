import os
import xml.etree.ElementTree as ET
import argparse

def insert_maven_local(file_path):
    _, extension = os.path.splitext(file_path)

    if extension in ['.gradle', '.kts']:
        with open(file_path, 'r') as file:
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

        with open(file_path, 'w') as file:
            file.writelines(new_lines)
    elif extension == '.xml':
        tree = ET.parse(file_path)
        root = tree.getroot()

        # Namespace map to handle namespaces in pom.xml
        namespaces = {'default': 'http://maven.apache.org/POM/4.0.0'}
        ET.register_namespace('', namespaces['default'])

        # Find the <repositories> element, create it if not exist
        repositories = root.find('default:repositories', namespaces)
        if repositories is None:
            repositories = ET.SubElement(root, 'repositories')

        # Create new <repository> element
        repository = ET.SubElement(repositories, 'repository')

        id_ = ET.SubElement(repository, 'id')
        id_.text = 'maven-local'
        url = ET.SubElement(repository, 'url')
        url.text = 'file://${user.home}/.m2/repository'

        tree.write(file_path)
    else:
        print(f"Unsupported file extension: {extension}")

    print(f"Konsist repository added to {file_path}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    insert_maven_local(args.file_path)
