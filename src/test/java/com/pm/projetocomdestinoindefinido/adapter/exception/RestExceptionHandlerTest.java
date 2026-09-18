package com.pm.projetocomdestinoindefinido.adapter.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class RestExceptionHandlerTest {
  @Test void generalExceptionProducesInternalServerErrorPayload() throws Exception {
    var response = new RestExceptionHandler().handleGeneral(new IllegalStateException("boom"));
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(response.getBody()).containsEntry("error", "Internal Server Error").containsEntry("message", "boom").containsEntry("status", 500);
    assertThat(response.getBody()).containsKey("timestamp");
  }
}
