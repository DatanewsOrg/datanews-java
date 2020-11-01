package io.datanews.monitoring;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents HTTP POST body for the {@code monitors/create} request.
 * <p>
 * See <a href="https://datanews.io/docs/monitoring-create">/create endpoint docs</a> for more info.
 */
@Value
@Builder
public class CreateMonitorParams {
  @NonNull String query;
  @NonNull String webhook;
}
