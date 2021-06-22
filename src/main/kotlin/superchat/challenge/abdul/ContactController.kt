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
    fun findAll(@QueryParam("userId") userId:String) : Response{
        var query = "select c from Contact c inner join User_contact u on c.id = u.contact_id where u.user_id = $userId"
        return  Response.ok(repository.find(query).list()).build() // should filter based on array above
    }

//    @GET
//    @Path("/{contact}")
//    fun getContact(@PathParam("contact") title: String){
//        return null;
//    }
}