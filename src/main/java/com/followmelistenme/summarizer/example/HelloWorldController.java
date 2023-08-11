package com.followmelistenme.summarizer.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  @RequestMapping()
  public String lol() {
    return "lol";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/kek", params = {"url"})
  @ResponseBody
  public String kek(@RequestParam("url") @DefaultValue("http://backend:8080") String url) throws URISyntaxException, IOException, InterruptedException {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url))
          .GET()
          .build();

      HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());

      return "kek->%s".formatted(response.body());
    } catch (RuntimeException e) {
      return e.getMessage();
    }
  }
}
