package business;

import java.io.IOException;
import java.util.Map.Entry;
import javax.net.ssl.SSLException;
import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;
import persistence.ArticleCRUD;

public class Messaging {
    public static void Receiving_Events_Store(String channelName) {
        try {
            String clientID = "createArticleSubscriber";
            String kmqAddress = System.getenv("KMQ_ADDRESS");
            Subscriber subscriber = new Subscriber(kmqAddress);
            SubscribeRequest sr = new SubscribeRequest();
            sr.setChannel(channelName);
            sr.setClientID(clientID);
            sr.setSubscribeType(SubscribeType.EventsStore);
            sr.setEventsStoreType(EventsStoreType.StartAtSequence);
            sr.setEventsStoreTypeValue(1);
            StreamObserver<EventReceive> so = new StreamObserver<EventReceive>() {
                @Override
                public void onNext(EventReceive value) {
                    try {
                        String val = (String) Converter.FromByteArray(value.getBody());

                        System.out.printf("Event received. EventID: %s, Channel: %s, Meta: %s, Body: %s\n",
                                value.getEventId(), value.getChannel(), value.getMetadata(),
                                Converter.FromByteArray(value.getBody()));

                        String[] msgParts = val.split(";");
                        if (msgParts.length == 5 && msgParts[0].equals("CREATE")) {
                            int uid = Integer.parseInt(msgParts[1]);
                            String title = msgParts[2];
                            String content = msgParts[3];
                            String token = msgParts[4];

                            Entry<Boolean, String> valid = Authenticate.verifyToken(token);
                            if (valid.getKey() && Integer.parseInt(valid.getValue()) == uid) {
                                ArticleCRUD.insertArticle(uid, title, content);
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable t) {
                }

                @Override
                public void onCompleted() {
                }
            };
            subscriber.SubscribeToEvents(sr, so);
        } catch (SSLException e) {
            System.out.println("SSLE: " + e.getMessage());
            e.printStackTrace();
        } catch (ServerAddressNotSuppliedException e) {
            System.out.println("SANSE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}