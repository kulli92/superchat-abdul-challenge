package model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Table(name="User_alias")
data class User_alias(
    var key: String = "",
    var value: String = ""
) : PanacheEntity(){

}