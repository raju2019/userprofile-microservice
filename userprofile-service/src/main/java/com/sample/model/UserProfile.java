package com.sample.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * This class define the necessary fields of an User Profile
 *
 */

@ApiModel(value = "UserProfile Model")
@Entity
@Data
@Accessors(chain = true)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Identification of the User")
    public Long userId;

    @ApiModelProperty(value = "UserProfile alias")
    public String alias;

    @ApiModelProperty(value = "First Name")
    @Size(min = 2, max = 100)
    private String firstName;

    @ApiModelProperty(value = "Last Name")
    @Size(min = 2, max = 100)
    private String lastName;

    @ApiModelProperty(value = "Date of Birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ApiModelProperty(value = "Home Address")
    @Size(min = 2, max = 50)
    private String homeAddress;

    @ApiModelProperty(value = "Office Address")
    @Size(min = 2, max = 50)
    private String officeAddress;

    @ApiModelProperty(value="Email of User")
    @javax.validation.constraints.Email
    @Size(max = 50)
    private String email;

    @ApiModelProperty(position = 4, required = true)
    @Size(max = 10)
    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String postcode;

    @ApiModelProperty(position = 7, required = true)
    @Size(min = 4, max = 60)
    private String country;


}
