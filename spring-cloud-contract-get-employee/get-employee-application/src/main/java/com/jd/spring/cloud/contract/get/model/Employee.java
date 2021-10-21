package com.jd.spring.cloud.contract.get.model;

public class Employee{

	private String id;

	private String firstName;

	private String lastName;

	private String identityCardNo;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id='" + id + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", identityCardNo='" + identityCardNo + '\'' +
				", status=" + status +
				'}';
	}
}