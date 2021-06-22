package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Table(name="Conversation")
data class Conversation(
    var user_id: Long = -1,
    var contact_id: Long = -1,
    var title: String = ""
) : PanacheEntity(){
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,insertable= false ,updatable= false)
    private lateinit var user: Account

    @OneToMany(fetch = FetchType.LAZY,mappedBy="conversation")
    private val messages: Set<Message>? = null
}