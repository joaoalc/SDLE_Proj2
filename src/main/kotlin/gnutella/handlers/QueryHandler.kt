package gnutella.handlers

import gnutella.messages.Message
import gnutella.peer.Peer

class QueryHandler(
    private val peer: Peer,
    private val message: Message
) : MessageHandler(message), Runnable {
    override fun run() {

    }
}