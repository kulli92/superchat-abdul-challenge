package superchat.challenge.abdul
import ConversationRepo
import MessageRepo
import dto.MessageDto
import model.Conversation
import model.Message
import java.net.HttpURLConnection
import java.net.URL
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
        //logic related to sending the message via api call or anything...
        // ...
        //insert the message if sent successfully
        var savedMsg = saveMessage(msg)
        if(savedMsg.isPersistent()){
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

    @GET
    @Path("/invoke")
    fun invokeServiceToSendMsg():Response{

        // this URL will invoke a lambda to broadcast and event of new message and HTTP PUT with data to sendMsgViaWebhook API here
        // but when running locally it won't trigger.
        val url = URL("https://vczf7atrjf.execute-api.ap-southeast-1.amazonaws.com/stg/webhook")
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET
            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
        }
        return Response.status(Response.Status.ACCEPTED).build()
    }

    @PUT
    // exposed webhook URL
    fun sendMsgViaWebhook(msg: MessageDto){
     this.saveMessage(msg);
    }


    // TODO : move it to a utility class
    private fun saveMessage(msg: MessageDto): Message {
        if (msg.conversation_id < 0) {
            var newConversation = Conversation(msg.sender_id, msg.receiver_id, "title");
            cnvRepository.persist(newConversation);
            msg.conversation_id = newConversation.id!!
        }

        var savedMsg = Message(msg.conversation_id, msg.message)
        MsgRepository.persist(savedMsg)
        return savedMsg
    }
}