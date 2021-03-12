package com.example.demo.common;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class APIResponse {
  private Integer status;
  private Object data;
}
