
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import model.Conversation
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class ConversationRepo : PanacheRepository<Conversation> {
}