package com.example.demo.api;

import com.example.demo.api.bean.ResponseBean;
import com.sun.javafx.binding.StringFormatter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Base64;

/**
 * @author : Sarah Xu
 * @date : 2019-06-10
 **/
@RestController
@RequestMapping(value = "/api/v1/images", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Image", tags = "Image")
public interface ImageApi extends RestfulApi{

    @Data
     class ImageResponseBean {
        @ApiModelProperty(value = "资源id")
        private String resourceId;
        @ApiModelProperty(value = "mime类型")
        private String mimeType;
        @ApiModelProperty(value = "base64图片信息字符串")
        private String resourceBase64;

        @Builder
        public ImageResponseBean(String resourceId, String mimeType, byte[] resourceDataBytes) {
            this.resourceId = resourceId;
            this.resourceBase64 = MessageFormat.format( "data:{0};base64,{1}" ,mimeType, new String(Base64.getEncoder().encode(resourceDataBytes)));
            this.mimeType=mimeType;
        }
    }
    @ApiOperation("通过RESTful API获取图片")
    @GetMapping("/{id}")
    default ResponseBean<ImageResponseBean> getImage(@PathVariable("id") String imageId) {
        return new ResponseBean<>(new ImageResponseBean("DSWQ1231","img/jpg",null));
    }

}
