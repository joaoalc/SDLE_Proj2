package gnutella

import Post
import User
import gnutella.peer.Peer
import gui.GUI
import org.graphstream.graph.Graph
import org.graphstream.graph.implementations.SingleGraph
import java.util.*

fun main() {
    System.setProperty("org.graphstream.ui", "swing")

    val peers = mutableListOf<Peer>()
    val graph: Graph = SingleGraph("Network")
    HostCache()

//    graph.display()

    for (i in 1..20) {
        val peer = Peer(User(i.toString()), graph = graph)
        peer.connect()
        peers.add(peer)
//        Thread.sleep(100)
    }

    peers[7].user.follow(peers[3].user)

    peers[3].storage.addPost(Post(UUID.randomUUID(), "Rãs", peers[3].user))
    peers[3].storage.addPost(Post(UUID.randomUUID(), "Sapos", peers[3].user))

    peers[7].search("4")

    Thread.sleep(200)
    peers[5].storage.addPost(Post(UUID.randomUUID(), "bruh aaa", peers[5].user))
    peers[5].storage.addPost(Post(UUID.randomUUID(), "bruh bbb", peers[6].user))

    val gui = GUI(peers)

    gui.start()
    Thread.sleep(5000)
    peers[4].discover("bruh")
}