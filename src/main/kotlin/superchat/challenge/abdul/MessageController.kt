package superchat.challenge.abdul
import ConversationRepo
import MessageRepo
import dto.MessageDto
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
    fun send(msg: MessageDto):Response{
        if(msg.conversation_id < 0){
            var newConversation = Conversation(msg.sender_id,msg.receiver_id,"title");
            cnvRepository.persist(newConversation);
            msg.conversation_id = newConversation.id!!
       }
        if(msg.conversation_id > 0){
            var newMsg = Message(msg.conversation_id,msg.message)
            MsgRepository.persist(newMsg)
            return Response.ok(msg).status(200).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build()

    }

    @GET
    @Path("/{userId}")
    fun getAllConversations(@PathParam("userId") userId:Long):Response{
        var query = "select distinct c from Conversation c inner join Message m on c.id = m.conversation_id where c.user_id = $userId "
        return Response.ok(cnvRepository.list(query)).build()
    }


}