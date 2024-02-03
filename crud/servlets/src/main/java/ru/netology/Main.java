package ru.netology;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.netology.config.JavaConfig;
import ru.netology.service.PostService;
import ru.netology.servlet.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@Component
public class Main {
    public static void main(String[] args) throws ServletException {
        // отдаём класс конфигурации
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);

        // получаем по имени бина
        final var controller = context.getBean("postController");

        // получаем по классу бина
        final var service = context.getBean(PostService.class);

        // по умолчанию создаётся лишь один объект на BeanDefinition
        final var isSame = service == context.getBean("postService");

        MainServlet mainServlet = context.getBean(MainServlet.class);
    }
}
