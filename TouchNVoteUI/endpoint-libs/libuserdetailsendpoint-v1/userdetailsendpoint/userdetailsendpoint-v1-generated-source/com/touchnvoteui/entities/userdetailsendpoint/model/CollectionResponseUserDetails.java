/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-11-22 19:59:01 UTC)
 * on 2013-11-27 at 07:09:07 UTC 
 * Modify at your own risk.
 */

package com.touchnvoteui.entities.userdetailsendpoint.model;

/**
 * Model definition for CollectionResponseUserDetails.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the userdetailsendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CollectionResponseUserDetails extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<UserDetails> items;

  static {
    // hack to force ProGuard to consider UserDetails used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(UserDetails.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nextPageToken;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<UserDetails> getItems() {
    return items;
  }

  /**
   * @param items items or {@code null} for none
   */
  public CollectionResponseUserDetails setItems(java.util.List<UserDetails> items) {
    this.items = items;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNextPageToken() {
    return nextPageToken;
  }

  /**
   * @param nextPageToken nextPageToken or {@code null} for none
   */
  public CollectionResponseUserDetails setNextPageToken(java.lang.String nextPageToken) {
    this.nextPageToken = nextPageToken;
    return this;
  }

  @Override
  public CollectionResponseUserDetails set(String fieldName, Object value) {
    return (CollectionResponseUserDetails) super.set(fieldName, value);
  }

  @Override
  public CollectionResponseUserDetails clone() {
    return (CollectionResponseUserDetails) super.clone();
  }

}
