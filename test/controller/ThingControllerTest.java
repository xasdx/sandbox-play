package controller;

import controllers.ThingController;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import services.ThingService;

import static org.assertj.core.api.Assertions.assertThat;
import static play.test.Helpers.contentAsString;

public class ThingControllerTest {

  private ThingController underTest = new ThingController(new ThingService());

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
