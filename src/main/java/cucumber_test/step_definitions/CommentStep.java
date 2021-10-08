package cucumber_test.step_definitions;

import api.APIClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CommentStep {
    public static HttpResponse<String> comments;
    public static HttpResponse<String> comment;

    @When("Call the GET comment function")
    public void getAllComments(){
        try {
            comments = APIClient.getInstance().getComments();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("Call the GET comment function with {string}")
    public void getOneComment(String id) {
        try {
            comment = APIClient.getInstance().getComment(Integer.parseInt(id));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("The following responses is expected for {string} and {string}")
    public void validateComments(String body, String postId) {
        Assert.assertEquals(200, comment.statusCode());
        JSONObject commentJSON = new JSONObject(comment.body());
        Assert.assertEquals(body, commentJSON.get("body"));
        Assert.assertEquals(Integer.parseInt(postId), commentJSON.get("postId"));
    }
}
