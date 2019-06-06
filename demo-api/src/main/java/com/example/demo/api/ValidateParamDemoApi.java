package com.example.demo.api;

import com.example.demo.dao.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.validation.constraints.*;

/**
 * Created by jf on 2019/5/28.
 */
@RestController
@Api(value = "validate", tags = "demo validate Module")
@RequestMapping("/api/v1/validate")
public interface ValidateParamDemoApi {

    /*@Null 被注释的元素必须为 null
    @NotNull 被注释的元素必须不为 null
    @AssertTrue 被注释的元素必须为 true
    @AssertFalse 被注释的元素必须为 false
    @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @Size(max=, min=) 被注释的元素的大小必须在指定的范围内
    @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
    @Past 被注释的元素必须是一个过去的日期
    @Future 被注释的元素必须是一个将来的日期
    @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
    @NotBlank(message =) 验证字符串非null，且长度必须大于0
    @Email 被注释的元素必须是电子邮箱地址
    @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
    @NotEmpty 被注释的字符串的必须非空
    @Range(min=,max=,message=) 被注释的元素必须在合适的范围内

    验证异常信息捕获
    @ExceptionHandler(BindException.class)
    public ResponseBase handleException(BindException e) {
        log.error("handleException :", e);
        return new ResponseBase(1001, e.getFieldError().getDefaultMessage());
    }
    */

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class GetStudentInfoRequestBean {
        @ApiModelProperty(value = "学生ID", name = "customerId",example = "sddfsdfds21")
        @NotBlank(message = "student.id.null")
        String customerId;

        @ApiModelProperty(value = "年龄", name = "age",example = "20")
        @Min(value = 18, message = "age.lower.than.baseline")
        @Max(value = 120,message = "age.beyond.upper.limit")
        private Integer age;

        @ApiModelProperty(value = "邮箱", name = "email",example = "123213@qq.com")
        @Email(message = "email.format.error=")
        private String email;

        @ApiModelProperty(value = "手机号", name = "mobile",example = "13111111111")
        @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$", message = "mobile.format.error")
        private String mobile;

        @ApiModelProperty(value = "备注", name = "mark",example = "调皮")
        @Length(max=20,message = "remart.words.exceed")
        private String mark;

    }

    @ApiOperation(value = "获取学生信息")
    @GetMapping("getStudentInfo")
    default String getStudentInfo(@Validated GetStudentInfoRequestBean input){
        return JsonUtils.toJson(input);
    }
}
