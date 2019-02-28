package com.TCS.ICRAPI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/dummyRecognition") 
public class dummyForm 
{
	@POST
	@Path("/dummyImage")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static dummyImageResponse main(dummyRequest DummyRequest) throws IOException, JSONException
	{
		dummyImageResponse response = new dummyImageResponse();
		String status = "", message = ""; int maxImageMB = 4;
		int x = 0;
        String fields[] = new String[1];
        String[] fieldTags = new String[1];
        String formType = DummyRequest.formType;
        if(formType.equalsIgnoreCase("dummyForm"))
        {
        	fields = dummyFormFields.fields;
            fieldTags = dummyFormFields.fieldTags;
        }
		String imageFormat = DummyRequest.imageFormat;
		String in = DummyRequest.images[0];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		//final ClassLoader loader = dummyForm.class.getClassLoader();
		//String path = System.getProperty("java.io.tmpdir");
		//String path = new File(".").getCanonicalPath()+"\"+""+"image";
		File image = new File("image");
		//System.out.println(path);
		if(imageFormat.equalsIgnoreCase("png") || imageFormat.equalsIgnoreCase("jpeg") || imageFormat.equalsIgnoreCase("jpg"))
		{
			ImageIO.write(img, imageFormat, image);
			long size = image.length();
			if(size<= 1024*1024*maxImageMB)
			{
				FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);                    
		        JSONObject j1 = azureAPIcall.main(reqEntity);
		        status = j1.getString("status");
		        //response.dummyStatus = status;
		        
		       
		        if(status.equalsIgnoreCase("Succeeded"))
		        {
		        	JSONObject j2 = j1.getJSONObject("recognitionResult");
		    		JSONArray lines = j2.getJSONArray("lines");
		    		//System.out.println(j2.toString());
		    		while(x <= fields.length-1)
		    		{
		    			
		    			for (int k = 0; k < lines.length(); k++)
		        		{
		        			JSONObject lineObject = lines.getJSONObject(k);
		        			lineObject.remove("boundingBox");
		        			String lineText = lineObject.getString("text");
		        			String text = "", confidence = "high";
		        			int isThere = searchSubstring.isSubstring(fieldTags[x],lineText);
		        			if(isThere!=-1)
		        			{
		        				fieldsJson f = new fieldsJson();
		        				int y = 0;
		        				
		        				if (x == fields.length-1)
		        				{
		        					y = lines.length();
		        				}
		        				else
		        				{
		        					for (int l = (k+1); l < lines.length(); l++)
		            				{
		            					JSONObject lineObject1 = lines.getJSONObject(l);
		            					lineObject1.remove("boundingBox");
		            	    			String lineText1 = lineObject1.getString("text");
		            	    			int isThere1 = searchSubstring.isSubstring(fieldTags[x+1],lineText1);
		            	    			if(isThere1!=-1)
		            	    			{
		            	    				y = l;
		            	    				break;
		            	    			}
		            	    			if(l == lines.length()-1)
		            	    			{
		            	    				y = lines.length()-1;
		            	    			}
		            				}
		        				}
		        				for (int m = k; m < y; m++)
		        				{
		        					JSONObject lineObject2 = lines.getJSONObject(m);
		        					String lineText2 = lineObject2.getString("text");
		        					text = text+" _"+lineText2;
		        					JSONArray word = lineObject.getJSONArray("words");
		        					for (int n = 0; n < word.length(); n++)
		        					{
		        						JSONObject wordObject = word.getJSONObject(n);
		        						if(wordObject.has("confidence"))
		                				{
		                					confidence = "low";
		                					break;
		                				}
		        					}
		        					int isAt = searchSubstring.isSubstring(fieldTags[x], text);
		        					if(isAt > -1)
		        					{
		        						//text = cleanText.clean(text.substring(isAt));
		        						text = text.substring(isAt);
		        					}
		        				}
		        				f.key = fields[x];
		        				if(fields[x].equalsIgnoreCase("FIRST NAME :") || fields[x].equalsIgnoreCase("LAST NAME :"))
		        				{
		        					text = cleanField.cleanNameField(text);
		        				}
		        				if(fields[x].equalsIgnoreCase("CONTACT NO :"))
		        				{
		        					text = cleanField.cleanContactNumberField(text);
		        					if(text.equalsIgnoreCase("Invalid Contact Number"))
		        					{
		        						message = message+"\n"+text;
		        					}
		        					else
		        					{
		        						confidence = "high";
		        					}
		        				}
		        				if(fields[x].equalsIgnoreCase("AGE :"))
		        				{
		        					text = cleanField.cleanAge(text);
		        					if(text.equalsIgnoreCase("Invalid Age"))
		        					{
		        						message = message+"\n"+text;
		        					}
		        				}
		        				if(fields[x].equalsIgnoreCase("GENDER :"))
		        				{
		        					text = GetGender.main(text);
		        					if(text.equalsIgnoreCase("Gender Not Detected Properly"))
		        					{
		        						message = message+"\n"+text;
		        					}
		        					else
		        					{
		        						confidence = "high";
		        					}
		        				}
		        				if(fields[x].equalsIgnoreCase("MARRITAL STATUS :"))
		        				{
		        					text = GetMaritalStatus.main(text);
		        					if(text.equalsIgnoreCase("Marital Status Not Detected Properly"))
		        					{
		        						message = message+"\n"+text;
		        					}
		        					else
		        					{
		        						confidence = "high";
		        					}
		        				}
		        				if(fields[x].equalsIgnoreCase("DOB :"))
		        				{
		        					text = cleanField.cleanDOB(text);
		        					if (text.equalsIgnoreCase("Invalid Date"))
		        					{
		        						message = message+"\n"+text;
		        					}
		        					else
		        					{
		        						confidence = "high";
		        					}
		        					//text = cleanText.removeSpaces(text);
		        				}
		        				if(fields[x].equalsIgnoreCase("ADDRESS :"))
		        				{
		        					text = cleanField.cleanAddress(text);
		        				}
		        				f.value = text;
		        				f.confidence = confidence;
		        				response.dummyLines.add(f);
		        			}
		        		}
		    			x++;
		    		}
		        }
			}
			else
			{
				status = "Failed";
				message = "Image Size is too Large";
			}
		}
		else
		{
			status = "Failed";
			message = "Unsupported Image Format";
		}
		
        response.dummyStatus = status;
        response.message = message;
        return response;
	}
}
