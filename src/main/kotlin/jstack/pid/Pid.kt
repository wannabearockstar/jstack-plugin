package jstack.pid

/**
 * Created by vChiper on 4/24/2016.
 */
data class Pid(val id: Long = 0, val name: String = "") {
    override fun toString(): String {
        return "$id@$name"
    }
}