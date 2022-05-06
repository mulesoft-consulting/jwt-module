# jwt-module

## Introduction

JSON Web Token (JWT) is an open standard ([RFC 7519](https://tools.ietf.org/html/rfc7519)) for securely transmitting information between parties as a JSON object.
This information can be verified and trusted because it is digitally signed.
When tokens are signed using public/private key pairs, the signature also certifies that only the party holding the private key is the one that signed it.

To-date, generating a signed JWT has involved writing code, sometimes in DataWeave or often Java, that completes the tasks of:

+ Encoding the JSON-formatted header information
+ Encoding the JSON-formatted payload information
+ Constructing the signature by applying a cryptographic algorithm
+ Combining the results into a Base64url encoded final result

This repository contains a module that extends Mule 4 to simplify this task, and remove the need for coding effort.

## Deploying to Exchange

It is recommended to deploy the module to [Anypoint Exchange](https://docs.mulesoft.com/exchange/), to make it available within your organization.

To do this follow the steps below:

1. Clone this repository to your local machine
2. Open a terminal window, and navigate to the root directory of the repository
3. Identify your Anypoint Platform Organization ID, see [this description](https://help.mulesoft.com/s/article/How-to-know-my-Organization-ID-Org-ID-on-the-Anypoint-Platform) if you're not sure how to do this
4. Execute the following command in your terminal window, replacing `<YOUR_ORG_ID>`
> `./deploy.sh <YOUR_ORG_ID>`

Please ensure that your Maven `settings.xml` file has been configured with the correct Anypoint Exchange credentials.

## Usage

Once the module has been deployed to Exchange, you can add it to your project by following the steps outlined at [https://docs.mulesoft.com/studio/7.12/add-modules-in-studio-to](https://docs.mulesoft.com/studio/7.12/add-modules-in-studio-to) to make it available in your Mule Palette.

![Mule Palette](/images/mule-palette.png)

### Appearance

The "Sign" component can then be placed into your flow like any other component.

![JWT Flow](/images/jwt-flow.png)

### Component

The "Sign" component allows you to specify the source for the JSON-formatted `Header` and `Payload` parts of the [JWT structure](https://en.wikipedia.org/wiki/JSON_Web_Token#Structure):

![Sign parameters](/images/sign-parameters.png)

### Configuration

The component also requires that an associated configuration is defined that specifies:

+ The cryptographic algorithm to be used for signing
+ The location of the `Private Key File` to be used during signing
    + It is recommended to make use of the `${mule.home}` and `${app.name}` properties to avoid hardcoding
+ The passphrase that was used to encrypt the private key (optional)

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
+ The [org.bouncycastle](https://javadoc.io/doc/org.bouncycastle/bcpkix-jdk15on/latest/index.html) cryptography library

## Notes

This module makes use of the BouncyCastle [PEMParser](https://www.bouncycastle.org/docs/pkixdocs1.5on/org/bouncycastle/openssl/PEMParser.html) to parse the `Private Key File`, which expects the private key used for signing to be supplied in [PEM](https://en.wikipedia.org/wiki/Privacy-Enhanced_Mail) format.
Some example private keys, used during automated testing, can be found in this repository at [src/test/resources/cert](https://github.com/mulesoft-catalyst/jwt-module/tree/main/src/test/resources/cert).

If you are not sure whether your key meets this requirement, it is recommended to convert it into the [PKCS #8](https://en.wikipedia.org/wiki/PKCS_8) format, using the [`openssl`](https://www.openssl.org/docs/man1.1.1/man1/openssl-pkcs8.html) command line or via online tools such as those at [8gwifi.org](https://8gwifi.org/).