# ICRsuiteAPI
API to perform ICR on images.

Send JSON request to https://icrsuite.azurewebsites.net/ICRAPI/webapi/handWritingRecognition/RecogniseImage

Structure of JSON request:

{
  
  "imageFormat": "//format of the images",
  
  "images": 
            [
              "//base64 of image1", 
              "//base64 of image2", //as many as required          
            ]

}

Example of JSON response for 2 images:
{
    
    "images": 
    [
        
        {
            "lines": [
                {
                    "text": "JULY 13 1971)",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "JULY"
                        },
                        {
                            "confidence": "high",
                            "text": "13"
                        },
                        {
                            "confidence": "high",
                            "text": "1971)"
                        }
                    ]
                },
                {
                    "text": "DEAR NANCY - HOW ARE",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "DEAR"
                        },
                        {
                            "confidence": "high",
                            "text": "NANCY"
                        },
                        {
                            "confidence": "high",
                            "text": "-"
                        },
                        {
                            "confidence": "high",
                            "text": "HOW"
                        },
                        {
                            "confidence": "high",
                            "text": "ARE"
                        }
                    ]
                },
                {
                    "text": "YOU & BOB - HOPE NEW",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "YOU"
                        },
                        {
                            "confidence": "high",
                            "text": "&"
                        },
                        {
                            "confidence": "high",
                            "text": "BOB"
                        },
                        {
                            "confidence": "high",
                            "text": "-"
                        },
                        {
                            "confidence": "high",
                            "text": "HOPE"
                        },
                        {
                            "confidence": "high",
                            "text": "NEW"
                        }
                    ]
                },
                {
                    "text": "YORK IS NOT UNBEARABLE -",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "YORK"
                        },
                        {
                            "confidence": "high",
                            "text": "IS"
                        },
                        {
                            "confidence": "high",
                            "text": "NOT"
                        },
                        {
                            "confidence": "high",
                            "text": "UNBEARABLE"
                        },
                        {
                            "confidence": "high",
                            "text": "-"
                        }
                    ]
                },
                {
                    "text": "WOULD YOU BE SO KIND",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "WOULD"
                        },
                        {
                            "confidence": "high",
                            "text": "YOU"
                        },
                        {
                            "confidence": "high",
                            "text": "BE"
                        },
                        {
                            "confidence": "high",
                            "text": "SO"
                        },
                        {
                            "confidence": "high",
                            "text": "KIND"
                        }
                    ]
                },
                {
                    "text": "AS TO SEND THE ENCLOSED",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "AS"
                        },
                        {
                            "confidence": "high",
                            "text": "TO"
                        },
                        {
                            "confidence": "high",
                            "text": "SEND"
                        },
                        {
                            "confidence": "high",
                            "text": "THE"
                        },
                        {
                            "confidence": "high",
                            "text": "ENCLOSED"
                        }
                    ]
                },
                {
                    "text": "CARD TO KASPER DAVID",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "CARD"
                        },
                        {
                            "confidence": "high",
                            "text": "TO"
                        },
                        {
                            "confidence": "high",
                            "text": "KASPER"
                        },
                        {
                            "confidence": "high",
                            "text": "DAVID"
                        }
                    ]
                },
                {
                    "text": "FISCHER ( THIS ADDRESS)",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "FISCHER"
                        },
                        {
                            "confidence": "high",
                            "text": "("
                        },
                        {
                            "confidence": "high",
                            "text": "THIS"
                        },
                        {
                            "confidence": "high",
                            "text": "ADDRESS)"
                        }
                    ]
                },
                {
                    "text": "A BELLOWING GIANT WHO",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "A"
                        },
                        {
                            "confidence": "high",
                            "text": "BELLOWING"
                        },
                        {
                            "confidence": "high",
                            "text": "GIANT"
                        },
                        {
                            "confidence": "high",
                            "text": "WHO"
                        }
                    ]
                },
                {
                    "text": "WILL CELEBRATE HIS FIRST",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "WILL"
                        },
                        {
                            "confidence": "low",
                            "text": "CELEBRATE"
                        },
                        {
                            "confidence": "high",
                            "text": "HIS"
                        },
                        {
                            "confidence": "high",
                            "text": "FIRST"
                        }
                    ]
                },
                {
                    "text": "BIRTHDAY ON JULY 29.",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "BIRTHDAY"
                        },
                        {
                            "confidence": "high",
                            "text": "ON"
                        },
                        {
                            "confidence": "high",
                            "text": "JULY"
                        },
                        {
                            "confidence": "high",
                            "text": "29."
                        }
                    ]
                },
                {
                    "text": "ANY CHANCE YOU WILL",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "ANY"
                        },
                        {
                            "confidence": "high",
                            "text": "CHANCE"
                        },
                        {
                            "confidence": "high",
                            "text": "YOU"
                        },
                        {
                            "confidence": "high",
                            "text": "WILL"
                        }
                    ]
                },
                {
                    "text": "RETURN TO EUROPE ?)",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "RETURN"
                        },
                        {
                            "confidence": "high",
                            "text": "TO"
                        },
                        {
                            "confidence": "high",
                            "text": "EUROPE"
                        },
                        {
                            "confidence": "low",
                            "text": "?)"
                        }
                    ]
                },
                {
                    "text": "BEST & BE WELL",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "BEST"
                        },
                        {
                            "confidence": "high",
                            "text": "&"
                        },
                        {
                            "confidence": "high",
                            "text": "BE"
                        },
                        {
                            "confidence": "high",
                            "text": "WELL"
                        }
                    ]
                }
            ],
            "status": "Succeeded"
        },
        {
            "lines": [
                {
                    "text": "Aa Bb Cc Dd Ee Ff Gq Hh I'll",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "Aa"
                        },
                        {
                            "confidence": "high",
                            "text": "Bb"
                        },
                        {
                            "confidence": "high",
                            "text": "Cc"
                        },
                        {
                            "confidence": "high",
                            "text": "Dd"
                        },
                        {
                            "confidence": "low",
                            "text": "Ee"
                        },
                        {
                            "confidence": "high",
                            "text": "Ff"
                        },
                        {
                            "confidence": "high",
                            "text": "Gq"
                        },
                        {
                            "confidence": "high",
                            "text": "Hh"
                        },
                        {
                            "confidence": "low",
                            "text": "I'll"
                        }
                    ]
                },
                {
                    "text": "Ji KK LL Mm Nn. Oo PP Qq Rr SS",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "Ji"
                        },
                        {
                            "confidence": "high",
                            "text": "KK"
                        },
                        {
                            "confidence": "low",
                            "text": "LL"
                        },
                        {
                            "confidence": "high",
                            "text": "Mm"
                        },
                        {
                            "confidence": "low",
                            "text": "Nn."
                        },
                        {
                            "confidence": "high",
                            "text": "Oo"
                        },
                        {
                            "confidence": "high",
                            "text": "PP"
                        },
                        {
                            "confidence": "low",
                            "text": "Qq"
                        },
                        {
                            "confidence": "high",
                            "text": "Rr"
                        },
                        {
                            "confidence": "low",
                            "text": "SS"
                        }
                    ]
                },
                {
                    "text": "It Uu Vv WW XX Yy Z z.`",
                    "words": [
                        {
                            "confidence": "low",
                            "text": "It"
                        },
                        {
                            "confidence": "low",
                            "text": "Uu"
                        },
                        {
                            "confidence": "low",
                            "text": "Vv"
                        },
                        {
                            "confidence": "high",
                            "text": "WW"
                        },
                        {
                            "confidence": "high",
                            "text": "XX"
                        },
                        {
                            "confidence": "high",
                            "text": "Yy"
                        },
                        {
                            "confidence": "high",
                            "text": "Z"
                        },
                        {
                            "confidence": "low",
                            "text": "z.`"
                        }
                    ]
                },
                {
                    "text": "abcdefg hyj k l mn op q r s tur wxy z",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "abcdefg"
                        },
                        {
                            "confidence": "high",
                            "text": "hyj"
                        },
                        {
                            "confidence": "high",
                            "text": "k"
                        },
                        {
                            "confidence": "high",
                            "text": "l"
                        },
                        {
                            "confidence": "high",
                            "text": "mn"
                        },
                        {
                            "confidence": "high",
                            "text": "op"
                        },
                        {
                            "confidence": "high",
                            "text": "q"
                        },
                        {
                            "confidence": "high",
                            "text": "r"
                        },
                        {
                            "confidence": "high",
                            "text": "s"
                        },
                        {
                            "confidence": "high",
                            "text": "tur"
                        },
                        {
                            "confidence": "high",
                            "text": "wxy"
                        },
                        {
                            "confidence": "high",
                            "text": "z"
                        }
                    ]
                },
                {
                    "text": "01 2 3 4 5 6 7 8 910",
                    "words": [
                        {
                            "confidence": "high",
                            "text": "01"
                        },
                        {
                            "confidence": "high",
                            "text": "2"
                        },
                        {
                            "confidence": "high",
                            "text": "3"
                        },
                        {
                            "confidence": "high",
                            "text": "4"
                        },
                        {
                            "confidence": "high",
                            "text": "5"
                        },
                        {
                            "confidence": "high",
                            "text": "6"
                        },
                        {
                            "confidence": "high",
                            "text": "7"
                        },
                        {
                            "confidence": "high",
                            "text": "8"
                        },
                        {
                            "confidence": "high",
                            "text": "910"
                        }
                    ]
                }
            ],
            "status": "Succeeded"
        
        }
    
    ]
}
