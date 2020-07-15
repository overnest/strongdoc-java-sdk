# StrongDoc Java SDK
The StrongDoc Java SDK for interacting with the StrongDoc API.
It allows users to add documents to the search index and make the search indexes searchable through API.

## Quickstart
The SDK can be obtained by adding it as a maven dependency, or cloning the source into your project.

## Setup Requirements
1. Requires Java 1.8

### PGP Signature 
A PGP signature is required for each artifact release that is uploaded to Maven.  Therefore, a PGP key is needed.
Please install PGP (https://central.sonatype.org/pages/working-with-pgp-signatures.html) and distribute the public key to a key server.  
There are several key servers.  Some of them are pgp.mit.edu, keyserver.ubuntu.com, and keys.openpgp.org
For Mac users, a GPG Suite (https://gpgtools.org/) can be used.
Please follow https://maven.apache.org/plugins/maven-gpg-plugin/usage.html to configure passphrase in ~/.m2/settings.xml

On Mac, you may see the following error: "gpg: signing failed: Inappropriate ioctl for device". 
This is the solution: https://gist.github.com/repodevs/a18c7bb42b2ab293155aca889d447f1b

### A sample of settings.xml
```
<settings xmlns="http://maven.apache.org/settings/1.0.0">
  <servers>
    <server>
      <id>ossrh</id>
      <!-- Your sonatype username -->
      <username>xxxxx</username>
      <!-- Your sonatype password -->
      <password>xxxxx</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>ossrh</id>
      <properties>
        <gpg.executable>gpg2</gpg.executable>
        <!-- Your Key Password -->
        <gpg.passphrase>xxxxx</gpg.passphrase>
        <!-- If not using the default key do the following -->
        <gpg.keyname>xxxxx</gpg.keyname>
        <gpg.passphraseServerId>${gpg.keyname}</gpg.passphraseServerId>
        <gpg.defaultKeyring>false</gpg.defaultKeyring>
      </properties>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>ossrh</activeProfile>
  </activeProfiles>
</settings>
```

### How to Tag and Release
The pom.xml should be auto updated with the snapshot and next release version.
To Prepare and Release:
```
mvn clean
mvn release:prepare
mvn release:perform