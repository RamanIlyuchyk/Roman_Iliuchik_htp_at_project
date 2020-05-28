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
        //System.out.println("I start execution");
        //throw new cucumber.api.PendingException();
    }

    @When("I search user by \"(.*)\" name")
    public void iSearchUserByName(String string) {
        LOGGER.info("I search user by name");
        //System.out.println("I search user by name");
        //throw new cucumber.api.PendingException();
    }

    @Then("I verify that I got \"(.*)\"")
    public void iVerifyThatIGot(String string) {
        LOGGER.info("I verify that I got");
        //System.out.println("I verify that I got");
        //throw new cucumber.api.PendingException();
    }
}