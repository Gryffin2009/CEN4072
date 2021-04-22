package GUI;

import View.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JInternalFrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class SearchCustomerViewTest {
    private FrameFixture window;
    private JInternalFrameFixture sut;

    @BeforeEach
    public void setUp() {
        JFrame frame = GuiActionRunner.execute(Main::new);
        window = new FrameFixture(frame);
        window.show(); // shows the frame to tests

        window.menuItem("jMenuItem2").click();
        sut = window.internalFrame("SearchCustomer");
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    /*
    Test Case ID: #SUI-1-1
    Requirement ID/Description: SUI-1, The software shall allow the user to search the database
        for customers by Customer ID.
    Test Setup: Create a window frame of the SearchCustomer screen with AssertJ.
    Test Strategy: Search for an existing customer in the database, then update their first and
        last name and ensure the database has changed to reflect this change.
    Input: The user searches for customer CS001 and changes the first name to “Brandon” and last
        name to “Baker”, then clicks the submit button.
    Expected Output: The database entry for CS001 is updated with the first name “Brandon” and
        last name “Baker”.
     */
    @Test
    void testUpdateCustomer() throws InterruptedException {
        sut.textBox("txtcustid").setText("CS001");
        sut.button("jButton4").click();

        sut.textBox("txtfirstname").setText("Brandon");
        sut.textBox("txtlastname").setText("Baker");
        sut.button("jButton2").click();
    }
}
