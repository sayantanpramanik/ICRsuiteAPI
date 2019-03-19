package com.TCS.ICRAPI;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
public class azureAPIcall 
{
	
    public static JSONObject main(FileEntity reqEntity)throws JSONException
    {
    	String subscriptionKey = "b9cbff9d4c6648b0afebf4485f0eeffd";
        String uriBase = "https://southeastasia.api.cognitive.microsoft.com/vision/v2.0/recognizeText";
        try 
        {            
        	CloseableHttpClient httpTextClient = HttpClientBuilder.create().build();
            CloseableHttpClient httpResultClient = HttpClientBuilder.create().build();;
            //String Path = "C:/Users/alexa/OneDrive/Desktop/TCS/untitled11.png";
        	URIBuilder builder = new URIBuilder(uriBase);

            // Request parameter.
            builder.setParameter("mode", "Printed");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            
            // Request body.           
            //long size = image.length();
            //FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);
            request.setEntity(reqEntity);

            // Call the first REST API method to detect the text.
            HttpResponse response = httpTextClient.execute(request);

            // Check for success.
            if (response.getStatusLine().getStatusCode() != 202) {
                // Format and display the JSON error message.
                HttpEntity entity = response.getEntity();
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("Error :\n");
                //String s = json.toString(2);
                return json;
            }
            String operationLocation = null;            
            // The 'Operation-Location' response header value contains the URI for
            // the second REST API method.
            Header[] responseHeaders = response.getAllHeaders();
            for (Header header : responseHeaders) 
            {
                if (header.getName().equals("Operation-Location")) {
                    operationLocation = header.getValue();
                    break;
                }
            }

            if (operationLocation == null) 
            {
                System.out.println("\nError retrieving Operation-Location.\nExiting.");
                System.exit(1);
            }            
            Thread.sleep(15000);
            // Call the second REST API method and get the response.
            HttpGet resultRequest = new HttpGet(operationLocation);
            resultRequest.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            HttpResponse resultResponse = httpResultClient.execute(resultRequest);
            HttpEntity responseEntity = resultResponse.getEntity();
            JSONObject json1 = new JSONObject();
            if (responseEntity != null) 
            {                
                String jsonString = EntityUtils.toString(responseEntity);
                JSONObject json = new JSONObject(jsonString);
                json1 =  json;
                
            }
            //s2=""+size;
            return json1;
        } 
        catch (Exception e) 
        {
        	String s3 = e.getMessage()+"	Error is in azureAPIcall	";
        	JSONObject j1 = new JSONObject(s3);
        	return j1;
        }
    }

}

