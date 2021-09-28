package api;

import api.dto.Post;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public static final String BACKEND_URL = "https://my-json-server.typicode.com/Kdroz/api_server";

    private HttpClient httpClient;

    public APIClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    static class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {

        private final Class<T> clazz;

        public JsonBodyHandler(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
            var stringBodySubscriber = HttpResponse.BodySubscribers
                    .ofString(StandardCharsets.UTF_8);

            return HttpResponse.BodySubscribers.mapping(
                    stringBodySubscriber,
                    (body) -> {
                        try {
                            return OBJECT_MAPPER.readValue(body, this.clazz);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }

    public HttpResponse<String> getPosts() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(BACKEND_URL + "/posts")).GET().build();
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}