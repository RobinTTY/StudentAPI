package com.api.org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student   {

  private @Valid UUID uuid;
  private @Valid Integer id;
  private @Valid String name;
  private @Valid Integer age;
  private @Valid Integer enrolmentNumber;
  private @Valid String fieldOfStudy;

  public Student(){}

  @JsonCreator
  public Student(@JsonProperty("uuid") UUID uuid, @JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("age") Integer age, @JsonProperty("enrolmentNumber") Integer enrolmentNumber, @JsonProperty("fieldOfStudy") String fieldOfStudy){
    this.uuid = uuid;
    this.id = id;
    this.name = name;
    this.age = age;
    this.enrolmentNumber = enrolmentNumber;
    this.fieldOfStudy = fieldOfStudy;
  }

  /**
   **/
  public Student uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }


  @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", value = "")
  @JsonProperty("uuid")
  public UUID getUuid() {
    return uuid;
  }
  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  /**
   **/
  public Student id(Integer id) {
    this.id = id;
    return this;
  }


  @ApiModelProperty(example = "1", value = "")
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   **/
  public Student name(String name) {
    this.name = name;
    return this;
  }


  @ApiModelProperty(example = "Lula Haverland", required = true, value = "")
  @JsonProperty("name")
  @NotNull
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  public Student age(Integer age) {
    this.age = age;
    return this;
  }


  @ApiModelProperty(example = "21", required = true, value = "")
  @JsonProperty("age")
  @NotNull
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   **/
  public Student enrolmentNumber(Integer enrolmentNumber) {
    this.enrolmentNumber = enrolmentNumber;
    return this;
  }


  @ApiModelProperty(example = "732597", required = true, value = "")
  @JsonProperty("enrolmentNumber")
  @NotNull
  public Integer getEnrolmentNumber() {
    return enrolmentNumber;
  }
  public void setEnrolmentNumber(Integer enrolmentNumber) {
    this.enrolmentNumber = enrolmentNumber;
  }

  /**
   **/
  public Student fieldOfStudy(String fieldOfStudy) {
    this.fieldOfStudy = fieldOfStudy;
    return this;
  }


  @ApiModelProperty(example = "Software Engineering", required = true, value = "")
  @JsonProperty("fieldOfStudy")
  @NotNull
  public String getFieldOfStudy() {
    return fieldOfStudy;
  }
  public void setFieldOfStudy(String fieldOfStudy) {
    this.fieldOfStudy = fieldOfStudy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(uuid, student.uuid) &&
            Objects.equals(id, student.id) &&
            Objects.equals(name, student.name) &&
            Objects.equals(age, student.age) &&
            Objects.equals(enrolmentNumber, student.enrolmentNumber) &&
            Objects.equals(fieldOfStudy, student.fieldOfStudy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, id, name, age, enrolmentNumber, fieldOfStudy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");

    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    enrolmentNumber: ").append(toIndentedString(enrolmentNumber)).append("\n");
    sb.append("    fieldOfStudy: ").append(toIndentedString(fieldOfStudy)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

