package api_testing;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class LoginAPI {
	
	
	
	  @Test
	    public void login() throws JSONException {
		  baseURI = "https://beta.btrainr.com";

	        JSONObject body = new JSONObject();
	        body.put("email", "testing1137@yopmail.com");
	        body.put("password", "Qwerty@123");
	        body.put("verificationType", "password");
	        body.put("authMethod", "email");
	        body.put("deviceType", "web");

	        // Send POST request
	        Response response =
	            given()
	                .header("Content-Type", "application/json")
	                .body(body.toString())
	            .when()
	                .post("/api/auths/login");

	        // Print full response
	        System.out.println("===== FULL RESPONSE =====");
	        response.prettyPrint();

	        // Status Code
	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);

	        // Extract values (update keys based on actual response)
	        String token = response.jsonPath().getString("data.session.accessToken");
	        String userId = response.jsonPath().getString("data.id");
	        String email = response.jsonPath().getString("data.email");

	        // Print extracted values
	        System.out.println("Access Token: " + token);
	        System.out.println("User ID: " + userId);
	        System.out.println("Email: " + email);
		 
	       	 
	  
}
	  
}
