import sys
from jar_analyzer import JarAnalyzer

def has_jar_transitive_dependencies(jar_path):
    analyzer = JarAnalyzer()
    dependencies = analyzer.analyze(jar_path)

    return bool(dependencies.transitive_dependencies)

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Please provide the path to the JAR file as a command-line argument.")
        sys.exit(1)

    jar_path = sys.argv[1]
    has_transitive_dependencies = has_jar_transitive_dependencies(jar_path)

    if has_transitive_dependencies:
        print("The JAR file has transitive dependencies.")
        sys.exit(1)
    else:
        print("The JAR file does not have any transitive dependencies.")
        sys.exit(0)
