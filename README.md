# ICRsuiteAPI
API to perform ICR on images.
Send JSON request to https://icrsuite.azurewebsites.net/ICRAPI/webapi/handWritingRecognition/RecogniseImage
Structure of JSON request:
{
  "imageFormat": "//format of the images",
  "images": ["//base64 of image1", "//base64 of image2", //as many as required]
}
