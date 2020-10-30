package io.datanews.monitoring;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import io.datanews.model.Article;
import lombok.NonNull;
import lombok.Value;

@Value
public class MonitorsLatestResponse {
  @NonNull String id;

  @SerializedName("monitor_id")
  @NonNull String monitorId;

  @NonNull String query;

  @SerializedName("run_time")
  @NonNull Date runTime;

  @SerializedName("last_run_id")
  String lastRunId;

  @SerializedName("last_run_time")
  Date lastRunTime;

  @NonNull List<Article> articles;
}
