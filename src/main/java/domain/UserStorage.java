package domain;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private final Map<String,User> users=new HashMap<>();

public boolean isUserExists(String login){
    return users.containsKey(login);
}

public boolean isUserExists(String login, String password){
    if (users.containsKey(login)){
        return users.get(login).hasPasswordEqualTo(password);
    } return false;
}

public void addUser(User user)  {
    if(isUserExists(user.getLogin())) throw new UserAlreadyExistsException();
        users.put(user.getLogin(),user);
    }

}
