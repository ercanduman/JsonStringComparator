# Json String Comparator

This is a demo Spring Boot application in order to compare 2 JSON Strings.

## Requirements

1. Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints.

    `<host>/v1/diff/<ID>left` and `<host>/v1/diff/<ID>right`

1. The provided data needs to be diff-ed and the results shall be available on a third endpoint.

    `<host>/v1/diff/<ID>`

1. The results shall provide the following info in JSON format
	- If equal return that
	- If not of equal size just return that
	- If of same size provide insight in where the diffs are, actual diffs are not needed. So mainly offsets + length in the data

## Must haves
- Solution written in Java
- Internal logic shall be under unit test
- Functionality shall be under integration test
- Documentation in code
- Clear and to the point readme on usage

## Solution
Within this requirement, a new Spring Boot application created from scratch.

The main purpose of this demo application is to retrieve JSON strings from "/left" and "/right" endpoints, insert these data into local database and return JSON result by comparing them.


#### JSON Format
Client requests should be in JSON format. If not, then "415 Unsupported Media Type" exception will be thrown.
##### JSON format - Sample JSON Request:
```
{
  "content": "Sample JSON content to compare"
}
```

Application will return JSON responses for each request and generate different JSON content for each request of Success and Failure requests. 

##### JSON format - Success Insertion Request:
```
{
  "error": false,
  "message": "Inserted successfully."
}
```
##### JSON format - Sample Failure Request:
```
{
  "error": true,
  "message": "Invalid JSON found! Error: Unrecognized token 'content': was expecting (JSON String, ..."
}
```

##### JSON format - Sample Success Request:
```
{
  "error": false,
  "message": "Objects are equal"
}
```
##### JSON format - Sample Success Request with Differences:
```
{
  "error": false,
  "message": "Execution successful.",
  "Differences": [
    {
      "offset": 22,
      "left char": 5,
      "right char": 6
    }
  ]
}
```


## Usage

An executable war file created and placed under project source folder /target directory.
In order to execute module and start web server, run below command in terminal.

`$ java -jar JsonStringComparator.war`

While application is running, typing `CTRL + C` in terminal will halt the server and stop execution of application.

Service will run and can be available in below url:
http://localhost:8080/v1/diff/1

In first run of application, default JSON response will be as:
```
{
  "error": false,
  "message": "Both objects are NULL"
}
```

## Testing
As a testing tool Postman is highly recommended to be used for making HTTP requests.

Internal logic is under **unit test** which is available in classes of FakeJsonObjectDaoImplTest, JsonComparatorTest and JsonResponseCreatorTest.


Functionality is under **integration test** which is available in classes of RestControllerTest and JsonObjectServiceTest.


### Testing insertion
Steps;
- Go to /target directory
- Run application via command line: `	$ java -jar JsonStringComparator.war`
<img src="https://user-images.githubusercontent.com/11629459/95589630-aad86500-0a4d-11eb-9a99-b030a3fb481a.png" title="Figure 5: Swipe to Delete Feature">
 
- Start Postman and create http request with below characteristics

    | Request type | URL |
    | :------------: | :------------: |
    |   POST | http://localhost:8080/v1/diff/1/left |
    
    | BODY | Media Type |
    | :------------: | :------------: |
    | ` {“content": "Sample JSON content to compare”} ` | JSON |

- Send request and check if execution is successful for insertion.

`{
    "error": false,
    "message": "Inserted successfully."
}`

<img src="https://user-images.githubusercontent.com/11629459/95592609-8e3e2c00-0a51-11eb-85db-b03e81d6fab9.png"  title="Json data successfully Inserted">

### Testing invalid JSON

If invalid JSON data is passed, then application will return a message as shown below.
<img src="https://user-images.githubusercontent.com/11629459/95593217-1290af00-0a52-11eb-8148-15c98efc9b99.png"  title="Invalid Json data cannot be inserted.">



### Testing comparison (Success)

Steps;
- Go to /target directory
- Run application via command line: `	$ java -jar JsonStringComparator.war`
- Start Postman and create POST http request for both /left and /right endpoints.

| Left endpoint | Request body |
| :------------: | :------------: |
| http://localhost:8080/v1/diff/1/left | ` {"content": "Sample JSON content to compare 152”} ` |

| Right endpoint | Request body |
| :------------: | :------------: |
| http://localhost:8080/v1/diff/1/right | ` {"content": "Sample JSON content to compare 987”} ` |




- And lastly send GET http comparison request as shown below with URL of http://localhost:8080/v1/diff/1/
<img src="https://user-images.githubusercontent.com/11629459/95596294-deb78880-0a55-11eb-8a84-9eb6ce969e2d.png"  title="Different JSON strings compared and result returned successfully">


### Technologies
- IntelliJ IDEA 2020.2.3 (Community Edition)
- Runtime version: 11.0.8+10-b944.34 amd64
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
- Windows 10 10.0
- GC: ParNew, ConcurrentMarkSweep
- Memory: 1963M
- Cores: 8
- Non-Bundled Plugins: in.1ton.idea.spring.assistant.plugin
- JUnit 5
- Apache Maven 3.3.9
- Postman v7.34.0 
- Java Development Kit (JDK) 13.0.2

### Documentation in code
For the documentation in code (Java docs) please double click on index.html under /target/docs/javadoc/ directory.

Happy coding! :+1: :1st_place_medal:

## License

[![License](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://github.com/ercanduman/JsonStringComparator/blob/master/LICENSE.md)

JsonStringComparator, Copyright (C) 2020
