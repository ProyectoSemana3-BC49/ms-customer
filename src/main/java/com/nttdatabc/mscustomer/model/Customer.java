package com.nttdatabc.mscustomer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Customer.
 */

@Document(value = "customer")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T10:47:15.590547700-05:00[America/Lima]")
public class Customer {

  private String id;

  private String identifier;

  private String fullname;

  private String type;

  private String address;

  private String phone;

  private String email;

  private String birthday;

  @Valid
  private List<@Valid AuthorizedSigner> authorizedSigners;

  public Customer id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id.
   * @return id.
   */

  @Schema(name = "_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Customer identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Get identifier
   * @return identifier
   */

  @Schema(name = "identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("identifier")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Customer fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  /**
   * Get fullname
   * @return fullname
   */

  @Schema(name = "fullname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fullname")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Customer type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */

  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Customer address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address.
   * @return address.
   */

  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Customer phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   */

  @Schema(name = "phone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Customer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */

  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer birthday(String birthday) {
    this.birthday = birthday;
    return this;
  }

  /**
   * Get birthday
   * @return birthday
   */

  @Schema(name = "birthday", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("birthday")
  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Customer authorizedSigners(List<@Valid AuthorizedSigner> authorizedSigners) {
    this.authorizedSigners = authorizedSigners;
    return this;
  }

  /**
   * Add authorized.
   *
   * @param authorizedSignersItem item.
   * @return customer.
   */
  public Customer addAuthorizedSignersItem(AuthorizedSigner authorizedSignersItem) {
    if (this.authorizedSigners == null) {
      this.authorizedSigners = new ArrayList<>();
    }
    this.authorizedSigners.add(authorizedSignersItem);
    return this;
  }

  /**
   * Get authorizedSigners
   * @return authorizedSigners
   */
  @Valid
  @Schema(name = "authorizedSigners", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorizedSigners")
  public List<@Valid AuthorizedSigner> getAuthorizedSigners() {
    return authorizedSigners;
  }

  public void setAuthorizedSigners(List<@Valid AuthorizedSigner> authorizedSigners) {
    this.authorizedSigners = authorizedSigners;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.id, customer.id)
        && Objects.equals(this.identifier, customer.identifier)
        && Objects.equals(this.fullname, customer.fullname)
        && Objects.equals(this.type, customer.type)
        && Objects.equals(this.address, customer.address)
        && Objects.equals(this.phone, customer.phone)
        && Objects.equals(this.email, customer.email)
        && Objects.equals(this.birthday, customer.birthday)
        && Objects.equals(this.authorizedSigners, customer.authorizedSigners);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identifier, fullname, type, address, phone, email, birthday, authorizedSigners);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    authorizedSigners: ").append(toIndentedString(authorizedSigners)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
