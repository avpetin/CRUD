package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Stub
public class PostRepository {
  private final ConcurrentMap<Long, Post> postMap = new ConcurrentHashMap<>();
  private long postId;
  public List<Post> all() {
    return postMap.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postMap.get(id));
  }

  public Post save(Post post) {
    if(post.getId() == 0){
      post.setId(++postId);
      postMap.put(postId, post);
    }
    else{
      if(getById(post.getId()).isPresent()) {
        postMap.replace(post.getId(), post);
      }
      else{
        postMap.put(postId, post);
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
