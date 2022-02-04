# jwt-module
## Introduction

This repository contains a module that extends Mule 4 to simplify the generation of a signed [JSON Web Token](https://en.wikipedia.org/wiki/JSON_Web_Token) used in many REST APIs.
Once incorporated into your Anypoint Studio project, it provides a single Mule component which allows for simple configuration.

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

![JWT Flow](/images/jwt-flow.png)

The "Sign" component can be placed into your flow like any other component, and allows you to specify the source for the JSON-formatted `header` and `payload` parts of the [structure](https://en.wikipedia.org/wiki/JSON_Web_Token#Structure):

![Sign parameters](/images/sign-parameters.png)

The component also requires that an associated configuration is defined that specifies:

+ The cryptographic algorithm to be used for signing
+ The location of the PKCS#8-encoded private key to be used during signing
    + It is recommended to make use of the `${mule.home}` and `${app.name}` properties to avoid hardcoding

![Configuration(/images/config-parameters.png)

The signed token returned can be however you wish, but would typically be incorporated into the `Authorization` header of a subsequent HTTP(S) request.