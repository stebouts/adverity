# description
This is a simple spring boot application 

# configuration
Go to application properties file located in the resources dir and set the appropriate values to the below properties. Database creation takes places automatically   
spring.datasource.url

spring.datasource.username

spring.datasource.password
spring.datasource.password

# execution
Build the project and execute the following
java -jar data-warehouse-0.0.1-SNAPSHOT 

# Request
The Datawarehouse controller offers a `/campaignData/get' endpoint
 which can be consumed via a `GET` *request* and the following parameters:


## *metrics*
Parameter: `metrics` 
Type: `mandatory` 
Default value: `clicks_total,impressions_total,ctlr_total` 
Data type: `List<String>`
Metrics to be returned. Allowed values are: `total_clicks`, `total_impressions`, `total_ctr`

## *aggregations*
Parameter: `aggregations`
Type: `optional` 
Data type: `List<String>`
Allowed values are: `datasource`, `campaign`, `date`

## *datasources*
Parameter: `datasources`
Type: `optional`
Data type: `List<String>`
Allowed values are: a list of campaign datasources

## *campaigns*
Parameter: `campaigns`
Type: `optional`
Data type: `List<String>`
Allowed values are: a list of campaigns

## *fromDate*
Parameter: `fromDate`
Type: `optional`
Data type: `Date`
Filter only records with dates equal or later than
the passed date. Use YYYY-mm-dd format.

## *toDate*
Parameter: `fromDate`
Type: `optional`
Data type: `Date`
Filter only records with dates equal or earlier than
the passed date. Use YYYY-mm-dd format.


# Response

The response contains the following:
|List<Record>|The actual list of records. The fields will vary depending
on the *metrics* and *aggregations* defined in the request. The general idea is what you ask is what you get in the response
|===


== Test Queries

Below are some example queries.

=== Total Clicks for a given Datasource for a given Date range

Request (http://{host}:{port}/campaignData/get?metrics=clicks_total&datasources=Facebook%20Ads&fromDate=2019-01-01&toDate=2019-01-02&aggregations=datasource


[source,http]
----

GET http://{host}:{port}/campaignData/ge
   ?metrics=clicks_total
   &aggregations=datasource
   &datasources=Google%20Ads
   &fromDate=2019-01-01
   &toDate=2019-01-02
   
----

Response
[source,json]
----
{ [
    [
    "Google Ads",
    209
    ]
  ]
}
----

=
== Architecture

The application consists of two major components:

1. The ETL job which consumes data from a CSV file into a Mysql database
2. The Query service which queries the Mysql database

=== ETL Job

The ETL job is a simple camel file route which consumes the files from a certain directory and adds the rows to mysql.
When done the file is moved to the .done directory located in the same dir of the csv file.

The job is executed on application startup and the table is created on startup.
In order to configure the job you have to set the following parameters:

Parameter: file.consumer.directory

Description: The directory the csv is located 

Parameter: file.poll.delay

Description: The delay after each poll on the directory in milliseconds