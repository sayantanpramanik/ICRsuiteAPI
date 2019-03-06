# ICRsuiteAPI
API to perform ICR on images.

Send JSON request to https://icrsuite.azurewebsites.net/webapi/handWritingRecognition/RecogniseImage

Structure of JSON request:

{
  
  "imageFormat": "//format of the images",
  
  "formType": "dummyForm",
  
  "images": 
            [
              
              "//base64 of image1", 
              
              "//base64 of image2", //as many as required          
            
            ]

}

Example of JSON response for 2 images:

{
    "dummyLines": [
        {
            "confidence": "high",
            "key": "FIRST NAME :",
            "value": "SAYANIAN"
        },
        {
            "confidence": "low",
            "key": "LAST NAME :",
            "value": "PRAMANIK"
        },
        {
            "confidence": "high",
            "key": "DOB :",
            "value": "13/08/1997"
        },
        {
            "confidence": "high",
            "key": "AGE :",
            "value": "21"
        },
        {
            "confidence": "low",
            "key": "ADDRESS :",
            "value": " 2125 IOLLYGUNGE  KOLKATA 700040"
        },
        {
            "confidence": "high",
            "key": "CONTACT NO :",
            "value": "8902229588"
        },
        {
            "confidence": "low",
            "key": "EMAIL ID :",
            "value": " :` _A ma & l _ _ _"
        },
        {
            "confidence": "high",
            "key": "MARRITAL STATUS :",
            "value": "SINGLE"
        },
        {
            "confidence": "high",
            "key": "GENDER :",
            "value": "MALE"
        },
        {
            "confidence": "high",
            "key": "SIGNATURE:",
            "value": "/*base64 of cropped signature*/"
        },
        {
            "confidence": "high",
            "key": "CLAIM TYPE :",
            "value": "CORPORATE"
        },
        {
            "confidence": "high",
            "key": "ID NO :",
            "value": " : _2 35"
        },
        {
            "confidence": "low",
            "key": "HOME PHONE :",
            "value": " :| ||| 8| 6| 2| 4||||| || | | |"
        },
        {
            "confidence": "high",
            "key": "HEALTH :",
            "value": "FIT"
        },
        {
            "confidence": "high",
            "key": "MEDICAL HISTORY :",
            "value": " :"
        }
    ],
    "dummyStatus": "Succeeded",
    "message": ""
}
