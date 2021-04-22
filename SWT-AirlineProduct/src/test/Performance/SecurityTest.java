package Performance;

import Model.User;
import Model.User.InvalidUserInputException;
import Service.AutoIDService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SecurityTest {
  static String id;

  User user;

  @BeforeAll
  // Before all tests, generate user ID.
  static void beforeAll() {
    //pass table name and prefix to generateAutoID method.
    AutoIDService.generateAutoID("user", "UO");
  }

  /*
  Create a new User object to test input validation on.
   */
  @BeforeEach
  void beforeEach() throws InvalidUserInputException {
    user = new User(id, "Todd", "Bauer", "TBFlow", "testPassw0rd");
  }

  /*
  Test Case ID: #U-3-1
  Requirement ID/Description: U-3, Users shall have a valid password. Passwords shall consist of
    only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter,
    and 1 lowercase letter.
  Purpose: Verifies that a password with 1 number, 1 uppercase letter, and 1 lowercase letter is
    accepted as a password.
  Test Setup: Generate user ID with AutoIDService, create a stub user.
  Test Strategy: Call user.setPassword() to pass a valid password as per the requirement and check
    if it is accepted.
  Input: Set a User’s password to “testPassw0rd”.
  Expected Output: No Exception is thrown, and the password is changed to “testPassw0rd”.
   */
  @Test
  void testValidPassword() {
    Assertions.assertDoesNotThrow(() -> user.setPassword("testPassw0rd"));

    System.out.println("User name: " + user.getFirstName() + " " + user.getLastName() + ", " +
        "password: " + user.getPassword());
  }

  /*
  Test Case ID: #U-3-2
  Requirement ID/Description: U-3, Users shall have a valid password. Passwords shall consist of
    only alphabetic and numerical characters and must have at least 1 number, 1 uppercase letter,
    and 1 lowercase letter.
  Purpose: Verifies that a password missing 1 number, 1 uppercase letter, or 1 lowercase letter
    is rejected as a password.
  Test Setup: Generate user ID with AutoIDService, create a stub user.
  Test Strategy: Call user.setPassword() to pass a variety of invalid passwords as per the
  requirement and check if it is rejected.
  Input: Set a User’s password to a variety of values that do not meet the requirement criteria.
    More specifically: "123451123", "ADFADFA12", "fadifjadijfaoijdsfoiajdsfoijadsoi1Pf", "a",
    "testpassw0rd", and "testPassword".
  Expected Output: An Exception is thrown, and the original password persists.
   */
  @ParameterizedTest
  @ValueSource(strings = {"123451123", "ADFADFA12",
      "fadifjadijfaoijdsfoiajdsfoijadsoi1Pf", "a",
      "testpassw0rd", "testPassword"})
  void testInvalidPasswordNoLetters(String password) {
    Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setPassword(password));
    Assertions.assertEquals(e.getMessage(), "Invalid Password.");

    System.out.println("attempted password: " + password + ", " +
        "password after attempt: " + user.getPassword());
  }
}
