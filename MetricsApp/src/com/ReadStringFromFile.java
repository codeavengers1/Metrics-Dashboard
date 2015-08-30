package com;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadStringFromFile 
{
	private static final String FILEPATH = "C:/Users/Administrator/Desktop/log.txt";

	//public int logProcess(String tITLE,String wR_NUM,String fILENAME,String fILTER,String dATE){
	public JSONObject logProcess(JSONObject inputObj){
		JSONObject outputObj = new JSONObject();
		String title = "",wrNum = "",fileName="",filter="",date="";
		try{
			if(inputObj.has("title") && inputObj.get("title")!=null)
				title = inputObj.get("title").toString();
			if(inputObj.has("wrNum") && inputObj.get("wrNum")!=null)
				wrNum = inputObj.get("wrNum").toString();
			if(inputObj.has("fileName") && inputObj.get("fileName")!=null)
				fileName = inputObj.get("fileName").toString();
			if(inputObj.has("filter") && inputObj.get("filter")!=null)
				filter = inputObj.get("filter").toString();
			if(inputObj.has("date") && inputObj.get("date")!=null)
				date = inputObj.get("date").toString();
		}catch(Exception e1){}
		if(!fileName.equalsIgnoreCase("")){//do search from file and store result in DB.
			int lineCount = 0;
			String FindClause1 = "METRICS_LOGS";
			FindClause1 = filter;
			File file = new File("C:/Users/Administrator/Desktop/Test.log");
			List<String> list = new ArrayList<String>();
			String[] name = new String[]{"TITLE","WR_NUM", "FILENAME","FILTER", "DATE", "COUNT"};
	  	  	//String[] value = new String[]{"Rec1", "TestWR", "Error_log", "Keyword", "27/09/1991", "1"};

			//	readResource();
			try  {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				String[] fAray = filter.split("\\|");
				while ((line = br.readLine()) != null) 
				{
				    // process the line.
					boolean notFound = false;
					for(int i=0;i<fAray.length;i++){
						if( line.toUpperCase().indexOf(fAray[i].toUpperCase()) == -1){
							notFound = true;
							break;
						}
					}
					
					//if( line.toUpperCase().indexOf(FindClause1.toUpperCase()) != -1)
					if( !notFound)
					{
						lineCount++;
						//list.add(line);
						line += System.getProperty("line.separator");
						useFileWriter(line, FILEPATH );
					}
				}
			}
			catch(FileNotFoundException e)
			{
				System.out.println("File " + file.getAbsolutePath() + " could not be found on filesystem");
			}
			catch(IOException ioe)
			{
				System.out.println("Exception while reading the file" + ioe);
			}
			//fileWriter("TEST LINE");
			//bw.close();
			//useBufferedFileOutPutStream(list,FILEPATH);
			//title = "",wrNum = "",fileName="",filter="",date="";
			String[] value = new String[]{title, wrNum, fileName, filter, date, lineCount+""};
			System.out.println("Total findings are " + lineCount );
			MongoJDBC mongoJDBC = new MongoJDBC();
			mongoJDBC.connectdb(name , value);
			try{
				
				outputObj.put("lineCount", lineCount);
			}catch(Exception e2){}
		}else{//no file name so pull data from DB.
			MongoJDBC mongoJDBC = new MongoJDBC();
			try{
				outputObj.put("gridData", mongoJDBC.Retrivecount(title , wrNum,date));
				String keyWords = mongoJDBC.Retrievekeyword(title , wrNum,date);
				outputObj.put("keyWords", keyWords);
				System.out.println("keyWords="+keyWords);
				String dates1 = mongoJDBC.RetrieveDate(title , wrNum,date);
				outputObj.put("dates", dates1);
				System.out.println("dates1="+dates1);
				String datas1 = "[1262304000000, 6], [1264982400000, 30], [1267401600000, 20], [1270080000000, 31], [1272672000000, 26], [1275350400000, 27], [1277942400000, 24], [1280620800000, 29], [1283299200000, 21], [1285891200000, 13], [1288569600000, 10], [1291161600000, 10]";
				String datas2 = "[1262304000000, 6], [1264982400000, 30], [1267401600000, 20], [1270080000000, 31], [1272672000000, 26], [1275350400000, 27], [1277942400000, 24], [1280620800000, 29], [1283299200000, 21], [1285891200000, 13], [1288569600000, 10], [1291161600000, 10]";
				String datas3 = "[1262304000000, 6], [1264982400000, 30], [1267401600000, 20], [1270080000000, 31], [1272672000000, 26], [1275350400000, 27], [1277942400000, 24], [1280620800000, 29], [1283299200000, 21], [1285891200000, 13], [1288569600000, 10], [1291161600000, 10]";
				String datas = "["+datas1+"],["+datas2+"],["+datas3+"]";
				outputObj.put("datas", datas);
			}catch(Exception e2){}
		}
		
		return outputObj;
	}
	
	public static void main(String[] args) 
	{
		String FindClause1 = "METRICS_LOGS";
		File file = new File("C:/Users/Administrator/Desktop/Test.log");
		List<String> list = new ArrayList<String>();
		String[] name = new String[]{"TITLE","WR_NUM", "FILENAME","FILTER", "DATE", "COUNT"};
  	  String[] value = new String[]{"Rec1", "TestWR", "Error_log", "Keyword", "27/09/1991", "1"};
		int lineCount = 0;

		//	readResource();
		try  {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) 
			{
			    // process the line.
				if( line.toUpperCase().indexOf(FindClause1.toUpperCase()) != -1)
				{
					lineCount++;
					//list.add(line);
					line += System.getProperty("line.separator");
					useFileWriter(line, FILEPATH );
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File " + file.getAbsolutePath() + " could not be found on filesystem");
		}
		catch(IOException ioe)
		{
			System.out.println("Exception while reading the file" + ioe);
		}
		//fileWriter("TEST LINE");
		//bw.close();
		//useBufferedFileOutPutStream(list,FILEPATH);

		System.out.println("Total findings are " + lineCount );
		MongoJDBC mongoJDBC = new MongoJDBC();
		mongoJDBC.connectdb(name , value);
	}

	public static void useBufferedFileOutPutStream(List<String> content, String filePath) {
		Writer writer = null;

		try {

			// Using OutputStreamWriter you don't have to convert the String to byte[]
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "utf-8"));

			for (String line : content) {
				line += System.getProperty("line.separator");
				writer.write(line);
			}

		} catch (IOException e) {

		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {

				}
			}
		}
	}

	/**
	 * Write a small string to a File - Use a FileWriter
	 */

	public static void useFileWriter(String content, String filePath) 
	{
		Writer writer = null;

		try {

			writer = new FileWriter(filePath, true);
			writer.write(content);

		} catch (IOException e) {

			System.err.println("Error writing the file : ");
			e.printStackTrace();

		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {

					System.err.println("Error closing the file : ");
					e.printStackTrace();
				}
			}

		}
	}

}
