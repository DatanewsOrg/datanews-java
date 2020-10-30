package io.datanews.monitoring;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateMonitorParams {
  @NonNull String query;
  @NonNull String webhook;
}
