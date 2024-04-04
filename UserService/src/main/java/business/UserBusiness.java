package business;

import persistence.*;
import helper.*;

public class UserBusiness {
    public UserXML getUser(int uid) {
        User user = UserCRUD.getUser(uid);
        UserXML ux = new UserXML();
        ux.setUser(user);
        return ux;
    }

    public UserXML getUser(String usr, String psw) {
        User user = UserCRUD.getUser(usr, psw);
        UserXML ux = new UserXML();
        ux.setUser(user);
        return ux;
    }
}