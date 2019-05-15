package com.example.demo.dao.bean;

import com.example.demo.dao.util.JsonUtils;

public interface Jsonable {

  default String toJson() {
    return JsonUtils.toJson(this);
  }
}
