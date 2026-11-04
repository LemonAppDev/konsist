import argparse
import subprocess
import sys

from check_konsist_snapshot_version import get_konsist_version, semver_key

# Checks that 'konsist.version' is a valid semver version higher than 'konsist.version' on the base branch, so every
# merge to main (release) increases the version.


def get_base_konsist_version(base_ref):
    result = subprocess.run(
        ["git", "show", f"{base_ref}:gradle.properties"],
        capture_output=True,
        text=True,
        check=True,
    )

    for line in result.stdout.splitlines():
        if line.startswith("konsist.version="):
            return line.split("=", 1)[1].strip()

    raise ValueError(f"'konsist.version' not found in gradle.properties on '{base_ref}'")


def check_konsist_version_increased(konsist_version, base_konsist_version, base_ref):
    konsist_version_key = semver_key(konsist_version)

    if konsist_version_key is None:
        return f"'konsist.version={konsist_version}' is not a valid semver version."

    base_konsist_version_key = semver_key(base_konsist_version)

    if base_konsist_version_key is None:
        return f"'konsist.version={base_konsist_version}' on '{base_ref}' is not a valid semver version."

    if konsist_version_key <= base_konsist_version_key:
        return (
            f"'konsist.version={konsist_version}' must be higher than '{base_konsist_version}' on '{base_ref}'. "
            f"Bump 'konsist.version' in gradle.properties."
        )

    print(f"Konsist version '{konsist_version}' is higher than '{base_konsist_version}' on '{base_ref}'.")
    return None


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("base_ref", help="Git ref of the base branch, e.g. 'origin/main'")
    args = parser.parse_args()

    error = check_konsist_version_increased(
        get_konsist_version(),
        get_base_konsist_version(args.base_ref),
        args.base_ref,
    )

    if error is not None:
        print(f"Error: {error}")
        sys.exit(1)
