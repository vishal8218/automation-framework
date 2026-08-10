// package db;

// import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.cloud.FirestoreClient;

// import utils.ConfigReader;

// public class FirebaseUtils {

// 	ConfigReader configReader;
// 	 private Firestore db ;

// 	 public  FirebaseUtils() {
// 		 configReader=new ConfigReader();
// 	        try {
// 	            FileInputStream serviceAccount = new FileInputStream(configReader.getCred());

// 	            FirebaseOptions options = FirebaseOptions.builder()
// 	            	    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(configReader.getCred())))
// 	            	    .setDatabaseUrl(configReader.getDbUrl())
// 	            	    .build();

// 	            if (FirebaseApp.getApps().isEmpty()) {
// 	                FirebaseApp.initializeApp(options);
	              
	                
// 	            }
// 	        } catch (Exception e) {
// 	            e.printStackTrace();
// 	        }
// 	    }
// 	  public void saveData(String userEmail,String pass) throws Exception
// 	    {
// 	    	 db =  (Firestore)FirestoreClient.getFirestore();

// 		        System.out.println("SAVE DATA  "+userEmail);
// 	  	  		    Map<String, Object> credentialsData = new HashMap<>();  
// 	  	  		    String uuid= UUID.randomUUID().toString();
// 	  	  	  	credentialsData .put("userId",uuid);
//   	  		credentialsData .put("email", userEmail);
// 	  	  	   credentialsData.put("password", pass);
// 	 		    db.collection("CoachData").document(uuid).set(credentialsData);
// //			 
			
	  		    
// 	    }
	  
	 

// }
