package com.library.steps.StepDefs;

import com.github.javafaker.Code;
import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class US04_StepDefs extends BookPage {


    static String bookUs;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String book) {
        BrowserUtil.waitFor(2);
        search.click();
        bookUs = book;
        search.sendKeys(book);
    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        editBook(bookUs).click();
        BrowserUtil.waitFor(5);
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        List<String> actualData = new ArrayList<>();
        actualData.add(bookName.getAttribute("value"));
        actualData.add(isbn.getAttribute("value"));
        actualData.add(year.getAttribute("value"));
        actualData.add(author.getAttribute("value"));
        actualData.add(description.getText());

        System.out.println("actualData = " + actualData);

        String query = "select books.name,isbn,year,author,books.description from books\n" +
                "where books.name like '"+bookUs+"'";
        DB_Util.runQuery(query);
        List<String> expectedData = DB_Util.getRowDataAsList(1);
        System.out.println("expectedData = " + expectedData);
        Assert.assertEquals(expectedData,actualData);

    }

}
