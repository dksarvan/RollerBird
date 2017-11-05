package com.org.CIBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParameterTest {
	
  Properties prop = new Properties();
  OutputStream output = null;
  InputStream input = null;
  static String data[][];	
  
  @Test(description = "Property file data processing")
  public void getPropertyFileData() {
	  try {

		  input = new FileInputStream(System.getProperty("user.dir")+"\\data\\Environment.properties");
		  
			// load a properties file
			prop.load(input);

			// get the properties value
			System.out.println(prop.getProperty("Dev_URL"));
			System.out.println(prop.getProperty("QA_URL"));

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
  }
	  
	  @Test
	  public void setPropertyFileData() {
		  try {

				output = new FileOutputStream(System.getProperty("user.dir")+"\\data\\Environment.properties");

				// get the properties value
				prop.setProperty("Dev_URL", "devurl");
				prop.setProperty("QA_URL", "qaurl");

				// save properties to project root folder
				prop.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
	  }
	  
	  @DataProvider(name="dp")
	  public Object[][] dpData()
	  {
		  Object[][] bookData = {
	                {"Head First Java", "Kathy Serria", 79},
	                {"Effective Java", "Joshua Bloch", 36},
	                {"Clean Code", "Robert martin", 42},
	                {"Thinking in Java", "Bruce Eckel", 35},
	        };
		  return bookData;
	  }
	  
	  @Test(dataProvider="dp")
	  public void dpDataProcess(String title, String author, int price)
	  {
		  System.out.println(title+author+price);
	  }
	  
	  @Test
	  public void writeSheet()
	  {
		  XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Java Books");
	         
	        Object[][] bookData = {
	                {"Head First Java", "Kathy Serria", 79},
	                {"Effective Java", "Joshua Bloch", 36},
	                {"Clean Code", "Robert martin", 42},
	                {"Thinking in Java", "Bruce Eckel", 35},
	        };
	 
	        int rowCount = 0;
	         
	        for (Object[] aBook : bookData) {
	            Row row = sheet.createRow(++rowCount);
	             
	            int columnCount = 0;
	             
	            for (Object field : aBook) {
	                Cell cell = row.createCell(++columnCount);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	             
	        }
	         
	         
	        try  {
	        	FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+"\\data\\JavaBooks.xlsx");
	            workbook.write(outputStream);
	        }
	        catch(Exception e)
	        {
	        	System.out.println("File Read/Write Exception");
	        }
	  }
	  
	  @Test(dataProvider="excelDP")
	  public void processSheet(String user, String pwd)
	  {
		  System.out.println(user+" "+pwd);
	  }
		  
		  @DataProvider(name="excelDP")
		  public static String[][] readExcelData() 
		  {
			  String[][] data = null;
				
				try {
					FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\data\\dataSheet.xlsx"));
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet sheet = workbook.getSheet("Sheet1");	
					
					// get the number of rows
					int rowCount = sheet.getLastRowNum();
					
					// get the number of columns
					int columnCount = sheet.getRow(0).getLastCellNum();
					data = new String[rowCount][columnCount];

					// loop through the rows
					for(int i=1; i <rowCount+1; i++){
						try {
							XSSFRow row = sheet.getRow(i);
							for(int j=0; j <columnCount; j++){ // loop through the columns
								try {
									String cellValue = "";
									try{
										if(cellValue!=null){
										cellValue = row.getCell(j).getStringCellValue();
										}
									}catch(NullPointerException e){
										
									}
									
									data[i-1][j]  = cellValue; // add to the data array
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}				
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					fis.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return data;			

		  }
		  
		  @Test
		  public void writeXML(){
			  
			   try {
					//String filepath = "c:\\file.xml";
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(System.getProperty("user.dir")+"\\data\\xmlfile.xml");

					// Get the root element
					Node company = doc.getFirstChild();

					// Get the staff element , it may not working if tag has spaces, or
					// whatever weird characters in front...it's better to use
					// getElementsByTagName() to get it directly.
					// Node staff = company.getFirstChild();

					// Get the staff element by tag name directly
					Node staff = doc.getElementsByTagName("staff").item(0);

					// update staff attribute
					NamedNodeMap attr = staff.getAttributes();
					Node nodeAttr = attr.getNamedItem("id");
					nodeAttr.setTextContent("2");

					// append a new node to staff
					Element age = doc.createElement("age");
					age.appendChild(doc.createTextNode("28"));
					staff.appendChild(age);

					// loop the staff child node
					NodeList list = staff.getChildNodes();

					for (int i = 0; i < list.getLength(); i++) {

			                   Node node = list.item(i);

					   // get the salary element, and update the value
					   if ("salary".equals(node.getNodeName())) {
						node.setTextContent("2000000");
					   }

			                   //remove firstname
					   if ("firstname".equals(node.getNodeName())) {
						staff.removeChild(node);
					   }

					}

					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(System.getProperty("user.dir")+"\\data\\xmlfile.xml"));
					transformer.transform(source, result);

					System.out.println("Done");

				   } catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				   } catch (TransformerException tfe) {
					tfe.printStackTrace();
				   } catch (IOException ioe) {
					ioe.printStackTrace();
				   } catch (SAXException sae) {
					sae.printStackTrace();
				   }
			  
		  }
		  
		  @Test
		  public void readXML()
		  {
			  try {

					File xmlFile = new File(System.getProperty("user.dir")+"\\data\\testFile.xml");

					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

					Document doc = documentBuilder.parse(xmlFile);

					doc.getDocumentElement().normalize();

					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

					NodeList nodeList = doc.getElementsByTagName("employee");

					System.out.println("===============================================================");

					//do this the old way, because nodeList is not iterable
					for (int itr = 0; itr < nodeList.getLength(); itr++) {

						Node node = nodeList.item(itr);

						System.out.println("\nNode Name :" + node.getNodeName());

						if (node.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) node;

							System.out.println("Employee id : "
									+ eElement.getAttribute("id"));
							System.out.println("First Name : "
									+ eElement.getElementsByTagName("firstname")
											.item(0).getTextContent());
							System.out.println("Last Name : "
									+ eElement.getElementsByTagName("lastname").item(0)
											.getTextContent());
							System.out.println("Email : "
									+ eElement.getElementsByTagName("email").item(0)
											.getTextContent());
							System.out.println("Department : "
									+ eElement.getElementsByTagName("department").item(0)
											.getTextContent());
							System.out.println("Salary : "
									+ eElement.getElementsByTagName("salary").item(0)
											.getTextContent());

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
  }

