package com.TCS.ICRAPI;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.json.stream.JsonParsingException;
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
	public static dummyImageResponse main(dummyRequest DummyRequest) throws IOException, JSONException, JsonParsingException
	{
		dummyImageResponse response = new dummyImageResponse();
		String status = "", message = ""; int maxImageMB = 4;
		int x = 0, w = 0;
		int[] signCoordinates = new int[8];
        String fields[][] = new String[1][1];
        String[][] fieldTags = new String[1][1];
        String formType = DummyRequest.formType;
        /*
        if(formType.equalsIgnoreCase("dummyForm"))
        {
        	fields = dummyFormFields.fields;
            fieldTags = dummyFormFields.fieldTags;
        }
        if(formType.equalsIgnoreCase("Feedback"))
        {
        	fields = FeedbackFormFields.fields;
            fieldTags = FeedbackFormFields.fieldTags;
        }
        if(formType.equalsIgnoreCase("Registration"))
        {
        	fields = RegistrationFormFields.fields;
            fieldTags = RegistrationFormFields.fieldTags;
        }
        */
        fields[0] = GetFormData.getFields(formType, "fields");
        fieldTags[0] = GetFormData.getFields(formType, "fieldtags");
		String imageFormat = DummyRequest.imageFormat;
		while(w < DummyRequest.images.length)
		{
			x = 0;
			String in = DummyRequest.images[w];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(in);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
			RescaleOp rescaleOp = new RescaleOp(1.25f, 35, null);
			rescaleOp.filter(img, img);  // Source and destination are the same.
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
			    			//System.out.println(fields[w][x]);
			    			for (int k = 0; k < lines.length(); k++)
			        		{
			        			JSONObject lineObject = lines.getJSONObject(k);
			        			//lineObject.remove("boundingBox");
			        			String lineText = lineObject.getString("text");
			        			//System.out.println(lineText);
			        			String text = "", confidence = "high";
			        			int isThere = searchSubstring.isSubstring(fieldTags[w][x],lineText);
			        			if(isThere!=-1)
			        			{
			        				fieldsJson f = new fieldsJson();
			        				int y = 0;
			        				//System.out.println(fields[w][x]);
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
			        								
			        				/*if(fields[w][x].equalsIgnoreCase("Rating:"))
			        				{
			        					System.out.println("Rating: "+text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("Phone No:"))
			        				{
			        					System.out.println("Phone No: "+text);
			        				}*/
			        				text = cleanText.allowValidCharacters(text);
			        				if(fields[w][x].equalsIgnoreCase("FIRST NAME :") || fields[w][x].equalsIgnoreCase("LAST NAME :") || fields[w][x].equalsIgnoreCase("First Name:") || fields[w][x].equalsIgnoreCase("Last Name:"))
			        				{
			        					//System.out.println(text);
			        					text = cleanField.cleanNameField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("CONTACT NO :") || fields[w][x].equalsIgnoreCase("CONTACT NO:") || fields[w][x].equalsIgnoreCase("Phone No:"))
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
			        				if(fields[w][x].equalsIgnoreCase("AGE :") || fields[w][x].equalsIgnoreCase("AGE:"))
			        				{
			        					text = cleanField.cleanAge(text);
			        					if(text.equalsIgnoreCase("Invalid Age"))
			        					{
			        						message = message+"\n"+text;
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("GENDER :") || fields[w][x].equalsIgnoreCase("GENDER:"))
			        				{
			        					//System.out.println(text);
			        					text = GetGender.main(text);
			        					if(text.equalsIgnoreCase("Gender Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("MARRITAL STATUS :") || fields[w][x].equalsIgnoreCase("MARRITAL STATUS:"))
			        				{
			        					//System.out.println(text);
			        					text = GetMaritalStatus.main(text);
			        					if(text.equalsIgnoreCase("Marital Status Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("DOB :") || fields[w][x].equalsIgnoreCase("DOB:") || fields[w][x].equalsIgnoreCase("DOB(DD-MM-YYYY):"))
			        				{
			        					text = cleanField.cleanDOB(text);
			        					if (text.equalsIgnoreCase("Invalid Date"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        					//text = cleanText.removeSpaces(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("ADDRESS :") || fields[w][x].equalsIgnoreCase("ADDRESS:"))
			        				{
			        					if(text.length()>0)
			        					{
			        						text = cleanField.cleanAddress(text);
				        					//text.trim(); 
			        					}
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
			        						confidence = "low";
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
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("Are you enjoying your visit at TCS Hyderabad so far?"))
			        				{
			        					text = GetEnjoymentStatus.main(text);
			        					if(text.equalsIgnoreCase("Enjoyment Status Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("How satisfied were you with your visit so far?"))
			        				{
			        					text = GetSatisfactionStatus.main(text);
			        					if(text.equalsIgnoreCase("Satisfaction Level Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        				}
			        				
			        				
			        				if(fields[w][x].equalsIgnoreCase("CONNECT VIA:"))
			        				{
			        					text = GetConnectVia.main(text);
			        					if (text.equalsIgnoreCase("Contacting Options Not Detected Properly"))
			        					{
			        						message = message+"\n"+text;
			        						confidence = "low";
			        					}
			        					else
			        					{
			        						confidence = "high";
			        					}
			        					//text = cleanText.removeSpaces(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("STATE:"))
			        				{
			        					text=cleanField.cleanStateField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("CITY:") || fields[w][x].equalsIgnoreCase("City:"))
			        				{
			        					text=cleanField.cleanCityField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("Zip code:") || fields[w][x].equalsIgnoreCase("ZIPCODE:"))
			        				{
			        					text=cleanField.cleanZipCodeField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("EMAIL ID:") || fields[w][x].equalsIgnoreCase("EMAIL ID :"))
			        				{
			        					//text = cleanText.removeSpaces(text);
			        					text = cleanField.cleanEmailField(text);
			        					if(text.length()>0)
			        					{
			        						boolean isValid = cleanField.validateEmail(text);
				        					if(isValid == false)
				        					{
				        						confidence = "low";
				        						message = message+"\n"+"Invalid Email ID";
				        					}
			        					}
			        				}
			        				if(fields[w][x].equalsIgnoreCase("Rating:"))
			        				{
			        					text=cleanField.cleanRatingField(text);
			        				}
			        				if(fields[w][x].equalsIgnoreCase("Suggestions:"))
			        				{
			        					//System.out.println(text);
			        					text=cleanField.cleanSuggestionsField(text);
			        				}
			        				if(text.equals(""))
			        				{
			        					message = message+"\n"+fields[w][x].substring(0, fields[w][x].length()-1)+" Not Detected Properly";
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
