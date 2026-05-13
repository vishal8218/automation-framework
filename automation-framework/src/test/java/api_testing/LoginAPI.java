package api_testing;


 import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

 import io.restassured.response.Response;
 import static io.restassured.RestAssured.*;



 public class LoginAPI {

 @Test public void login()  {
 baseURI = "http://localhost:8448/";
 JSONObject body = new JSONObject();
 body.put("userEmailId", "vk368065@gmail.com");
 body.put("password", "1234");
 

 Response response =
 given()
 .header("Content-Type", "application/json")
 .queryParam("otp", "6485")
 .when()
 .post("verifyotp");
 
 
 
 

 System.out.println("===== FULL RESPONSE =====");
 response.prettyPrint();


 
 
// String token = response.jsonPath().getString("token");
// System.out.println("Token Extracted : "+token);
// JSONObject body2 = new JSONObject();
// body2.put("senderId","982fcbcd-f670-4c93-8f43-385d591c7463");
// body2.put("reciverId", "ce509a25-5c41-4bab-9066-4896d0c44157");
// Response response2 =
//		 given()
//		   
//		     .headers(
//		         "Authorization",   token,
//		         "Content-Type", "application/json"
//		     )
//		     .body(body2.toString())
//		 .when()
//		     .post("get_messages");
//
//		 response2.prettyPrint();
//		 Map<String, List<String>> data = response2.jsonPath().getMap("");
//		 System.out.println("Status Code: " + response2.getStatusCode());
//		 System.out.println(response2.jsonPath().getString("messageContent"));
//
//



 }

 }
