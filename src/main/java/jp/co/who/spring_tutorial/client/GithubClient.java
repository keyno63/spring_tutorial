package jp.co.who.spring_tutorial.client;

import jp.co.who.spring_tutorial.client.resource.JsonResource;
import jp.co.who.spring_tutorial.repository.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class GithubClient {
    private static final String url = "http://localhost:8080/echo/easy";
    @Autowired
    RestOperations restOperations;

    JsonResource resource = restOperations.getForObject(
            url,
            JsonResource.class
    );

    RequestEntity<JsonResource> req = RequestEntity
            .post(URI.create(url))
            .contentType(MediaType.APPLICATION_JSON)
            .header("sample-header", "sample")
            .body(resource);
    ResponseEntity<Void> res =
            restOperations.exchange(req, Void.class);

    public void print() {
        System.out.println(resource);
    }

}
