package io.datanews.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.NonNull;
import lombok.Value;

@Value
public class Monitor {
  @NonNull String id;
  @NonNull String query;
  @NonNull String webhook;
  boolean active;
  @SerializedName("last_run_id")
  String lastRunId;
  @SerializedName("last_run_time")
  Date lastRunTime;
}
