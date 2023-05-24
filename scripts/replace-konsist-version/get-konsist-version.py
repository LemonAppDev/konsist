import os

def get_konsist_version():
    current_script_path = os.path.abspath(__file__)
    current_script_directory = os.path.dirname(current_script_path)
    parent_script_directory = os.path.dirname(current_script_directory)
    project_root_directory = os.path.dirname(parent_script_directory)

    gradle_properties_path = project_root_directory + "/gradle.properties"

    with open(gradle_properties_path, 'r') as file:
        for line in file:
            if line.startswith('konsist.version'):
                return line.split('=')[1].strip()
    return None

print(get_konsist_version())