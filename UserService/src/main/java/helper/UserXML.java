package helper;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXML {
    @XmlElement(name = "user")
    private User user;

    public UserXML() {
    }

    public UserXML setUser(User user) {
        this.user = user;
        return this;
    }

    public User getUser() {
        return user;
    }
}