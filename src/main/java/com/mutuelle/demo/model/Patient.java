package com.mutuelle.demo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mutuelle.demo.utils.EUbudeheCategory;


@Entity
@Table(name = "PATIENT")
public class Patient
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = " This field cant be null")
    @Pattern(regexp = "[0-9]+", message = "Invalid characters. Use digits only")
    @Size(min = 15, max = 16, message = "Valid National Identification is 16 Digits")
    private String idnb;

    /** The date of birth. */
    @NotNull
    private String dateOfBirth;

    /** The gender. */
    @NotNull
    private String gender;

    /** The first name. */
    @NotNull
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The category. */
    @Enumerated(EnumType.STRING)
    private EUbudeheCategory category;

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getIdnb()
    {
        return idnb;
    }

    public void setIdnb(final String idnb)
    {
        this.idnb = idnb;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(final String gender)
    {
        this.gender = gender;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public EUbudeheCategory getCategory()
    {
        return category;
    }

    public void setCategory(final EUbudeheCategory category)
    {
        this.category = category;
    }

    /**
     * @return the id
     */

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient [id=");
        builder.append(id);
        builder.append(", idnb=");
        builder.append(idnb);
        builder.append(", dateOfBirth=");
        builder.append(dateOfBirth);
        builder.append(", gender=");
        builder.append(gender);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", category=");
        builder.append(category);
        builder.append("]");
        return builder.toString();
    }

}