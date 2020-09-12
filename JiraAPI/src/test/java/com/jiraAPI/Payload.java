package com.jiraAPI;

public class Payload {
	public static String createSessionPayload() {
		return "{  \r\n" + 
				"    \"username\":\"vishalanerao\",  \r\n" + 
				"    \"password\": \"Vishal@123\"  \r\n" + 
				"}";
	}
	public static String createIssuePaylaod() {
		return "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"FIR\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"Creating a new story form login  page \",\r\n" + 
				"        \"description\": \"user should be login with valid credentials\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Story\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
	}
	public static String addCommentPayload() {
		return "{\r\n" + 
				"    \"body\": \"This is a comment regarding the quality of the response. From Raghvendra\"\r\n" + 
				"}";
	}

}
