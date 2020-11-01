# datanews-java
This repo contains the official Datanews client library for Java.

You can find full documentation on the [official website](https://datanews.io/docs).

## Installation
TODO

## Usage
Here is a small example of how you can use this library to query search API.
```java
NewsParams params = NewsParams.builder()
    .query("US elections")
    .size(25)
    .fromDate(LocalDate.of(2020, 10, 1))
    .build();

NewsResponse data = Datanews
    .search("Your API key")
    .news(params);

System.out.println(data.getArticles());
```
You can also set your API key once and use it later.
```java
Datanews.setGlobalApiKey("Your API key");

NewsParams params = NewsParams.builder()
    .query("US elections")
    .size(25)
    .fromDate(LocalDate.of(2020, 10, 1))
    .build();

NewsResponse data = Datanews
    .search()
    .news(params);

System.out.println(data.getArticles());
```

