package repo
import model.User_contact
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class UserRepo : PanacheRepository<User_contact> {
}