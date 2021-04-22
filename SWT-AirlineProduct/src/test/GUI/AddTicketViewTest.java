package GUI;

import View.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JInternalFrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class AddTicketViewTest {
    private FrameFixture window;
    private JInternalFrameFixture sut;

    @BeforeEach
    public void setUp() {
        JFrame frame = GuiActionRunner.execute(Main::new);
        window = new FrameFixture(frame);
        window.show(); // shows the frame to tests

        window.menuItem("jMenuItem3").click();
        sut = window.internalFrame("AddTicket");
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    /*
    Test Case ID: #BUI-10-1
    Requirement ID/Description: BUI-10, The user shall be able to book a ticket from the UI.
    Purpose: Verify that a ticket is booked and added to the database from the UI.
    Test Setup: Create window frame from AddTicket class using AssertJ
    Test Strategy: Use AssertJ to automatically open AddTicket window and select all required
        options to book a ticket using the UI, verifying that the ticket is booked successfully.
    Input: The user shall select all fields associated with booking a ticket:
        Customer ID: CS001
        Source: India
        Depart: Uk
        Associated flight: FO001
        Class type: Economy
        Price: $100
        Number of seats: 1
        Then click the submit button.
    Expected Output: The dialog box confirming a ticket was successfully booked is shown, and the
        database contains the added ticket.
     */
    @Test
    void testAddTicket() throws InterruptedException {
        sut.textBox("txtcustid").setText("CS001");;
        sut.button("jButton4").click();

        sut.comboBox("txtsource").selectItem(0);
        sut.comboBox("txtdepart").selectItem(2);
        sut.button("jButton3").click();

        sut.table().cell("India").click();

        sut.comboBox("txtclass").selectItem("Economy");
        sut.textBox("txtprice").setText("100");
        sut.spinner("txtseats").increment();

        sut.button("jButton1").click();
    }

}