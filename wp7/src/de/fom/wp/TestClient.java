package de.fom.wp;

import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class TestClient {
	
	public static void main(String[] args) throws Exception, IOException{
		String r = Request.Put("http://localhost:8080/wp7/rest")
        .useExpectContinue()
        .version(HttpVersion.HTTP_1_1)
        .bodyString("{\"name\":\"Provinzial\"}", ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();
		System.out.println(r);
	}

}
