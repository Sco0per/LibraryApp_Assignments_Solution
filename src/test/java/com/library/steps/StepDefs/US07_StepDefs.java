package com.library.steps.StepDefs;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class US07_StepDefs extends BasePage {

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        BrowserUtil.waitFor(2);
        borrowBookButton.click();
    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String BorrowingBooks) {
        navigateModule(BorrowingBooks);
       List<String> borrowedBooksName = BrowserUtil.getElementsText(allBorrowedBooksName);
        System.out.println("US04_StepDefs.bookUs = " + US04_StepDefs.bookUs);
        Assert.assertTrue(borrowedBooksName.contains(US04_StepDefs.bookUs));

        returnBook.click();
    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        String query = "SELECT b.name\n" +
                "FROM book_borrow bb\n" +
                "         JOIN books b ON bb.book_id = b.id\n" +
                "        JOIN users u ON bb.user_id = u.id\n" +
                "WHERE u.email like '"+ ConfigurationReader.getProperty("student_username") +"'";

        DB_Util.runQuery(query);
        List<String> studentBorrowedBooks = DB_Util.getColumnDataAsList(1);

        Assert.assertTrue(studentBorrowedBooks.contains(US04_StepDefs.bookUs));
    }


}
