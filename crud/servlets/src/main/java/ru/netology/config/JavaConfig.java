package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;
import ru.netology.servlet.MainServlet;

@Configuration
public class JavaConfig {
    @Bean
    // аргумент метода и есть DI
    // название метода - название бина
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }

    @Bean
    public MainServlet mainServlet() {
        return new MainServlet();
    }
}