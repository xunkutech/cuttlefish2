package com.example.demo.api;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
public interface StudentApi extends RestfulApi {

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class CreateStudentRequestBean {

    @ApiModelProperty(value = "学生姓名")
    String name;

    @ApiModelProperty(value = "学生性别")
    String gender;
  }

  @ApiOperation(
      value = "创建学生",
      nickname = "createStudent",
      notes = "Awesome",
      tags = {
          "student",
      })
  @ApiResponses(value = {@ApiResponse(code = 200, message = "创建学生成功")})
  @PostMapping(value = "")
  default ResponseBean<Void> createStudent(
      @ApiParam(value = "Created user object", required = true) @Valid @RequestBody
          CreateStudentRequestBean input) {
    return new ResponseBean<>();
  }

  @ApiOperation("api测试接口")
  @GetMapping("/hello")
  default ResponseBean<String> test() {
    return new ResponseBean<>("hello world");
  }
}
