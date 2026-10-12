import argparse
import sys
import time
import urllib.error
import urllib.request
import xml.etree.ElementTree as ET

from check_konsist_snapshot_version import get_konsist_version

# Prints the unique (timestamped) version of the latest Konsist snapshot published to the Central snapshots repository
# e.g. 1.0.0-alpha.1-SNAPSHOT -> 1.0.0-alpha.1-20261013.101530-1

SNAPSHOTS_REPOSITORY_URL = "https://central.sonatype.com/repository/maven-snapshots/"
RETRY_COUNT = 5
RETRY_DELAY_SECONDS = 10


def get_snapshot_metadata(snapshot_version):
    url = f"{SNAPSHOTS_REPOSITORY_URL}com/lemonappdev/konsist/{snapshot_version}/maven-metadata.xml"

    for attempt in range(1, RETRY_COUNT + 1):
        try:
            with urllib.request.urlopen(url, timeout=30) as response:
                return ET.fromstring(response.read())
        except urllib.error.URLError as error:
            if attempt == RETRY_COUNT:
                raise
            print(f"Failed to fetch {url} ({error}), retrying...", file=sys.stderr)
            time.sleep(RETRY_DELAY_SECONDS)


# Snapshot qualifier matches 'konsist.snapshotQualifier' Gradle property used to publish the snapshot
def get_published_konsist_snapshot_version(snapshot_qualifier=None):
    base_version = get_konsist_version()

    if snapshot_qualifier:
        base_version = f"{base_version}-{snapshot_qualifier}"

    snapshot_version = f"{base_version}-SNAPSHOT"
    metadata = get_snapshot_metadata(snapshot_version)

    timestamp = metadata.findtext("versioning/snapshot/timestamp")
    build_number = metadata.findtext("versioning/snapshot/buildNumber")

    if timestamp is None or build_number is None:
        raise ValueError(f"Snapshot metadata of '{snapshot_version}' has no timestamp or build number.")

    return f"{base_version}-{timestamp}-{build_number}"


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--snapshot-qualifier", help="Qualifier of the snapshot version (e.g. 'pr2016').")
    args = parser.parse_args()

    print(get_published_konsist_snapshot_version(args.snapshot_qualifier))
