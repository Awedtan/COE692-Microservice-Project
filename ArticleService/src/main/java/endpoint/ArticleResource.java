package endpoint;

import java.io.StringWriter;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.xml.bind.*;
import business.*;
import helper.*;

@Path("")
public class ArticleResource {

    @Context
    private UriInfo context;

    public ArticleResource() {
    }

    // @POST
    // @Path("create")
    // @Produces(MediaType.TEXT_HTML)
    // public boolean createArticle(@FormParam("uid") int uid, @FormParam("title") String title,
    //         @FormParam("content") String content, @FormParam("token") String token) {
    //     ArticleBusiness ab = new ArticleBusiness();
    //     boolean response = ab.insertArticle(uid, title, content, token);
    //     return response;
    // }

    @GET
    @Path("public")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getPublic() {
        ArticleBusiness ab = new ArticleBusiness();
        ArticlesXML articles = ab.getPublicArticles();

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ArticlesXML.class);
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

    @GET
    @Path("user/{query}")
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getUser(@PathParam("query") String query) {
        ArticleBusiness ab = new ArticleBusiness();
        ArticlesXML articles = ab.getUserArticles(query);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ArticlesXML.class);
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