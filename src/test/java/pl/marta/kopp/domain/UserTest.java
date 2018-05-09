package pl.marta.kopp.domain;

import domain.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
    private static final String SOME_LOGIN="Orlando";
    private static final String SOME_PASSWORD="Bloom";
    private static final String DIFFERENT_PASSWORD="Smith";
    
    @Test
    public void shouldRecognizeWhenPasswordIsDifferent()  {
assertFalse(givenUser().hasPasswordEqualTo(DIFFERENT_PASSWORD));
    }

    @Test
    public void ShouldRecognizeWhenPasswordIsEqual()  {
        assertTrue(givenUser().hasPasswordEqualTo(SOME_PASSWORD));
    }

    private User givenUser() {
        return new User(SOME_LOGIN,SOME_PASSWORD);
    }
}
