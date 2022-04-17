import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Manager {
	public static void main(String[] args) {

		MongoClient client = MongoClients.create("mongodb://44.202.101.172:27017");
		System.out.println("Mongo Connection established successfully");

		System.out.println("Connecting to database");
		MongoDatabase db = client.getDatabase("testdb");
		System.out.println("Connection successful");

		MongoCollection<Document> collection = db.getCollection("testbooks");

		boolean exit = false;
		while (!exit) {
			System.out.println("Choose the required operation : \n");
			System.out.println(
					"1. Insert document\n2. Delete document\n3. Update document\n4. Display all documents\n5. Exit\n");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				insertDocument(collection);
				break;
			case 2:
				deleteDocument(collection);
				break;
			case 3:
				updateDocument(collection);
				break;
			case 4:
				displayAllDocuments(collection);
				break;
			case 5:
				exit=true;
				break;
			default:
				break;
			}
		}
		
	}

	private static void updateDocument(MongoCollection<Document> collection) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ISBN of book to update");
		String isbn = sc.nextLine();
		collection.updateOne(new Document("primary_isbn13", isbn),
				new Document("$set", new Document("status", "Updated")));
		System.out.println("Update successful. Status of book changed from 'new' to 'Updated'\n");
	}

	private static void deleteDocument(MongoCollection<Document> collection) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ISBN of book to delete");
		String isbn = sc.nextLine();
		collection.deleteOne(Filters.eq("primary_isbn13", isbn));
		System.out.println("Document successfully deleted\n");
	}

	private static void insertDocument(MongoCollection<Document> collection) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of book to insert");
		String name = sc.nextLine();
		System.out.println("Enter the ISBN");
		String isbn = sc.nextLine();
		Document doc = new Document("name", name).append("primary_isbn13", isbn).append("status", "new");
		collection.insertOne(doc);
		System.out.println("Document successfully inserted\n");
	}

	private static void displayAllDatabases(MongoClient client) {
		MongoCursor<String> dbsCursor = client.listDatabaseNames().iterator();
		while (dbsCursor.hasNext()) {
			System.out.println(dbsCursor.next());
		}
	}

	private static void displayAllDocuments(MongoCollection<Document> collection) {
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		System.out.println("Total number of documents : " + collection.countDocuments()+"\n");
	}
}
