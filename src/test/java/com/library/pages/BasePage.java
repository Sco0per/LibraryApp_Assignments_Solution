package com.library.pages;

import com.library.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * parent class for concrete Page object classes
 * provides constructor with initElements method for re-usability
 * abstract - to prevent instantiation.
 */
public abstract  class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(css = "#navbarDropdown>span")
    public WebElement accountHolderName;

    @FindBy(linkText = "Log Out")
    public WebElement logOutLink;

    public void logOut(){
        accountHolderName.click();
        logOutLink.click();
    }

    public void navigateModule(String moduleName){
        Driver.getDriver().findElement(By.xpath("//span[@class='title'][.='"+moduleName+"']")).click();
    }

    @FindBy(xpath = "   (//a[normalize-space()='Borrow Book'])[1]")
    public WebElement borrowBookButton;

    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

    @FindBy(xpath = "(//td[.='NOT RETURNED '])[last()]/preceding-sibling::td[5]")
    public WebElement returnBook;

}
