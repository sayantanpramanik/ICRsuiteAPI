package com.TCS.ICRsuiteAPI;

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
                    azureAPIcall ob = new azureAPIcall();
                    String r = ob.main(reqEntity);
                    result.append("\n"+r);
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
                String r = ob.main(reqEntity);
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
