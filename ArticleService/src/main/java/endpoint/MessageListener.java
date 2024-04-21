package endpoint;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import business.Messaging;

public class MessageListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent e) {
    }

    @Override
    public void contextInitialized(ServletContextEvent e) {
        Runnable r = new Runnable() {
            public void run() {
                Messaging.Receiving_Events_Store("createArticleChannel");
            }
        };
        new Thread(r).start();
    }
}
