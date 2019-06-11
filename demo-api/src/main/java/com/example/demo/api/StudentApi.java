package com.example.demo.api;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiModelProperty(value = "头像")
    private MultipartFile portrait;
  }

  @ApiOperation(
      value = "创建学生",
      nickname = "createStudent",
      notes = "Awesome",
      tags = {
        "student",
      }
  )
  @ApiResponses(value = {@ApiResponse(code = 200, message = "创建学生成功")})
  @PostMapping(value = "/student")
  @ApiImplicitParams (value = {
          @ApiImplicitParam(name = "portrait", paramType = "form", dataType = "file")})
  default ResponseBean<Void> createStudent(
      @ApiParam(value = "Created user object", required = true) @Valid
          CreateStudentRequestBean input) {
    return new ResponseBean<>();
  }

  @ApiOperation("api测试接口")
  @GetMapping("/hello")
  default ResponseBean<String> test() {
    return new ResponseBean<>("hello world");
  }
}
