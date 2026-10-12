import os
import sys
import xml.etree.ElementTree as ET
import argparse

# Get the absolute path to the parent directory
parent_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(parent_dir)
from get_published_konsist_snapshot_version import SNAPSHOTS_REPOSITORY_URL

def insert_snapshot_repository(file_path):
    _, extension = os.path.splitext(file_path)

    if extension in ['.gradle', '.kts']:
        if extension == '.kts':
            repository = f'maven("{SNAPSHOTS_REPOSITORY_URL}")'
        else:
            repository = f"maven {{ url = '{SNAPSHOTS_REPOSITORY_URL}' }}"

        with open(file_path, 'r') as file:
            lines = file.readlines()

        new_lines = []
        found = False
        for line in lines:
            new_lines.append(line)
            if 'mavenCentral()' in line:
                found = True
                # make sure the indentation of snapshot repository matches with 'mavenCentral()'
                indentation = len(line) - len(line.lstrip(' '))
                new_lines.append(' ' * indentation + repository + '\n')

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
        id_.text = 'central-snapshots'
        url = ET.SubElement(repository, 'url')
        url.text = SNAPSHOTS_REPOSITORY_URL

        tree.write(file_path)
    else:
        print(f"Unsupported file extension: {extension}")

    print(f"Konsist snapshot repository added to {file_path}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    insert_snapshot_repository(args.file_path)
