package pl.marta.kopp.domain;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.connector.DBConnector;
import pl.marta.kopp.service.UserStorageImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageImplTest {
    private static final String SOME_LOGIN = "spiderman";
    private static final String SOME_PASSWORD = "spider123";
    private static final String ANOTHER_LOGIN = "batman";
    private static final String ANOTHER_PASSWORD="bat123";
    private DBConnector dbConnector;

    @Before
    public void setUp() {
        userStorageImpl.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    private final UserStorageImpl userStorageImpl = new UserStorageImpl(dbConnector);

    @Test
    public void shouldRecognizeWhenLoginIsNotPresentIsStore() {
        assertFalse(userStorageImpl.isUserExists(ANOTHER_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresetIsStore() {
        assertTrue(userStorageImpl.isUserExists(SOME_LOGIN));
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionWhenUserWithTheSomeLoginAddTwice() {
        userStorageImpl.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenLogin()  {
        assertFalse(userStorageImpl.isUserExists(ANOTHER_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenPassword()  {
        assertFalse(userStorageImpl.isUserExists(SOME_LOGIN,ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresentIsStore() {
        assertTrue(userStorageImpl.isUserExists(SOME_LOGIN,SOME_PASSWORD));
    }
}
