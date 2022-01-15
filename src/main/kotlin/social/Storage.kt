package social

import java.util.*

class Storage {
    val posts = mutableMapOf<User, MutableList<Post>>()
    val searchPosts = mutableSetOf<Post>()

    fun emptySearchPosts() {
        searchPosts.removeAll(searchPosts)
    }

    fun addSearchPosts(postList: List<Post>) {
        for (p in postList) {
            searchPosts.add(p)
        }
    }

    fun addPost(post: Post) {
        if (post.author !in posts)
            posts[post.author] = mutableListOf()

        posts[post.author]?.add(post)
    }

    fun digest(user: User): Digest {
        val postIDs = mutableSetOf<UUID>()

        if (user in posts)
            for (post in posts[user]!!)
                postIDs.add(post.ID)

        return Digest(user, postIDs)
    }

    fun retrievePosts(digest: Digest): List<Post>? {
        return posts[digest.user]?.filter { it.ID in digest.postIDs }
    }

    fun timeline(user: User): List<Post> =
        posts.filter { it.key in user.following || it.key == user }
            .values
            .flatten()
            .sortedByDescending { it.date }

    fun findMatchingPosts(keywordString: String): MutableSet<Post> {
        val keywords = keywordString.split(" ").toMutableList()
        val results = mutableSetOf<Post>()

//        // Match users
//        for (i in keywords) {
//            if (i.slice(0..4) == "user:") {
//                val strLength = i.length
//                
//                if (posts[User(i.slice(5 until strLength))] != null)
//                    results.addAll(posts[User(i.slice(5 until strLength))]!!)
//                
//                keywords.remove(i)
//            }
//        }

        // Match posts
        for (u in posts.values) {
            for (p in u) {
                val list = p.content.split(" ")

                for (w in list) {
                    if (w in keywords) {
                        results.add(p)

                        break
                    }
                }
            }
        }
        
        print("Search results: ${results}")

        return results
    }
}