package com.library.steps.StepDefs;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03_StepDefs extends BookPage {

    List<String> actualCategories;
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String Books) {
        navigateModule(Books);
    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
       Select select = new Select(categoryDropdown);
       actualCategories = BrowserUtil.getAllSelectOptions(categoryDropdown);
       actualCategories.remove(0);
    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        String query = "select name from book_categories";
        DB_Util.runQuery(query);
        List<String> expectedCategories= DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedCategories,actualCategories);
    }
}
