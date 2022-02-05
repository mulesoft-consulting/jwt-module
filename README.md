# jwt-module

## Introduction

This repository contains a module that extends Mule 4 to simplify the generation of a signed [JSON Web Token](https://en.wikipedia.org/wiki/JSON_Web_Token).

Once incorporated into your Anypoint Studio project, it provides a single "Sign" component which allows for simple configuration.

## Installation

Download the release JAR file and load it into your Anypoint Studio Maven repository. Once present, you can add a dependency to your pom.xml to incorporate the module into your Mule project:

```xml
<dependency>
  <groupId>uk.org.mule.jwt</groupId>
  <artifactId>jwt-module</artifactId>
  <version>0.3.0</version>
  <classifier>mule-plugin</classifier>
</dependency>
```

## Usage

### Appearance

![JWT Flow](/images/jwt-flow.png)

### Component

The "Sign" component can be placed into your flow like any other component, and allows you to specify the source for the JSON-formatted `Header` and `Payload` parts of the [JWT structure](https://en.wikipedia.org/wiki/JSON_Web_Token#Structure):

![Sign parameters](/images/sign-parameters.png)

### Configuration

The component also requires that an associated configuration is defined that specifies:

+ The cryptographic algorithm to be used for signing
+ The location of the `Private Key File` to be used during signing
    + It is recommended to make use of the `${mule.home}` and `${app.name}` properties to avoid hardcoding

![Configuration](/images/config-parameters.png)

The signed token returned can be used however you wish, but would typically be incorporated into the `Authorization` header of a subsequent HTTP(S) request.

## Errors Raised

The Sign component can result in one of the following errors occuring, if mis-configured:

+ JWT:FILE_NOT_FOUND
    + If the path to the `Private Key File` does not resolve to an existing file
+ JWT:INVALID_KEY
    + If the algorithm selected and the private key identified do not align, or the private key is not [appropriately formatted](#notes)
+ JWT:IO_ERROR
    + If some other error occurs during the process of reading the `Private Key File`

## Dependencies

This module makes use of the following 3rd party libraries:

+ The [jwt.io](https://jwt.io/) Java JWT library, [jjwt](https://github.com/jwtk/jjwt)
+ The [org.bouncycastle](https://www.bouncycastle.org/) cryptography library

## Notes

This module makes use of the BouncyCastle [PEMParser](https://www.bouncycastle.org/docs/pkixdocs1.5on/org/bouncycastle/openssl/PEMParser.html) to parse the `Private Key File`, which expects the private key used for signing to be supplied in [PEM](https://en.wikipedia.org/wiki/Privacy-Enhanced_Mail) format.
If you are not sure whether your key meets this requirement, it is recommended to convert it into the [PKCS #8](https://en.wikipedia.org/wiki/PKCS_8) format, using the [`openssl`](https://www.openssl.org/docs/man1.1.1/man1/openssl-pkcs8.html) command line or via online tools such as those at [8gwifi.org](https://8gwifi.org/).