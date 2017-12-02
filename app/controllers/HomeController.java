package controllers;

import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import services.HomeService;

import java.util.stream.Collectors;

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
}
