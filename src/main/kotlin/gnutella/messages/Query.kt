package gnutella.messages

import gnutella.peer.Node
import java.util.*

class Query(
    ID: UUID,
    source: Node,
    var propagatorId: String,
    var timeToLive: Int,
    var hops: Int,
    val keyword: String,
) : Message(ID, source) {
    override fun cloneThis(): Message {
        return Query(ID, source, propagatorId, timeToLive, hops, keyword)
    }

    override fun toString(): String {
        return "Query"
    }
}