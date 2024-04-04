package endpoint;

import java.io.StringWriter;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.xml.bind.*;
import business.*;
import helper.*;

@Path("")
public class UserResource {

    @Context
    private UriInfo context;

    public UserResource() {
    }

    @GET
    @Path("user/{uid}")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("uid") String uid) {
        UserBusiness ub = new UserBusiness();
        UserXML user = ub.getUser(Integer.parseInt(uid));

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(UserXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(user, sw);
            return (sw.toString());
        } catch (JAXBException ex) {
            System.out.println(ex);
            return ("Oops an error occurred");
        }
    }

    @GET
    @Path("user/{usr}/{psw}")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("usr") String usr, @PathParam("psw") String psw) {
        UserBusiness ub = new UserBusiness();
        UserXML user = ub.getUser(usr, psw);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(UserXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(user, sw);
            return (sw.toString());
        } catch (JAXBException ex) {
            System.out.println(ex);
            return ("Oops an error occurred");
        }
    }
}