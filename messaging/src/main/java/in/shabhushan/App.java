package in.shabhushan;

import in.shabhushan.models.Message;
import in.shabhushan.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Hello world!
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("Hello World!");

        transaction(session -> {
            Message outsideMessage = new Message("Outside Message");
            outsideMessage.setUuid(UUID.randomUUID());

            session.save(outsideMessage);
        });

        transaction(session -> {
            Message message = new Message("Message");

            Message nextMessage = new Message("second message");
            message.setNextMessage(nextMessage);
            message.setUuid(UUID.randomUUID());

            Message differentMessage = new Message("New message");
            differentMessage.setUuid(UUID.randomUUID());

            session.save(message);
            session.save(differentMessage);

            session.persist(nextMessage);

            throw new RuntimeException("Should rollback transaction");
        });

//        Query q = session.createQuery("From messages ");
//
//        List<Message> resultList = q.getResultList();
//        System.out.println("num of employess:" + resultList.size());
//
//        for (Message next : resultList) {
//            System.out.println("next employee: " + next);
//        }

    }

    private static void transaction(Consumer<Session> operation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                operation.accept(session);

                transaction.commit();
            } catch (Exception cause) {
                LOG.error("Error, cause {}", cause.getMessage());
                transaction.rollback();
            }
        }
    }
}
