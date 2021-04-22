package Unit.Model;

import Model.User;
import org.junit.jupiter.api.*;
import Model.User.InvalidUserInputException;
import Service.AutoIDService;

public class UserTest {
    static String id;
    User user;

    @BeforeAll
    static void beforeAll() {
        // Before all test generate user ID
        // pass table name and prefix to generateAutoID method.
        AutoIDService.generateAutoID("user", "UO");
    }

    @BeforeEach
    void beforeEach() throws InvalidUserInputException {
        user = new User(id, "Todd", "Bauer", "TBFlow", "testPassw0rd");
    }

    /**
     * Test Case ID: U-2-1, U-3-1, U-4-1, U-5-1
     * Requirement:
     * U-2: Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * U-3:Users shall have a valid password. Passwords shall consist of only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter, and 1 lowercase letter. Passwords shall contain 5-15 characters inclusive.
     * U-4:Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * U-5:Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set valid properties to create a user.
     * Test setup: none
     * Test Strategy: A new user is created.
     * Input: "Todd", "Bauer", "TBFlow", "l33Tc0d3r"
     * Expected output: the method should not throw an exception
     **/
    @Test
    @DisplayName("Create user, pairwise for all valid inputs")
    void testCreateUserValidPairwise() {
        Assertions.assertDoesNotThrow(() -> new User(id, "Todd", "Bauer", "TBFlow", "l33Tc0d3r"));
    }

    /**
     * Test Case ID: U-2-2, U-3-2, U-4-2, U-5-2
     * Requirement:
     * U-2:Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * U-3:Users shall have a valid password. Passwords shall consist of only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter, and 1 lowercase letter. Passwords shall contain 5-15 characters inclusive.
     * U-4:Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * U-5:Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set valid properties to create a user.
     * Test setup: none
     * Test Strategy: A new user is created.
     * Input: "Todd", "Bauer", "TBFlow", "l33Tc0d3r"
     * Expected output: the method should not throw an exception
     **/
    @Test
    @DisplayName("Create user, pairwise for all invalid inputs")
    void testCreateUserInvalidPairwise0() {
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));
    }
    /**
     * Test Case ID: U-2-1, U-2-3,  U-3-3, U-4-2,  U-5-2
     * Requirement:
     * U-2:Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * U-3:Users shall have a valid password. Passwords shall consist of only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter, and 1 lowercase letter. Passwords shall contain 5-15 characters inclusive.
     * U-4:Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * U-5:Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set valid properties to create a user.
     * Test setup: none
     * Test Strategy: A new user is created.
     * Input: "Todd", "Bauer", "TBFlow", "l33Tc0d3r"
     * Expected output: the method should not throw an exception
     **/
    @Test
    @DisplayName("Create user, pairwise for all invalid inputs")
    void testCreateUserInvalidPairwise1() {
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "2Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));

    }
    /**
     * Test Case ID: U-2-2, U-3-1,  U-4-1, U-5-1
     * Requirement:
     * U-2:Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * U-3:Users shall have a valid password. Passwords shall consist of only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter, and 1 lowercase letter. Passwords shall contain 5-15 characters inclusive.
     * U-4:Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * U-5:Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set valid properties to create a user.
     * Test setup: none
     * Test Strategy: A new user is created.
     * Input: "Todd", "Bauer", "TBFlow", "l33Tc0d3r"
     * Expected output: the method should not throw an exception
     **/
    @Test
    @DisplayName("Create user, pairwise for all invalid inputs")
    void testCreateUserInvalidPairwise2() {
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "2Todd", "Bauer", "TBFlow", "l33tc0d3r"));
    }

    /**
     * Test Case ID: #U-2-1
     * Requirement: U-2 Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * Purpose: The method attempts to set an valid username for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setUserName("TestUsername") is called and should not throw an exception.
     * Input: "TestUsername"
     * Expected output: the method should not throw an exception
     **/
    @Test
    void testValidUsername() {
        Assertions.assertDoesNotThrow(() -> user.setUserName("TestUsername"));
    }

    /**
     * Test Case ID: #U-2-2
     * Requirement: U-2 Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * Purpose: The method attempts to set an invalid username for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setUserName("jakwmakaoqoqoqkakakskqoq") is called and should throw an exception.
     * Input: "jakwmakaoqoqoqkakakskqoq"
     * Expected output: the method should not throw an InvalidUserInputException exception
     **/
    @Test
    void testInvalidUsernameTooManyChar() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setUserName("jakwmakaoqoqoqkakakskqoq"));
        Assertions.assertEquals(e.getMessage(), "Invalid Username.");
    }

    /**
     * Test Case ID: #U-2-3
     * Requirement: U-2 Users shall have a valid username. Usernames shall contain only alphabetic characters and contain 4-20 characters inclusive.
     * Purpose: The method attempts to set an invalid username for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setUserName("a") is called and should throw an exception.
     * Input: "a"
     * Expected output: the method should throw an InvalidUserInputException exception
     **/
    @Test
    void testInvalidUsernameTooLittleChar() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setUserName("a"));
        Assertions.assertEquals(e.getMessage(), "Invalid Username.");
    }

    /**
     * Test Case ID: U-4-1
     * Requirement: U-4 Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set an valid name for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setFirstName("D’Angelo-Paul") is called and should not throw an exception.
     * Input: "D’Angelo-Paul"
     * Expected output: the method should not throw an exception
     **/
    @Test
    void testValidFirstName() {
        Assertions.assertDoesNotThrow(() -> user.setFirstName("D’Angelo-Paul"));
    }

    /**
     * Test Case ID: U-4-2
     * Requirement: U-4  Users shall have a valid first name. First names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set an invalid name for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setFirstName("D’Angelo-Paul23" is called and should throw an exception.
     * Input: "D’Angelo-Paul23"
     * Expected output: the method should throw an InvalidUserInputException exception
     **/
    @Test
    void testInvalidFirstNameNumbers() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setFirstName("D’Angelo-Paul23"));
        Assertions.assertEquals(e.getMessage(), "Invalid First Name.");
    }

    /**
     * Test Case ID: #U-5-1
     * Requirement: U-5 Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set an invalid last name for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setLastName("O’Rourke-McAllister") is called and should not throw an exception.
     * Input: "O’Rourke-McAllister"
     * Expected output: the method should not throw an exception
     **/
    @Test
    void testValidLastName() {
        Assertions.assertDoesNotThrow(() -> user.setLastName("O’Rourke-McAllister"));
    }

    /**
     * Test Case ID: #U-5-2
     * Requirement: U-5 Users shall have a valid last name. Last names shall consist of only alphabetic characters, dashes, or apostrophes.
     * Purpose: The method attempts to set an invalid last name for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setLastName("O’Rourke-McAllister23") is called and should throw an exception.
     * Input: "O’Rourke-McAllister23"
     * Expected output: the method should throw an InvalidUserInputException exception
     **/
    @Test
    void testInvalidLastNameNumbers() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setLastName("O’Rourke-McAllister23"));
        Assertions.assertEquals(e.getMessage(), "Invalid Last Name.");
    }

    /**
     * Test Case ID: #U-6-1
     * Requirement: U-6 All usernames shall be unique.
     * Purpose: The method attempts to set an valid unique username for the user.
     * Test setup: A user object is created beforehand.
     * Test Strategy: user.setUserName("shouldbeunique") is called and should not throw an exception.
     * Input: "shouldbeunique"
     * Expected output: the method should not throw an exception
     **/
    @Test
    void testUniqueUserName() {
        Assertions.assertDoesNotThrow(() -> user.setUserName("shouldbeunique"));
    }
}
