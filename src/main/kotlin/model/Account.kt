package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*


@Entity
@Table(name="Account")
data class Account(
    var first_name: String = "",
    var last_name: String = "",
    var phone: String = "",
    var is_active: Boolean = false
) : PanacheEntity(){
    @OneToMany(mappedBy="user")
    private val contacts: Set<User_contact>? = null

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private val conversations: Set<Conversation>? = null
}