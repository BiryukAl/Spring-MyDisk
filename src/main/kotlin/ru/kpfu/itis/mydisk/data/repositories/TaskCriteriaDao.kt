package ru.kpfu.itis.mydisk.data.repositories

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import ru.kpfu.itis.mydisk.data.entity.Post


@Repository
class TaskCriteriaDao(
    private val entityManager: EntityManager,
) {

    fun findOnPost(query: String): List<Post> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Post::class.java)
        val post = criteriaQuery.from(Post::class.java)



        criteriaQuery
            .where(
                criteriaBuilder.or(
                    criteriaBuilder.like(post.get("title"), "%$query%"),
                    criteriaBuilder.like(post.get("description"), "%$query%")
                )
            )

        return entityManager.createQuery(criteriaQuery).resultList
    }


    /*
        fun commentedPostOnUser(
            user: User,
        ): List<Post> {
            val builder = entityManager.criteriaBuilder
            val query = builder.createQuery(Post::class.java)
            val p = query.from(Post::class.java)

            val subQuery = query.subquery(Int::class.java)
            val pc = subQuery.from(Comment::class.java)
            subQuery
                .select(builder.literal(1))
                .where(
                    builder.equal(pc.get("post_id"),   p),
                    builder.equal(pc.get("holder_id"), user)
                )

            query.where(builder.exists(subQuery))
            return entityManager.createQuery(query).resultList
        }

    */

}
