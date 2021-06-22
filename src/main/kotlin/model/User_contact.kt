
package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*
import javax.persistence.JoinColumn


@Entity
@Table(name="User_contact")
data class User_contact(
    var user_id: Long = 0,
    var contact_id: Long = 0
) : PanacheEntity(){

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,insertable= false ,updatable= false)
    private lateinit var user: Account
}