package com.nttdatabc.mscustomer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import javax.annotation.Generated;

/**
 * AuthorizedSigner.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T10:47:15.590547700-05:00[America/Lima]")
public class AuthorizedSigner {

  private String dni;

  private String fullname;

  private String cargo;

  public AuthorizedSigner dni(String dni) {
    this.dni = dni;
    return this;
  }

  /**
   * Get dni.
   *
   * @return dni.
   */

  @Schema(name = "dni", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dni")
  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public AuthorizedSigner fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  /**
   * Clase generado openApi.
   *
   * Get fullname.
   *
   * @return fullname.
   */

  @Schema(name = "fullname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fullname")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public AuthorizedSigner cargo(String cargo) {
    this.cargo = cargo;
    return this;
  }

  /**
   * Get cargo
   * @return cargo
   */

  @Schema(name = "cargo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cargo")
  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizedSigner authorizedSigner = (AuthorizedSigner) o;
    return Objects.equals(this.dni, authorizedSigner.dni)
        && Objects.equals(this.fullname, authorizedSigner.fullname)
        && Objects.equals(this.cargo, authorizedSigner.cargo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni, fullname, cargo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthorizedSigner {\n");
    sb.append("    dni: ").append(toIndentedString(dni)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    cargo: ").append(toIndentedString(cargo)).append("\n");
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

