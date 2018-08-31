package pl.marta.kopp.domain;


import org.junit.Test;
import pl.marta.kopp.persistence.UserStorageJpa;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageJpaTest {
    private static final String SOME_LOGIN = "spider-man";
    private static final String SOME_PASSWORD = "spider123";
    private static final String ANOTHER_LOGIN = "batman";
    private static final String ANOTHER_PASSWORD="bat123";
    private final UserStorageJpa userStorageJpa = new UserStorageJpa();


    @Test
    public void shouldAddNewUser() {
        userStorageJpa.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
        assertTrue(userStorageJpa.isUserExists(SOME_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        User user=new User(SOME_LOGIN, SOME_PASSWORD);
        userStorageJpa.addUser(user);
        userStorageJpa.delete(user.getId());
        assertFalse(userStorageJpa.isUserExists(SOME_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        User user=new User(SOME_LOGIN, SOME_PASSWORD);
        userStorageJpa.addUser(user);
        User byId = userStorageJpa.getById(user.getId());
        byId.setPassword(ANOTHER_PASSWORD);
        userStorageJpa.update(byId);
        assertTrue(userStorageJpa.isUserExists(SOME_LOGIN,ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenLoginIsPresentIsStore() {
        userStorageJpa.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
        assertTrue(userStorageJpa.isUserExists(SOME_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenIsNotLoginInStore()  {
        assertFalse(userStorageJpa.isUserExists(ANOTHER_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenUserHasInvalidPassword()  {
        userStorageJpa.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
        assertFalse(userStorageJpa.isUserExists(SOME_LOGIN,ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenLoginIsNotPresentIsStore() {
        assertFalse(userStorageJpa.isUserExists(ANOTHER_LOGIN));
    }

}
