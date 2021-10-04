package com.eomcs.server;

import java.util.Map;
import com.google.gson.Gson;

public class Request {

  String command;
  String jsonData;
  Map<String, String> params;


  public Request(String command, String jsonData) {
    this.command = command;
    this.jsonData = jsonData;
  }

  public String getCommand() {
    return command;
  }

  public <T> T getObject(Class<T> type) {
    if (jsonData == null || jsonData.length() == 0) {
      return null;
    }
    return new Gson().fromJson(jsonData, type);
  }

  @SuppressWarnings("unchecked")
  public String getParameter(String name) {
    try {
      if (params == null) {
        params = new Gson().fromJson(jsonData, Map.class);
      }
      return params.get(name);
    } catch (Exception e) {
      return null;
    }
  }
}
