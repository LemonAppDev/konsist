# Script used to verify that the library artifact only exposes dependencies from org.jetbrains.kotlin.

import sys
import xml.etree.ElementTree as ET

def check_dependencies(file_path):
    try:
        tree = ET.parse(file_path)
        root = tree.getroot()

        ns = {'mvn': 'http://maven.apache.org/POM/4.0.0'}
        for dependency in root.findall(".//mvn:dependency", ns):
            group_id = dependency.find('mvn:groupId', ns)
            if group_id is not None and group_id.text != 'org.jetbrains.kotlin':
                print(f'Invalid dependency: {group_id.text}')
                return 1

        print('All dependencies are from org.jetbrains.kotlin')
        return 0

    except ET.ParseError as e:
        print(f'Failed to parse XML: {e}')
        return 1
    except Exception as e:
        print(f'Error: {e}')
        return 1

if __name__ == "__main__":
    konsist_version = call_get_konsist_version_script()
    print(konsist_version)
    pom_path = "/home/runner/.m2/repository/com/lemonappdev/konsist/" + konsist_version + "/" + konsist_version + ".pom"

    sys.exit(check_dependencies(pom_path))
