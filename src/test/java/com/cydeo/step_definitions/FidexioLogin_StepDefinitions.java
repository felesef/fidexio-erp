package com.cydeo.step_definitions;

import com.cydeo.pages.FidexioErpPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;

public class FidexioLogin_StepDefinitions {
    FidexioErpPage fidexioErpPage= new FidexioErpPage();

    @Given("user is on the login page of fidexio page")
    public void userIsOnTheLoginPageOfFidexioPage() {
        String url = ConfigurationReader.getProperty("fidexioUrl");
        Driver.getDriver().get(url);
    }

    @When("user enters username {string}")
    public void user_enters_username(String string) {
        fidexioErpPage.UserNameInput.sendKeys(string);
                //sendKeys(string);
    }
    @When("user enters password {string}")
    public void user_enters_password(String string) {
        fidexioErpPage.passwordInput.sendKeys(string);
    }
    @When("user clicks to login button")
    public void user_clicks_to_login_button() {
       fidexioErpPage.LoginButton.click();
     }


    @Then("user sees {string} in the title")
    public void userSeesInTheTitle(String string) {
        String expectedResult= string;
        String actualResult= fidexioErpPage.title.getText();
        System.out.println("actualResult = " + actualResult);
        Assert.assertTrue(actualResult.equalsIgnoreCase(expectedResult));
       // Assert.assertTrue(FidexioErpPage.title.contains(string));
    }

    @Then("user sees {string} above Login Button")
    public void userSeesAboveLoginButton(String string) {
        String ExpectedWarningMessage = string;
        String ActualWarningMessage= fidexioErpPage.AlertMessage.getText();
        Assert.assertTrue(fidexioErpPage.AlertMessage.isDisplayed());

        System.out.println("ExpectedWarningMessage = " + ExpectedWarningMessage);
        System.out.println("ActualWarningMessage = " + ActualWarningMessage);
        Assert.assertEquals(ExpectedWarningMessage,ActualWarningMessage);



    }

    @Then("user stays in same page and sees {string} on URL field")
    public void userStaysInSamePageAndSeesOnURLField(String URL) {
        String ExpectedURL = URL;
        String ActualURL = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(ExpectedURL,ActualURL);
    }

    @Then("user stays in same page and sees {string} as title")
    public void userStaysInSamePageAndSeesAsTitle(String Title) {
        BrowserUtils.verifyTitle(Title);
    }

    @And("user clicks to Logout button")
    public void userClicksToLogoutButton() {
        fidexioErpPage.LogOutButton.click();
    }

    @And("user clicks back arrow button")
    public void userClicksBackArrowButton() {
        Driver.getDriver().navigate().back();
                //execute_script("window.history.go(-1)")
    }

    @Then("user sees {string} alert")
    public void userSeesMessage(String Alert) {
        String ExpextedAlertMessage= Alert;
        WebElement ActualAlertMessage = fidexioErpPage.SignOut_ThanInAlertMessage;
        boolean condition = fidexioErpPage.SignOut_ThanInAlertMessage.isDisplayed();
        Assert.assertTrue(condition);

    }

    @And("user clicks UserID button")
    public void userClicksUserIDButton() {
        fidexioErpPage.UserIdButton.click();
    }
}
