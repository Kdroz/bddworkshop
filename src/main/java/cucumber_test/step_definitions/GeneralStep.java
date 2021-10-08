package cucumber_test.step_definitions;

import api.APIClient;
import io.cucumber.java.en.Given;

public class GeneralStep {

    public static APIClient apiClient;

    @Given("Initiate the contact with the API")
    public void callSetup(){
        apiClient = APIClient.getInstance();
    }
}
