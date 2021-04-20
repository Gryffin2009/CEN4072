package Unit.View;

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

    @Test
    void testAddTicket() throws InterruptedException {
        sut.textBox("txtcustid").setText("CS001");;
        sut.button("jButton4").click();

        sut.comboBox("txtsource").selectItem(0);
        sut.comboBox("txtdepart").selectItem(0);
        sut.button("jButton3").click();

        sut.table().cell("India").click();

        sut.comboBox("txtclass").selectItem("Economy");
        sut.textBox("txtprice").setText("100");
        sut.spinner("txtseats").increment();

        sut.button("jButton1").click();
    }

}