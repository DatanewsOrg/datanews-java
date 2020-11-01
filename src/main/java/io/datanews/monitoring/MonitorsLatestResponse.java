package io.datanews.monitoring;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

import io.datanews.model.Article;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents a response from {@code monitors/latest} endpoint.
 * <p>
 * See <a href="https://datanews.io/docs/monitoring-latest">/latest endpoint docs</a> for more info.
 */
@Value
public class MonitorsLatestResponse {
  @NonNull
  String id;

  @SerializedName("monitor_id")
  @NonNull
  String monitorId;

  @NonNull
  String query;

  @SerializedName("run_time")
  @NonNull
  Date runTime;

  @SerializedName("last_run_id")
  @Nullable
  String lastRunId;

  @SerializedName("last_run_time")
  @Nullable
  Date lastRunTime;

  @NonNull
  List<Article> articles;
}
