package Test_Cases_Package;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;


public class API {
	@Test
	public void TC_get_All_Vgames() {
		given().when().get("http://localhost:8080/app/videogames").then().statusCode(200);
		}
		
	
public void TC_add_Game() {
	HashMap data=new HashMap();
	data.put("id", "100");
	data.put("name", "Spiderman");
	Response res=given().contentType("application/json").body(data).when().get("http://localhost:8080/videogames").then().statusCode(200).log().body().extract().response();
	String Json=res.asString();
	Assert.assertEquals(Json.contains("Record Added Succesfdully"),true);
}
public void TC_get_Game() {
	given().when().get("http://localhost:8080/app/videogames/100").then().statusCode(200).body("videoGame.id",equalTo("100")).body("videoGame.name",equalTo("SpiderMan"));
	
}
public void TC_Update_Game() {
	HashMap data=new HashMap();
	data.put("id", "100");
	data.put("name", "Pockerman");
	
	given().contentType("application/json").body(data).when().put("http://localhost:8080/videogames/100").then().statusCode(200).log().body().body("videoGame.id",equalTo("100")).body("videoGame.name",equalTo("SpiderMan"));
}
public void TC_Delete_Game() {
Response res=
given().when().delete("http://localhost:8080/videogames/100").then().statusCode(200).log().body().extract().response();

 String Json=res.asString();
		 Assert.assertEquals(Json.contains("Record deleted Successfully"),true);
}
}