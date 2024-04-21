package business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Channel;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;
import java.io.IOException;
import javax.net.ssl.SSLException;

public class Messaging {
    public static void sendMessage(String msg) {
        try {
            String channelName = "createArticleChannel";
            String clientID = "createArticlePublisher";
            String kmqAddress = System.getenv("KMQ_ADDRESS");

            Channel channel = new Channel(channelName, clientID, true, kmqAddress);
            Event event = new Event();
            event.setBody(Converter.ToByteArray(msg));
            event.setEventId("event-store-");

            channel.SendEvent(event);
        } catch (SSLException e) {
            System.out.println("SSLE: " + e.getMessage());
            e.printStackTrace();
        } catch (ServerAddressNotSuppliedException e) {
            System.out.println("SANSE: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}