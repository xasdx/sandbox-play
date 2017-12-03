package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ThingService;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ThingController extends Controller {

  private final ThingService service;

  @Inject
  public ThingController(ThingService service) {
    this.service = service;
  }

  public Result index() {
    String requestHeaderContent = extractHeaders();
    return ok(requestHeaderContent.concat("\n").concat(service.sayHi()));
  }

  public Result pathVariable(String id) {
    return ok(String.format("Getting resource with id: %s", id));
  }

  public Result things() {
    JsonNode json = Json.toJson(asList(service.randomThing(), service.randomThing()));
    return ok(json);
  }

  public Result thing() {
    return ok(views.html.thing.render(service.randomThing()));
  }

  private String extractHeaders() {
    return request().getHeaders().toMap().entrySet().stream()
      .map(entrySet -> String.format("%s=%s", entrySet.getKey(), entrySet.getValue()))
      .collect(Collectors.joining("\n"));
  }
}
