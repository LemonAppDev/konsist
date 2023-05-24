import argparse
import xml.etree.ElementTree as ET

def insert_maven_local(filename):
    tree = ET.parse(filename)
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

    tree.write(filename)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    args = parser.parse_args()

    konsist_version = insert_maven_local(args.file_path)
