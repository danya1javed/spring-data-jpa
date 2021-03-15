package com.example.demo.confguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

//@Configuration
public class ContentNegotiationConfiguration {
  public void contentNegotiationConfiguration(ContentNegotiationConfigurer configurer){
    configurer.favorPathExtension(true).
    favorParameter(false).
    ignoreAcceptHeader(true).
    useJaf(false).
    defaultContentType(MediaType.APPLICATION_JSON);
  }
}
