package com.library.steps.StepDefs;

import com.library.pages.BasePage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_StepDefs extends DashBoardPage{
    LoginPage lp = new LoginPage();
    String actualBBnumber;
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {
        lp.login(librarian);
    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        BrowserUtil.waitFor(1);
        actualBBnumber =borrowedBooksNumber.getText();
        System.out.println("actualBBnumber = " + actualBBnumber);
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query = "select count(*) from book_borrow\n" +
                "    where is_returned = 0";
        DB_Util.runQuery(query);
        String expectedBBnumber = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBBnumber = " + expectedBBnumber);

        Assert.assertEquals(expectedBBnumber,actualBBnumber);
    }
}
