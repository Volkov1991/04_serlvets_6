package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository {
  private final AtomicLong countId = new AtomicLong(1);
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<Long, Post>();
  public List<Post> all() {
    if (posts.isEmpty()) {
      return Collections.emptyList();
    }
    return new ArrayList<Post>(posts.values());
  }

  public Optional<Post> getById(long id) {
    Optional<Post> post = Optional.empty(); // ????
    if (posts.containsKey(id)) {
      post = Optional.of(posts.get(id));
    }
    return post;
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = countId.getAndIncrement();
      post.setId(id);
      posts.put(id, post);
    } else {
      posts.put(post.getId(), post);
    }
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
