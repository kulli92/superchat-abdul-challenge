package superchat.challenge.abdul
import model.Contact
import model.User_contact
import repo.ContactRepo
import repo.UserRepo
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.transaction.Transactional



@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactController (val repository: ContactRepo,val usrRepository: UserRepo) {

    private val contacts: ArrayList<Contact> = ArrayList();
    // retrieve user Id from session variables
    private val userId: Long = 1;

    @POST
    @Transactional
    fun create(contact: Contact):Response{
        repository.persist(contact);
        if(contact.isPersistent()){
            usrRepository.persist(User_contact(userId,contact.id as Long))
            return Response.ok(contact).status(201).build()
        }
        return Response.status(Response.Status.BAD_REQUEST).build()
    }


    @GET
    fun findAll() : Response{
        var userContacts = usrRepository.list("user_id",userId).map{ user -> user.contact_id };
        return  Response.ok(repository.listAll()).build() // should filter based on array above
    }

    @GET
    @Path("/{contact}")
    fun getContact(@PathParam("contact") title: String):ArrayList<Contact>{
        return contacts;
    }
}