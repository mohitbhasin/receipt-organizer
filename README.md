# Receipt Organizer

This is the backend service for Receipt Organizer. The project digitize images of physical receipts and stores the content for viewing later.

## Project Description

The uploaded image gets scanned for list of items having prices and stores them in database. Upon selecting the receipt title from the UI, itemized list of products is displayed with option to discard.

Java Spring Boot Framework has been used for the development as it lets to create standalone applications with features like dependency injection and embedded server for web development. Maven is used as a build tool for managing dependencies.

Some of the challenges faced during the developing were:
- Reading path of the image file from the client.
- Configuring tesseract api with tessdata.

Features for the future:
- Adding search functionality for the items.
- Adding pagination on the data.

![Image](https://github.com/mohitbhasin/receipt-organizer-ui/blob/develop/upload.gif?raw=true)

## How to run the project
### Prerequisites
- Make sure frontend service is up and running (link -https://github.com/mohitbhasin/receipt-organizer-ui)
- Make sure environment variable TESSDATA_PREFIX is set to the directory with eng.traineddata. (link - https://github.com/tesseract-ocr/tessdata/blob/main/eng.traineddata)
```
export TESSDATA_PREFIX=<dir-of-eng.traineddata>
```
### Setup, Build and Run
- Clone the repository, build dependencies and start the application:
```
$git clone https://github.com/mohitbhasin/receipt-organizer.git

$cd receipt-organizer

$mvn clean install

$cd target

$java -jar receiptOrganizer<version>.jar
```
## How to use the project
- Click the browse button to select the image of the receipt to upload.
- The dialog will appear with the list of items that have been read. Choose to accept or delete.
- Historical receipts can be viewed by selecting desired title from the table.