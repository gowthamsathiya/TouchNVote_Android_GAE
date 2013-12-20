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
 * Model definition for UserDetails.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the userdetailsendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UserDetails extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String classSelected;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String firstName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lastName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String netID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String password;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestion1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestion2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestion3;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestionAnswer1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestionAnswer2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String securityQuestionAnswer3;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer type;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getClassSelected() {
    return classSelected;
  }

  /**
   * @param classSelected classSelected or {@code null} for none
   */
  public UserDetails setClassSelected(java.lang.String classSelected) {
    this.classSelected = classSelected;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName firstName or {@code null} for none
   */
  public UserDetails setFirstName(java.lang.String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLastName() {
    return lastName;
  }

  /**
   * @param lastName lastName or {@code null} for none
   */
  public UserDetails setLastName(java.lang.String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNetID() {
    return netID;
  }

  /**
   * @param netID netID or {@code null} for none
   */
  public UserDetails setNetID(java.lang.String netID) {
    this.netID = netID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPassword() {
    return password;
  }

  /**
   * @param password password or {@code null} for none
   */
  public UserDetails setPassword(java.lang.String password) {
    this.password = password;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestion1() {
    return securityQuestion1;
  }

  /**
   * @param securityQuestion1 securityQuestion1 or {@code null} for none
   */
  public UserDetails setSecurityQuestion1(java.lang.String securityQuestion1) {
    this.securityQuestion1 = securityQuestion1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestion2() {
    return securityQuestion2;
  }

  /**
   * @param securityQuestion2 securityQuestion2 or {@code null} for none
   */
  public UserDetails setSecurityQuestion2(java.lang.String securityQuestion2) {
    this.securityQuestion2 = securityQuestion2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestion3() {
    return securityQuestion3;
  }

  /**
   * @param securityQuestion3 securityQuestion3 or {@code null} for none
   */
  public UserDetails setSecurityQuestion3(java.lang.String securityQuestion3) {
    this.securityQuestion3 = securityQuestion3;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestionAnswer1() {
    return securityQuestionAnswer1;
  }

  /**
   * @param securityQuestionAnswer1 securityQuestionAnswer1 or {@code null} for none
   */
  public UserDetails setSecurityQuestionAnswer1(java.lang.String securityQuestionAnswer1) {
    this.securityQuestionAnswer1 = securityQuestionAnswer1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestionAnswer2() {
    return securityQuestionAnswer2;
  }

  /**
   * @param securityQuestionAnswer2 securityQuestionAnswer2 or {@code null} for none
   */
  public UserDetails setSecurityQuestionAnswer2(java.lang.String securityQuestionAnswer2) {
    this.securityQuestionAnswer2 = securityQuestionAnswer2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSecurityQuestionAnswer3() {
    return securityQuestionAnswer3;
  }

  /**
   * @param securityQuestionAnswer3 securityQuestionAnswer3 or {@code null} for none
   */
  public UserDetails setSecurityQuestionAnswer3(java.lang.String securityQuestionAnswer3) {
    this.securityQuestionAnswer3 = securityQuestionAnswer3;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getType() {
    return type;
  }

  /**
   * @param type type or {@code null} for none
   */
  public UserDetails setType(java.lang.Integer type) {
    this.type = type;
    return this;
  }

  @Override
  public UserDetails set(String fieldName, Object value) {
    return (UserDetails) super.set(fieldName, value);
  }

  @Override
  public UserDetails clone() {
    return (UserDetails) super.clone();
  }

}
