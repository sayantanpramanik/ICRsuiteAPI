package com.tcs.tcsicr.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tcs.tcsicr.model.RequestBody;
import com.tcs.tcsicr.model.azureAPIcall;
import com.tcs.tcsicr.view.imagesJson;
import com.tcs.tcsicr.view.lineJson;
import com.tcs.tcsicr.view.linesJson;
import com.tcs.tcsicr.view.wordJson;

@Path("/handWritingRecognition") 

public class handWritingRecognition
{
    
    @POST
	@Path("/RecogniseImage")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public static imagesJson imageDigitizer(RequestBody requestBody)throws JSONException, IOException
    {    	
    	imagesJson imagesResponse = new imagesJson(); 
    	linesJson linesResponse = new linesJson();
    	JSONArray jsonResponse = new JSONArray();                                      	
        String imageFormat = requestBody.getImageFormat();        	
        String[] images = requestBody.getImages();
        JSONObject jsonResultForEachImage;
        	
        	for(int i = 0; i < images.length; i++)
        	{        		        		
        		jsonResultForEachImage = new JSONObject();
        		String in = images[i];
        		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
        		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        		File image = new File("image");
        		String status = "";
        		if(imageFormat.equalsIgnoreCase("png") || imageFormat.equalsIgnoreCase("jpeg") )
                {
                	ImageIO.write(img, imageFormat, image);
                }
                else
                {
                	status = "Unsupported Image Format";
                	
                }
        		long size = image.length();
                if(size<= 1024*1024*4)
                {
                	FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);                    
                    JSONObject j1 = azureAPIcall.main(reqEntity);                   
                    status = j1.getString("status");     
                    
                    if(status.equalsIgnoreCase("Succeeded"))
                    {                    	
                		JSONObject j2 = j1.getJSONObject("recognitionResult");
                		JSONArray lines = j2.getJSONArray("lines");
                		                		
                		for (int k = 0; k < lines.length(); k++)
                		{
                			JSONObject lineObject = lines.getJSONObject(k);
                			lineObject.remove("boundingBox");
                			String lineText = lineObject.getString("text");               			
                			lineJson lineResponse=new lineJson();
                			JSONArray word = lineObject.getJSONArray("words");
                			lineResponse.text=lineText;                			
                			for (int l = 0; l < word.length(); l++)
                			{
                				wordJson wordResponse = new wordJson();
                				JSONObject wordObject = word.getJSONObject(l);
                				wordObject.remove("boundingBox");
                				String wordText = wordObject.getString("text");
                				wordResponse.setText(wordText);
                				//System.out.println(wordText);
                				if(wordObject.has("confidence"))
                				{
                					wordResponse.setConfidence("low");
                				}
                				else
                				{
                					wordResponse.setConfidence("high");
                				}
                				lineResponse.words.add(wordResponse);
                			}               			
                			linesResponse.lines.add(lineResponse);
                		}
                		
                		
                		jsonResultForEachImage = j2;
                    }
                    
                    jsonResultForEachImage.put("status", status);
                }
                else 
                {
                	status = "File size is too large";                	                	
                }
                linesResponse.status = status;
                imagesResponse.images.add(linesResponse);
                jsonResponse.put(jsonResultForEachImage);                
        	}        	        	                 
        return imagesResponse;
    }
    
    @POST
    @Path("/RecognisePdf")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public static String pdfDigitizer(String in)
    {
    	StringBuilder result = new StringBuilder();
    	try
    	{
    		byte[] pdfBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
    		File file = new File("pdfFile.pdf");
    		FileOutputStream fop = new FileOutputStream(file);
    		fop.write(pdfBytes);
    		fop.flush();
			fop.close();
    		PDDocument document = PDDocument.load(file);
    		PDFRenderer pdfRenderer = new PDFRenderer(document);
    		for (int page = 0; page < document.getNumberOfPages(); ++page)
    		{
    			BufferedImage img = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
    			File image = new File("image");
    			ImageIO.write(img, "png", image);
    			FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);
                azureAPIcall ob = new azureAPIcall();
                JSONObject j1 = ob.main(reqEntity);
                String r=j1.toString();
                result.append(r);
    		}
    		document.close();
    		return result.toString();
    	}
    	catch(Exception e)
    	{
    		String s3 = e.getMessage()+"	here in pdf";
        	return s3;
    	}
    }
    
    @GET
    @Path("Recognise")
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public String check()
    {
    	return "Fine";
    }    
}
