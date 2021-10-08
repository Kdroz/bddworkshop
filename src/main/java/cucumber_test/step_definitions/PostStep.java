package cucumber_test.step_definitions;

import api.APIClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class PostStep {

    public static HttpResponse<String> posts;
    private static int i;
    private final String POST_ID = "id";
    private final String POST_TITLE = "title";

    private void validateResponseBody(List<Map<String, String>> expectedResponse, JSONArray jsonResponse) {
        i = 0;
        expectedResponse.stream().forEach(expectedRespMap -> {
            JSONObject respInJSON = jsonResponse.getJSONObject(i);
            i++;
            Assert.assertEquals(Integer.parseInt(expectedRespMap.get(POST_ID)), respInJSON.get(POST_ID));
            Assert.assertEquals(expectedRespMap.get(POST_TITLE), respInJSON.get(POST_TITLE));
        });
    }

    @When("I call GET request")
    public void callPosts(){
        try {
            posts = APIClient.getInstance().getPosts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("The following responses are returned")
    public void postsResponseValidation(DataTable dataResponse){
        List<Map<String, String>> expectedResponse = dataResponse.asMaps();
        Assert.assertEquals(posts.statusCode(), 200);
        JSONArray responseInJSON = new JSONArray(posts.body());
        this.validateResponseBody(expectedResponse, responseInJSON);
    }
}
