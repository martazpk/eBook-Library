package pl.marta.kopp.domain;

import domain.User;
import domain.UserAlreadyExistsException;
import domain.UserStorage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageTest {
    private static final String SOME_LOGIN = "spiderman";
    private static final String SOME_PASSWORD = "spider123";
    private static final String ANOTHER_LOGIN = "batman";
    private static final String ANOTHER_PASSWORD="bat123";

    @Before
    public void setUp() {
        userStorage.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    private final UserStorage userStorage = new UserStorage();

    @Test
    public void shouldRecognizeWhenLoginIsNotPresentIsStore() {
        assertFalse(userStorage.isUserExists(ANOTHER_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresetIsStore() {
        assertTrue(userStorage.isUserExists(SOME_LOGIN));
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionWhenUserWithTheSomeLoginAddTwice() {
        userStorage.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenLogin()  {
        assertFalse(userStorage.isUserExists(ANOTHER_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenPassword()  {
        assertFalse(userStorage.isUserExists(SOME_LOGIN,ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresentIsStore() {
        assertTrue(userStorage.isUserExists(SOME_LOGIN,SOME_PASSWORD));
    }
}
