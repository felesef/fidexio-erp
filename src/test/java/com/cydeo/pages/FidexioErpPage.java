package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FidexioErpPage {
    public FidexioErpPage(){        PageFactory.initElements(Driver.getDriver(), this);    }



    @FindBy(id = "login")
    public  WebElement UserNameInput;

    @FindBy(id = "password")
    public  WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement LoginButton;

    @FindBy(xpath = "//*[text() = 'Reset Password']")
    public  WebElement ResetPasswordButton;

    @FindBy(xpath = "//a[@href='/']")
    public WebElement TopLeftHeader;

    @FindBy(xpath = "//b")
    public WebElement SingInLink;

    @FindBy(css = "span")
    public WebElement CopyRight;

    @FindBy (xpath = "//span[@class='oe_topbar_name']")
    public static WebElement title;

    @FindBy (xpath = "//p[@class='alert alert-danger']")
    public static WebElement AlertMessage;

    @FindBy(xpath = "//span[@class=\'oe_topbar_name\']")
    public WebElement UserIdButton;

    @FindBy(xpath = "//a[text()='Log out']")
        public WebElement LogOutButton;

    @FindBy (xpath = "//h4[@class='modal-title']")
    public WebElement SignOut_ThanInAlertMessage;


    


}
