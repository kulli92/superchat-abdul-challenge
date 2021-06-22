package repo
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import model.User_alias
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class AliasRepo : PanacheRepository<User_alias> {
}