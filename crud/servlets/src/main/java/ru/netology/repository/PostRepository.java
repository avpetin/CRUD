package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentMap<Long, Post> postMap = new ConcurrentHashMap<>();
  private final AtomicLong postId = new AtomicLong(0);
  public List<Post> all() {
    return postMap.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postMap.get(id));
  }

  public Post save(Post post) {
    if(post.getId() == 0){
      post.setId(postId.addAndGet(1));
      postMap.put(postId.longValue(), post);
    }
    else{
      if(getById(post.getId()).isPresent()) {
        postMap.replace(post.getId(), post);
      }
      else{
        postMap.put(postId.longValue(), post);
      }
    }
    return post;
  }

  public void removeById(long id) {
    if(getById(id).isPresent()){
      postMap.remove(id);
    }
  }
}
