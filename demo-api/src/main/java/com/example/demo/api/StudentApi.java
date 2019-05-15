package com.example.demo.api;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public interface StudentApi extends RestfulApi {

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class createStudentRequestBean {
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
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  default ResponseBean<Void> createStudent(
      @ApiParam(value = "Created user object", required = true) @Valid @RequestBody
          createStudentRequestBean input) {
    return new ResponseBean<>();
  }
}
