package com.jiraAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Authentication {
	private static final String Base_URI="http://localhost:8080";
	private static final String Session_URL="/rest/auth/1/session";
	private static final String Create_Issue="/rest/api/2/issue";
	private static final String Add_Comment="/rest/api/2/issue/{key}/comment";
	private static final String Add_Attachment="/rest/api/2/issue/{key}/attachments";
	static String newlyCreatedTicket="key";
	
	 SessionFilter sessionFilter;
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI=Base_URI;
	}
	public  SessionFilter createSession() {
		sessionFilter=new SessionFilter();
		given()
		.contentType(ContentType.JSON)
		.body(Payload.createSessionPayload())
		.log().all()
		.filter(sessionFilter)
		.when()
		.post(Session_URL)
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.body("session.value", Matchers.notNullValue());
		return sessionFilter;
	}
	@Test
	public  String createIssue() {
		sessionFilter=createSession();
		given()
		.contentType(ContentType.JSON)
		.body(Payload.createIssuePaylaod())
		.log().all()
		.filter(sessionFilter)
		.when()
		.post(Create_Issue)
		.then()
		.log().all()
		.assertThat()
		.statusCode(201);
		
		String responseCreateTicket = createIssue();
		JsonPath jsonPath=new JsonPath(responseCreateTicket);
		return newlyCreatedTicket = jsonPath.getString("key");
	}
	
	public void addCommentInExistingTicket() {
		sessionFilter=createSession();
		given()
		.contentType(ContentType.JSON)
		.body(Payload.addCommentPayload())
		.log().all()
		.filter(sessionFilter)
 		.when()
		.post(Add_Comment)
		.then()
		.log().all();

	}
	
	public void addAttachment() {
		sessionFilter=createSession();
		given()  
		.header("X-Atlassian-Token", "nocheck")  
		.header("Content-Type", "multipart/form-data")  
		.filter(sessionFilter)  
		.multiPart("file", new File ("C:\\Users\\VISHLO\\Desktop\\jira_file.txt"))  
		.log().all()  
		.when()  
		.post(Add_Attachment)  
		.then()  
		.log().all()
		.assertThat()
		.statusCode(200);
	}
	
}
