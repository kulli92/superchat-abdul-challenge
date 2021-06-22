package superchat.challenge.abdul
import ConversationRepo
import MessageRepo
import model.Contact
import model.Conversation
import model.Message
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.transaction.Transactional




@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageController (val MsgRepository: MessageRepo,val cnvRepository: ConversationRepo) {



    @POST
    @Transactional
    fun send(msg: Message, @QueryParam("sender") sender : String, @QueryParam("sender") receiver : String):Response{
        if(msg.conversation_id ==null){
        // it is a new conversation
            var newConversation = Conversation();
            cnvRepository.persist(newConversation);
            msg.conversation_id = newConversation.id!!
       }

        if(msg.conversation_id != null){
            MsgRepository.persist(msg)
        }
        return Response.ok(msg).status(202).build();
    }

    @GET
    @Path("/{user_id}")
    fun getAllConversations(@PathParam("user_id") user_id:Long):Response{
        return Response.ok(MsgRepository.listAll()).build()
    }


}