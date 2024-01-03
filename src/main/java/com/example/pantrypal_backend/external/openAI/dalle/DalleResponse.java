package com.example.pantrypal_backend.external.openAI.dalle;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DalleResponse (
        @JsonProperty Long created,
        @JsonProperty ImgUrl[] data
        /*
        {
  "created": 1589478378,
  "data": [
    {
      "url": "https://..."
    },
    {
      "url": "https://..."
    }
  ]
}
         */
){
}
