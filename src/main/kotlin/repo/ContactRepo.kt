package repo

import model.Contact
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class ContactRepo : PanacheRepository<Contact> {
}