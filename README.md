# testBackend

This is a Maven application. In order to run the tests, you need to do a mvn test.

The url of the implementation is http://127.0.0.1:8080/rest/bank-transaction/deposit. You need to run as a Java application using Java 8.

An example of POST call body:

{
    "result": "OK",
    "origin": {
        "sortCode": "01-01-12",
        "accountNumber": "43322122"
    },
    "destination": {
        "sortCode": "01-01-13",
        "accountNumber": "43322123"
    }
}
