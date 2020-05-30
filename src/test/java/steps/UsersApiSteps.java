package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class UsersApiSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @Given("I start execution")
    public void iStartExecution() {
        LOGGER.error("I start execution");
    }

    @When("I search user by {string} name")
    public void iSearchUserByName(String string) {
        LOGGER.info("I search user by {string} name");
    }

    @Then("I verify that I got {string}")
    public void iVerifyThatIGot(String string) {
        LOGGER.info("I verify that I got {string}");
    }
}