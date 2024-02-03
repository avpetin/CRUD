package ru.netology.servlet;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MainServlet extends HttpServlet {
  private PostController controller;
  private static String path;
  private static String method;
  private static final String GET = "GET";
  private static final String POST = "POST";
  private static final String DELETE = "DELETE";
  private static final String QUERY = "/api/posts";
  private static final String DELIMITER = "/";

  private ApplicationContext context;
  @Override
  @Bean
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      path = req.getRequestURI();
      method = req.getMethod();
      // primitive routing
      if (method.equals(GET) && path.equals(QUERY)) {
        controller.all(resp);
        return;
      }
      if (method.equals(GET) && path.matches("/api/posts/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf(DELIMITER)));
        controller.getById(id, resp);
        return;
      }
      if (method.equals(POST) && path.equals(QUERY)) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals(DELETE) && path.matches("/api/posts/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf(DELIMITER) + 1));
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

