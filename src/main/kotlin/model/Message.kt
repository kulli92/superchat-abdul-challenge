package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import java.util.UUID;
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="Message")
data class Message(
    var conversation_id: Long = -1,
    var message: String = ""
) : PanacheEntity(){
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false ,insertable= false ,updatable= false)
    private lateinit var conversation: Conversation
}