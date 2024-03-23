package endpoint;

import java.io.StringWriter;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.xml.bind.*;
import business.*;
import helper.*;

@Path("article/{query}")
public class ArticleResource {

    @Context
    private UriInfo context;

    public ArticleResource() {
    }

    @GET
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

    /**
     * PUT method for updating or creating an instance of SearchResource
     * 
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}