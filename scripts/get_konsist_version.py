import os

def get_konsist_version():
    current_script_path = os.path.abspath(__file__)
    current_script_directory = os.path.dirname(current_script_path)
    project_root_directory = os.path.dirname(current_script_directory)

    gradle_properties_path = project_root_directory + "/gradle.properties"

    with open(gradle_properties_path, 'r') as file:
        for line in file:
            if line.startswith('konsist.version'):
                return line.split('=')[1].strip() + "-SNAPSHOT"
    return None

if __name__ == "__main__":
    print(f'Konsist version: {get_konsist_version()}')
