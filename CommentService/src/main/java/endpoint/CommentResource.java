package endpoint;

import java.io.StringWriter;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.xml.bind.*;
import business.*;
import helper.*;

@Path("")
public class CommentResource {

    @Context
    private UriInfo context;

    public CommentResource() {
    }

    @GET
    @Path("article/{query}")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        CommentBusiness cb = new CommentBusiness();
        CommentsXML articles = cb.getArticleComments(query);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(CommentsXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(articles, sw);
            return (sw.toString());
        } catch (JAXBException ex) {
            System.out.println(ex);
            return ("Oops an error occurred");
        }
    }
}