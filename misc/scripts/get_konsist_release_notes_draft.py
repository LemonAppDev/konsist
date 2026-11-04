import json
import os
import subprocess
import sys

from check_konsist_snapshot_version import get_konsist_version

# Prints id of the draft GitHub release (release notes) for the 'konsist.version' (gradle.properties). Fails if the
# draft release with 'v<konsist.version>' tag or title does not exist or has no release notes.
# Title is matched as well, because draft release created without a tag has 'untagged-...' tag name.
#
# Requires 'GH_TOKEN' with 'contents: write' permission (draft releases are not listed for read-only tokens) and
# 'GITHUB_REPOSITORY' environment variables.


def get_releases(repository):
    result = subprocess.run(
        ["gh", "api", "--paginate", "--slurp", f"repos/{repository}/releases?per_page=100"],
        capture_output=True,
        text=True,
        check=True,
    )

    return [release for page in json.loads(result.stdout) for release in page]


def get_release_notes_draft(tag_name, releases):
    matching_releases = [
        release for release in releases
        if release["tag_name"] == tag_name or (release.get("name") or "").strip() == tag_name
    ]

    if not matching_releases:
        return None, (
            f"Draft GitHub release with '{tag_name}' tag or title does not exist. Create draft release with "
            f"'{tag_name}' title and release notes (https://github.com/LemonAppDev/konsist/releases/new), "
            f"then re-run this check."
        )

    if len(matching_releases) > 1:
        return None, f"Found {len(matching_releases)} GitHub releases with '{tag_name}' tag or title, keep only one."

    release = matching_releases[0]

    if not release["draft"]:
        return None, f"GitHub release '{tag_name}' is already published ({release['html_url']})."

    if not (release.get("body") or "").strip():
        return None, f"Draft GitHub release '{tag_name}' has no release notes ({release['html_url']})."

    return release, None


if __name__ == "__main__":
    tag_name = f"v{get_konsist_version()}"
    release, error = get_release_notes_draft(tag_name, get_releases(os.environ["GITHUB_REPOSITORY"]))

    if error is not None:
        print(f"Error: {error}", file=sys.stderr)
        sys.exit(1)

    print(f"Found draft GitHub release '{tag_name}': {release['html_url']}", file=sys.stderr)
    print(release["id"])
