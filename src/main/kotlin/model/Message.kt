package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="Message")
data class Message(
    var conversation_id: Long = -1,
    var message: String = "",
    var created_date: LocalDateTime = LocalDateTime.now(),
) : PanacheEntity(){
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false ,insertable= false ,updatable= false)
    private lateinit var conversation: Conversation
}