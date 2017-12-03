package controller;

import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import service.HomeService;

import static org.assertj.core.api.Assertions.assertThat;
import static play.test.Helpers.contentAsString;

public class HomeControllerTest {

  private HomeController underTest = new HomeController(new HomeService());

  @Test
  public void returnsPathVariable() {
    String customId = "id";

    Result result = underTest.pathVariable(customId);

    assertThat(result.status()).isEqualTo(Http.Status.OK);
    assertThat(contentAsString(result)).endsWith(customId);
  }

  @Test
  public void returnsJsonContentType() {
    Result result = underTest.things();

    assertThat(result.status()).isEqualTo(Http.Status.OK);
    assertThat(result.contentType()).isPresent();
    assertThat(result.contentType().get()).isEqualTo(Http.MimeTypes.JSON);
  }
}
