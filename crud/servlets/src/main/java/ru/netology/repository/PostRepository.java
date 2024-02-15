package ru.netology.repository;

import org.springframework.stereotype.Component;
import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;

@Component
public interface PostRepository {
    List<Post> all();

    Optional<Post> getById(long id);

    Post save(Post post);

    void removeById(long id);
}

