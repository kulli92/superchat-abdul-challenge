import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import model.Message
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class MessageRepo : PanacheRepository<Message> {
}