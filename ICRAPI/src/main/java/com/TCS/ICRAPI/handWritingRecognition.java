package com.TCS.ICRAPI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
//import com.TCS.ICRAPI.model.RequestBody;
//import com.TCS.ICRAPI.model.azureAPIcall;


@Path("/handWritingRecognition") 

public class handWritingRecognition
{
    
    @POST
	@Path("/RecogniseImage")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_ATOM_XML)
    public static String imageDigitizer(RequestBody requestBody) 
    {
    	
    	StringBuilder result = new StringBuilder();
        try 
        {                        
        	
        	String imageFormat = requestBody.getImageFormat();
        	//result.append(imageFormat);
        	String[] images = requestBody.getImages();
        	
        	for(int i = 0; i < images.length; i++)
        	{        		        		
        		String in = images[i];
        		//result.append(in+"\n");
        		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
        		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        		File image = new File("image");
        		if(imageFormat.equalsIgnoreCase("png") || imageFormat.equalsIgnoreCase("jpeg") )
                {
                	ImageIO.write(img, imageFormat, image);
                }
                else
                {
                	result.append("\nUnsupported Image Format");
                	
                }
        		long size = image.length();
                if(size<= 1024*1024*4)
                {
                	FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);
                    //azureAPIcall ob = new azureAPIcall();
                    //String r = ob.main(reqEntity);
                    JSONObject j1 = azureAPIcall.main(reqEntity);
                    //String r = j1.toString();
                    String status = j1.getString("status");
                    //result.append(status);
                    //JSONObject jsonResponse = new JSONObject();
                    if(status.equalsIgnoreCase("Succeeded"))
                    {
                    	//JSONObject j1 = new JSONObject(j);
                		JSONObject j2 = j1.getJSONObject("recognitionResult");
                		JSONArray lines = j2.getJSONArray("lines");
                		//StringBuilder result = new StringBuilder();
                		for (int k = 0; k < lines.length(); k++)
                		{
                			JSONObject im = lines.getJSONObject(k);
                			String text = im.getString("text");
                			result.append("\n"+text);
                		}
                		//return result;
                    
                    }
                    //result.append(r);
                }
                else 
                {
                	result.append("\nFile size is too large");                	                	
                }
        	}
        	return result.toString();
        } 
        catch (Exception e) 
        {
        	String s3 = e.getMessage()+"	here";
        	return s3;
        }
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
