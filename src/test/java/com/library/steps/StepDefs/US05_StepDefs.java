package com.library.steps.StepDefs;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_StepDefs {

    String actualMostPopular;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        String query = "SELECT bc.name\n" +
                "FROM book_borrow bb\n" +
                "         LEFT JOIN books b ON bb.book_id = b.id\n" +
                "         JOIN book_categories bc ON b.book_category_id = bc.id\n" +
                "GROUP BY bb.book_id\n" +
                "order by count(*) desc";

            DB_Util.runQuery(query);
                actualMostPopular = DB_Util.getFirstRowFirstColumn();
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedMostPopular) {

        Assert.assertEquals(expectedMostPopular,actualMostPopular);

    }
}
