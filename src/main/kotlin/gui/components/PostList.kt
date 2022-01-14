package gnutella.gui.components

import gnutella.peer.Peer
import social.Post
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JSeparator
import javax.swing.SwingConstants

class PostList(val peer: Peer): JPanel() {
    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        
        refresh()
    }

    private fun addPost(post: Post) {
        val separator = JSeparator(SwingConstants.HORIZONTAL)

        add(separator)
        add(PostPanel(peer.user, post))
    }

    fun refresh() {
        removeAll()
        
        for (post in peer.timeline())
            addPost(post)
        
        revalidate()
        repaint()
    }
}