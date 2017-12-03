import io.joshworks.restclient.http.HttpResponse;
import io.joshworks.restclient.http.Unirest;
import org.junit.Test;
import play.mvc.Http;
import play.test.WithServer;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends WithServer {

  @Test
  public void saysHi() {
    HttpResponse<String> response = Unirest
      .get(String.format("http://localhost:%s/", this.testServer.port()))
      .asString();

    assertThat(response.getStatus()).isEqualTo(Http.Status.OK);
    assertThat(response.getBody()).endsWith("hi");
  }
}
