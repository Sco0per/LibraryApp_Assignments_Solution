package com.library.pages;

import com.library.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BorrowedBooksPage extends BasePage {


    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;


    @FindBy(xpath = "//tbody/tr[td[2]='Self Confidence']/td[2]/preceding-sibling::td[1]")
    public List<WebElement> listreturnBook;

    public WebElement returnBook = listreturnBook.get(listreturnBook.size()-1);



}