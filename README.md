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
#####  JSON format - Sample JSON Request:
```
{
  "content": "Sample Json content to compare"
}
```

Application will return JSON responses for each request and generate different json content for each request of Success and Failure requests. 

#####  JSON format - Success Insertion Request:
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
