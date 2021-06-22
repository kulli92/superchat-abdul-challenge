package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name="Contact")
data class Contact(
    var first_name: String = "test",
    var last_name: String = "test",
    var phone: String = "123456",
    var is_active: Boolean = true
    ) : PanacheEntity(){
    @OneToMany(fetch = FetchType.LAZY,mappedBy="contact")
    private val conversations: Set<Conversation>? = null
    }