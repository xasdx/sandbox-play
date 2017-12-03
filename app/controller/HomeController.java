package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import model.Thing;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.HomeService;

import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;

public class HomeController extends Controller {

  private final HomeService service;

  @Inject
  public HomeController(HomeService service) {
    this.service = service;
  }

  public Result index() {
    String requestHeaderContent = request().getHeaders().toMap().entrySet().stream()
      .map(entrySet -> String.format("%s=%s", entrySet.getKey(), entrySet.getValue()))
      .collect(Collectors.joining("\n"));

    return ok(requestHeaderContent.concat("\n").concat(service.sayHi()));
  }

  public Result pathVariable(String id) {
    return ok(String.format("Getting resource with id: %s", id));
  }

  public Result things() {
    Thing thing = new Thing(randomUUID().toString(), "MyThing");
    JsonNode json = Json.toJson(singletonList(thing));
    return ok(json);
  }
}
