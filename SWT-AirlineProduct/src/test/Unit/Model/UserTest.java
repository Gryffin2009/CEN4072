package Unit.Model;

import Model.User;
import org.junit.jupiter.api.*;
import Model.User.InvalidUserInputException;
import Service.AutoIDService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {
    static String id;

    User user;

    @BeforeAll
    // Before all test generate user ID
    static void beforeAll() {
        //pass table name and prefix to generateAutoID method.
        AutoIDService.generateAutoID("user", "UO");
    }

    @BeforeEach
    void beforeEach() throws InvalidUserInputException {
        user = new User(id, "Todd", "Bauer", "TBFlow", "testPassw0rd");
    }

    @Test
    void testUpdateInDatabase() {

    }

    @Test
    @DisplayName("Create user, pairwise for all valid and invalid inputs")
        // Testing valid and invalid input.
    void testCreateUser() {

        // Testing invalid input
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));

        // Testing valid input
        Assertions.assertDoesNotThrow(() -> new User(id, "Todd", "Bauer", "TBFlow", "l33Tc0d3r"));

        // Testing invalid input
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "2Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));

        // Testing invalid input
        Assertions.assertThrows(InvalidUserInputException.class, () ->
                new User(id, "2Todd", "Bauer", "TBFlow", "l33tc0d3r"));
    }

    @Test
    void testValidUsername() {
        Assertions.assertDoesNotThrow(() -> user.setUserName("TestUsername"));
    }

    // TODO there is not a test case table for this
    @Test
    void testInvalidUsernameTooManyChar() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setUserName("jakwmakaoqoqoqkakakskqoq"));
        Assertions.assertEquals(e.getMessage(), "Invalid Username.");
    }

    // TODO there is not a test case table for this
    @Test
    void testInvalidUsernameTooLittleChar() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setUserName("a"));
        Assertions.assertEquals(e.getMessage(), "Invalid Username.");
    }


    @Test
    void testValidPassword() {
        Assertions.assertDoesNotThrow(() -> user.setPassword("testPassw0rd"));
    }

    // TODO there is not a test case table for these
    // They might be able to be combined into one test case table?
    // testInvalidPasswordNoLetters
    // testInvalidPasswordNoLowercase
    // testInvalidPasswordTooManyChar
    // testInvalidPasswordTooLittleChar

    // But there is a test case table for:
    // testInvalidPasswordNoUppercase
    // testInvalidPasswordNoNumbers
    // the following test tests all 6 above test cases
    @ParameterizedTest
    @ValueSource(strings = {"123451123", "ADFADFA12",
            "fadifjadijfaoijdsfoiajdsfoijadsoi1Pf", "a",
            "testpassw0rd", "testPassword"})
    void testInvalidPasswordNoLetters(String password) {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setPassword(password));
        Assertions.assertEquals(e.getMessage(), "Invalid Password.");
    }

    @Test
    void testValidFirstName() {
        Assertions.assertDoesNotThrow(() -> user.setFirstName("D’Angelo-Paul"));
    }

    @Test
    void testInvalidFirstNameNumbers() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setFirstName("D’Angelo-Paul23"));
        Assertions.assertEquals(e.getMessage(), "Invalid First Name.");
    }

    @Test
    void testValidLastName() {
        Assertions.assertDoesNotThrow(() -> user.setLastName("O’Rourke-McAllister"));
    }

    @Test
    void testInvalidLastNameNumbers() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setLastName("O’Rourke-McAllister23"));
        Assertions.assertEquals(e.getMessage(), "Invalid Last Name.");
    }

    @Test
    void testNonUniqueUserName() {
        Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setUserName("john"));
        Assertions.assertEquals(e.getMessage(), "Username already exists.");
    }

    @Test
    void testUniqueUserName() {
        Assertions.assertDoesNotThrow(() -> user.setUserName("shouldbeunique"));
    }


}
