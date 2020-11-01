package io.datanews.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.datanews.model.Article;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents a response from {@code news} and {@code headlines} endpoints.
 * <p>
 * See <a href="https://datanews.io/docs/headlines">Headlines API docs</a> and
 * <a href="https://datanews.io/docs/news">News API docs</a> for more info.
 */
@Value
public class NewsResponse {
  int numResults;
  @SerializedName("hits")
  @NonNull List<Article> articles;
}
