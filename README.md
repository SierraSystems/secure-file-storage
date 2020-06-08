# secure-file-storage

[![Maintainability](https://api.codeclimate.com/v1/badges/bfeb6197269135e0fa52/maintainability)](https://codeclimate.com/github/SierraSystems/secure-file-storage/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/bfeb6197269135e0fa52/test_coverage)](https://codeclimate.com/github/SierraSystems/secure-file-storage/test_coverage)

Proof of concept application to perform secure file uploads and storage to an S3-compatible store while enforcing BC Services Card authentication.

## User Interface

![unauthed](https://user-images.githubusercontent.com/28017034/83955116-771dd200-a804-11ea-9ee8-8503666a0553.PNG)

![authed](https://user-images.githubusercontent.com/28017034/83955118-78e79580-a804-11ea-84c8-99d832213361.PNG)

## Sequence/Flow Diagram

![securefileupload-sequencediagram](https://user-images.githubusercontent.com/28017034/84064048-dbf93980-a976-11ea-864d-cd4477a9fc73.PNG)

## Running the App

```bash
docker-compose up
```

Containers:
| name | description | url |
| --- | --- | --- |
| localstack/s3 | localstack S3 service | [http://localhost:4566](http://localhost:4566) |
| secure-file-storage-api | s3 api gateway | [http://localhost:8056/store](http://localhost:8056/store) |

## Initial Setup

Install the [AWS CLI](https://aws.amazon.com/cli/).

Run:

```bash
aws configure

AWS Access Key ID [None]: 123
AWS Secret Access Key [None]: xyz
Default region name [None]:
Default output format [None]:
```

Create a bucket using the gateway:

```bash
aws --endpoint-url=http://localhost:8056/store s3 mb s3://demo-bucket
```

Attach an [ACL](https://docs.aws.amazon.com/AmazonS3/latest/dev/acl-overview.html) to the bucket so it is readable:

```bash
aws --endpoint-url=http://localhost:8056/store s3api put-bucket-acl --bucket demo-bucket --acl public-read
```

Go to [http://localhost:8055](http://localhost:8055) to check that the bucket has been created.

![dashboard-1](docs/dashboard-1.png)

## Testing the App (Postman)

1. Upload a new file using [postman collection](tests/secure-file-storage.postman_collection.json)

2. Select `Upload a file` request, go to body and click `Select File`

3. Once you have uploaded your file, you should get a 200 OK response

4. Select `Get image` request and execute

5. You should see your image in postman

## Resources

- [How to fake AWS locally with LocalStack](https://dev.to/goodidea/how-to-fake-aws-locally-with-localstack-27me)

- [Testing file uploads to AWS S3 with IAM user credentials in Postman](https://medium.com/@christinavhastenrath/testing-file-uploads-to-aws-s3-with-iam-user-credentials-in-postman-5026fbde3ca6)

- [Filepond documentation](https://pqina.nl/filepond/docs/patterns/api/server/#advanced)

- [AWS S3 Encryption](https://docs.aws.amazon.com/AmazonS3/latest/dev/bucket-encryption.html)
