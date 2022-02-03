package uz.umarxon.road_rules.DB

import uz.umarxon.road_rules.Models.User

interface Service {
    fun addMember(user: User)
    fun updateMember(user: User):Int
    fun deleteMember(user: User)
    fun getAllMember():ArrayList<User>
}