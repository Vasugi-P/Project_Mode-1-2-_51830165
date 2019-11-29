package com.demo.model.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="customer_tab")
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	
				@NotEmpty
	private String name;
	
				@NotEmpty
	private String address;
	
				@NotEmpty
	private String mobile;
	
				@NotNull(message="price cannot Null")
				@Min(value=10,message="min value should be more than 10")
				@Max(value=10000,message="min value should be more than 10000")
	private Double salary;
	
				@NotEmpty
				@Email
	private String email;

				public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

				public String getAddress() {
					return address;
				}

				public void setAddress(String address) {
					this.address = address;
				}

				public String getMobile() {
					return mobile;
				}

				public void setMobile(String mobile) {
					this.mobile = mobile;
				}

				public Double getSalary() {
					return salary;
				}

				public void setSalary(Double salary) {
					this.salary = salary;
				}

				public String getEmail() {
					return email;
				}

				public void setEmail(String email) {
					this.email = email;
				}

				public Customer(int id, String name, String address, String mobile, Double salary, String email) {
					super();
					this.id = id;
					this.name = name;
					this.address = address;
					this.mobile = mobile;
					this.salary = salary;
					this.email = email;
				}

				public Customer() {
					super();
					// TODO Auto-generated constructor stub
				}

				@Override
				public String toString() {
					return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", mobile=" + mobile
							+ ", salary=" + salary + ", Email=" + email + "]";
				}
	
	
				
	
	

}
