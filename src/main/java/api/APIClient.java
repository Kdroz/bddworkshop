package api;

import api.dto.Post;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class APIClient {

    private static APIClient singleton;
    public static final String BACKEND_URL = "https://my-json-server.typicode.com/Kdroz/api_server";
    private HttpClient httpClient;

    public APIClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public static APIClient getInstance() {
        if (singleton == null) {
            singleton = new APIClient();
        }
        return singleton;
    }

    public HttpResponse<String> getPosts() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(BACKEND_URL + "/posts")).GET().build();
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getComments() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(BACKEND_URL + "/comments")).GET().build();
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getComment(int id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(BACKEND_URL + "/comments" + "/" + id)).GET().build();
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}