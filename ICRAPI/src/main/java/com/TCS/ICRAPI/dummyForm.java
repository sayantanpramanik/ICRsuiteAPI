package com.TCS.ICRAPI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
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
		int x = 0, w = 0;
		int[] signCoordinates = new int[8];
        String fields[][] = new String[1][1];
        String[][] fieldTags = new String[1][1];
        String formType = DummyRequest.formType;
        if(formType.equalsIgnoreCase("dummyForm"))
        {
        	fields = dummyFormFields.fields;
            fieldTags = dummyFormFields.fieldTags;
        }
		String imageFormat = DummyRequest.imageFormat;
		while(w < fields.length)
		{
			x = 0;
			String in = DummyRequest.images[w];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
			File image = new File("image");
			if(imageFormat.equalsIgnoreCase("png") || imageFormat.equalsIgnoreCase("jpeg") || imageFormat.equalsIgnoreCase("jpg"))
			{
				ImageIO.write(img, imageFormat, image);
				long size = image.length();
				if(size<= 1024*1024*maxImageMB)
				{
					FileEntity reqEntity = new FileEntity(image, ContentType.APPLICATION_OCTET_STREAM);                    
			        JSONObject j1 = azureAPIcall.main(reqEntity);
			        status = j1.getString("status");
			   
			        if(status.equalsIgnoreCase("Succeeded"))
			        {
			        	JSONObject j2 = j1.getJSONObject("recognitionResult");
			    		JSONArray lines = j2.getJSONArray("lines");
			    		//System.out.println(j2.toString());
			    		while(x <= fields[w].length-1)
			    		{
			    			
			    			for (int k = 0; k < lines.length(); k++)
			        		{
			        			JSONObject lineObject = lines.getJSONObject(k);
			        			//lineObject.remove("boundingBox");
			        			String lineText = lineObject.getString("text");
			        			String text = "", confidence = "high";
			        			int isThere = searchSubstring.isSubstring(fieldTags[w][x],lineText);
			        			if(isThere!=-1)
			        			{
			        				fieldsJson f = new fieldsJson();
			        				int y = 0;
			        				
			        				if (x == fields[w].length-1)
			        				{
			        					y = lines.length();
			        				}
			        				else
			        				{
			        					for (int l = (k+1); l < lines.length(); l++)
			            				{
			            					JSONObject lineObject1 = lines.getJSONObject(l);
			            					//lineObject1.remove("boundingBox");
			            	    			String lineText1 = lineObject1.getString("text");
			            	    			int isThere1 = searchSubstring.isSubstring(fieldTags[w][x+1],lineText1);
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
			        					//System.out.println(text);
			        					int isAt = searchSubstring.isSubstring(fieldTags[w][x], text);
			        					if(isAt > -1)
			        					{
			        						//text = cleanText.clean(text.substring(isAt));
			        						text = text.substring(isAt);
			        					}
			        				}
			        				f.key = fields[w][x];
			        				
			        				if(fields[w][x].equalsIgnoreCase("FIRST NAME :") || fields[w][x].equalsIgnoreCase("LAST NAME :"))
			        				{
			        					text = cleanField.cleanNameField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("CONTACT NO :"))
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
			        				if(fields[w][x].equalsIgnoreCase("AGE :"))
			        				{
			        					text = cleanField.cleanAge(text);
			        					if(text.equalsIgnoreCase("Invalid Age"))
			        					{
			        						message = message+"\n"+text;
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("GENDER :"))
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
			        				if(fields[w][x].equalsIgnoreCase("MARRITAL STATUS :"))
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
			        				if(fields[w][x].equalsIgnoreCase("DOB :"))
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
			        				if(fields[w][x].equalsIgnoreCase("ADDRESS :"))
			        				{
			        					text = cleanField.cleanAddress(text);
			        					text.trim();
			        				}
			        				if(fields[w][x].equalsIgnoreCase("SIGNATURE:"))
			        				{
			        					JSONArray coor = (lines.getJSONObject(lines.length()-1)).getJSONArray("boundingBox");
			        					text = "";
			        					for (int n = 0; n < 8; n++)
			        					{
			        						signCoordinates[n] = coor.getInt(n);
			        						text = text+" "+signCoordinates[n];
			        					}
			        					BufferedImage newImg = img.getSubimage(signCoordinates[0], signCoordinates[1], (signCoordinates[2]-signCoordinates[0]), (signCoordinates[5]-signCoordinates[1]));
			        					File of = new File ("image1");
			        					ImageIO.write(newImg, "png", of);
			        					FileInputStream fileInputStreamReader = new FileInputStream(of);
			        					byte[] bytes = new byte[(int)of.length()];
			        					fileInputStreamReader.read(bytes);
			        					String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
			        					text = encodedfile;
			        					fileInputStreamReader.close();
			        				}
			        				if(fields[w][x].equalsIgnoreCase("CLAIM TYPE :"))
			        				{
			        					text = GetClaimType.main(text);
			        					if(text.equalsIgnoreCase("Claim Type Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("HEALTH :"))
			        				{
			        					text = GetHealth.main(text);
			        					if(text.equalsIgnoreCase("Health Condition Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
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
			w++;
		}
		
        response.dummyStatus = status;
        response.message = message;
        return response;
	}
}
