package com.example.employee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate
@Table(name="employee")

public class EmployeeEntity implements Serializable {

    /*
	Generates the default id
	
	*/
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	
    @Column
    private String id;
	
	/* 
	 * database columns
	 */
	
	@Column(name = "phone_number")
    private String phoneNumber;
	
	@Column(name = "first_name")
    private String firstName;

	@Column(name = "last_name")
    private String lastName;
	
    @Column(name = "email_id")
    private String emailId;

    /*
     * getters and setters
     */

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
    
	
    
	
	  
}
