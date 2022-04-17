import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class Test {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient( "mongodb://44.202.101.172:27017" ); 
		System.out.println("Created Mongo Connection successfully"); 
		
		System.out.println("Creating new database");
		MongoDatabase db = mongoClient.getDatabase("BookStore");
		System.out.println("Get database is successful");
		
		System.out.println("Below are list of databases present in MongoDB");
		
		
		// To get all database names        
			 MongoCursor<String> dbsCursor1 = mongoClient.listDatabaseNames().iterator();
			   while(dbsCursor1.hasNext()) {
			          System.out.println(dbsCursor1.next());
			      }
				  
				  
		//Inserting sample record by creating collection and document.
		   MongoCollection<Document>  collection= db.getCollection("Books");
		   Document doc =new Document("name","Book1");
		   collection.insertOne(doc);
		   System.out.println("########### Insert is completed  ###############");
		   
		// To get all database names        
		 MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
		   while(dbsCursor.hasNext()) {
		          System.out.println(dbsCursor.next());
		      }
		   	   
		 
			   
		 //Drop Database
		   mongoClient.dropDatabase("youtube");
		   System.out.println("################## Database dropped successfully ##################");

		   
		   System.out.println("After Database getting dropped, present list of Database's...");
		 //list all databases
		  MongoCursor<String> dbsCursor2 = mongoClient.listDatabaseNames().iterator();
		    while(dbsCursor2.hasNext()) {
		           System.out.println(dbsCursor2.next());
		  }
	}

}
