import re
import argparse

def replace_version(file_path, konsist_version):
    with open(file_path, 'r') as file:
        content = file.read()

    # pattern for gradle files and version catalog module notation (e.g. konsist = "com.lemonappdev:konsist:+")
    module_pattern = r"(com\.lemonappdev:konsist:)([\w.+-]+)"
    module_replacement = r"\g<1>" + konsist_version

    if file_path.endswith(".toml"):
        # pattern for gradle version catalog version entry
        pattern = r"(?m)^(konsist\s*=\s*\")([\w.+-]+)(\")"
        replacement = r"\g<1>" + konsist_version + r"\g<3>"
        new_content = re.sub(module_pattern, module_replacement, re.sub(pattern, replacement, content))
    elif file_path.endswith(".gradle") or file_path.endswith(".kts"):
        new_content = re.sub(module_pattern, module_replacement, content)
    else:
        # pattern for pom.xml
        pattern = r"(<groupId>com\.lemonappdev</groupId>\s*<artifactId>konsist</artifactId>\s*<version>)([\w.-]+)(</version>)"
        replacement = r"\g<1>" + konsist_version + r"\g<3>"
        new_content = re.sub(pattern, replacement, content)

    with open(file_path, 'w') as file:
        file.write(new_content)

    print(f"Konsist version replaced in {file_path}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file_path", help="The path to the file to be modified.")
    parser.add_argument("konsist_version", help="The Konsist version to use (e.g. 1.0.0-alpha.1-20261013.101530-1).")
    args = parser.parse_args()
    replace_version(args.file_path, args.konsist_version)
