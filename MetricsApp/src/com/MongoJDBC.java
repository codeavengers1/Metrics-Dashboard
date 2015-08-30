package com;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.List;

public class MongoJDBC{
   public static void main( String args[] ){
      try{   
    	  String[] name = new String[]{"TITLE","WR_NUM", "FILENAME","FILTER", "DATE", "COUNT"};
    	  String[] value = new String[]{"Rec2", "TestWR", "Error_log1", "Keyword4",  "28/09/2001", "1"};
    	  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    	  
        //DBCollection coll = db.createCollection("testCOLL",new BasicDBObject());
         //System.out.println("Collection created successfully");
    	  
    	  System.out.println("Call connect db");
         //connectdb(name, value);
         		DB db = mongoClient.getDB( "test" );
         		DBCollection coll3 = db.getCollection("testCOLL");
              System.out.println("Collection testCOLL selected successfully for view");
              
              String res;
              
              res = Finalsort("Rec2","TestWR","Keyword4");
          	
      }catch(Exception e){
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
   }
   public static void connectdb(String[] name, String[] value){
	   try{
		   System.out.println("In function connect db");
	   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	   DB db = mongoClient.getDB( "test" );
	   System.out.println("Connect to database successfully");
	   DBCollection coll2 = db.getCollection("testCOLL");
	   System.out.println("Collection testCOLL selected successfully");
	   BasicDBObject doc = new BasicDBObject();
	   for(int i=0; i<name.length; i++)
	   {	   System.out.println("In append"+i);
	            doc.append(name[i], value[i]);
	            
	   }
	         	coll2.insert(doc);
	         	System.out.println("Collection testCOLL inserted successfully");
	   }
	   catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
   }
   public static String Retrivecount(String title, String wr_name, String date){
	   String returnstr = "SUCCESS";
	   try{
		   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
		   DB db = mongoClient.getDB( "test" );
    		DBCollection coll3 = db.getCollection("testCOLL");
         System.out.println("Collection testCOLL selected successfully for view");
         
         BasicDBObject query = new BasicDBObject();
         List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     	obj.add(new BasicDBObject("TITLE", title));
     	obj.add(new BasicDBObject("WR_NUM", wr_name));
     	if(!date.equals("NULL"))
     	{
     		obj.add(new BasicDBObject("DATE", date));
     	}
     	query.put("$and", obj);
     	BasicDBObject fields = new BasicDBObject();
     	fields.put("COUNT",1);
     	
     	DBCursor cursor = coll3.find(query);
     	JSON json =new JSON();
       String serialize ;
       returnstr = json.serialize(cursor);
       //System.out.println(json);
       System.out.println(returnstr);
       cursor.close();
	   
	   }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   return returnstr;
   }
   public static String RetrieveDate(String title, String wr_name, String date){
	   String returnstr = "SUCCESS";
	   try{
		   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
		   DB db = mongoClient.getDB( "test" );
		   DBCollection coll3 = db.getCollection("testCOLL");
         System.out.println("Collection testCOLL selected successfully for view");
         
         BasicDBObject query = new BasicDBObject();
         List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     	obj.add(new BasicDBObject("TITLE", title));
     	obj.add(new BasicDBObject("WR_NUM", wr_name));
     	query.put("$and", obj);
     	BasicDBObject fields = new BasicDBObject();
     	fields.put("COUNT",1);
     	List l1= coll3.distinct("DATE", query);
     	JSON json =new JSON();
       String serialize ;
       returnstr = json.serialize(l1);
       //System.out.println(json);
       System.out.println(returnstr);
       
	   
	   }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   return returnstr;
   }
   public static String Retrievekeyword(String title, String wr_name, String date){
	   String returnstr = "SUCCESS";
	   try{
		   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
		   DB db = mongoClient.getDB( "test" );
    		DBCollection coll3 = db.getCollection("testCOLL");
         System.out.println("Collection testCOLL selected successfully for view");

     	BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
    	obj.add(new BasicDBObject("TITLE", title));
    	obj.add(new BasicDBObject("WR_NUM", wr_name));
    	query.put("$and", obj);
    	
    	List l1= coll3.distinct("FILTER", query);
     	//java.util.Collections.sort(keyfilter);
     	
     	JSON json =new JSON();

       returnstr = json.serialize(l1);
       //System.out.println(json);
       System.out.println(returnstr);
   
	   
	   }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   return returnstr;
   }
   public static String Finalsort(String title, String wr_name, String filter){
	   String returnstr = "SUCCESS";
	   try{
		   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
		   DB db = mongoClient.getDB( "test" );
		   DBCollection coll3 = db.getCollection("testCOLL");
         System.out.println("Collection testCOLL selected successfully for view");
         
         BasicDBObject query = new BasicDBObject();
         List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     	obj.add(new BasicDBObject("TITLE", title));
     	obj.add(new BasicDBObject("WR_NUM", wr_name));
     	obj.add(new BasicDBObject("FILTER", filter));
     	query.put("$and", obj);
     	DBCursor cursor = coll3.find(query);
     	JSON json =new JSON();
       String serialize ;
       returnstr = json.serialize(cursor);
       //System.out.println(json);
       System.out.println(returnstr);
       cursor.close();
	   
	   }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   return returnstr;
   }
   
}
