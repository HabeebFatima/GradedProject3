package com.gl.customerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.customerManagement.entity.Customer;
import com.gl.customerManagement.service.CustomerService;



@Controller
@RequestMapping("/home")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@RequestMapping("/list-Customers")
	public String listStudents(Model theModel) {

		System.out.println("request recieved");

		// get student details from db
		List<Customer> theCustomers = customerService.findAll();
		
		// add to the spring model
		theModel.addAttribute("Customers", theCustomers);
		System.out.println(theCustomers);
		return "list-Customers";
	
	}
	@RequestMapping("/showFormToAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("NewCustomer", theCustomer);

		return "Customer-form";
	}

	 @RequestMapping("/showFormToUpdate")
	 public String showUpdateForm(@RequestParam("customerId") int theId,Model theModel)
	 {
		 //get the Student info
		 Customer theCustomer = customerService.findById(theId);
		 
		 //Set the Student Details to pre-populate in the form
		 theModel.addAttribute("Customer",theCustomer);
		 
		 //send over to the update form
		 return "Customer-form";
		 
		 
	 }
	 @PostMapping("/save")
	 public String saveCustomer(@RequestParam("id")Integer id,
			 				   @RequestParam("firstName")String fname,
			 				   @RequestParam("lastName")String lname,
			 				   @RequestParam("email")String email) {
		 
		 Customer customer ;//=new Student();
		 if(id!=null && id!=0) {
			 customer=customerService.findById(id);
			 customer.setFirstName(fname);
			 customer.setLastName(lname);
			 customer.setEmail(email);
			 
		 }else {
			 customer =new Customer(fname,lname,email);
			 System.out.println(customer);
			 

		 }
		 customerService.save(customer);
		 return "redirect:/home/list-Customers";
		 
	 
	 }
		@RequestMapping("/delete")
		public String deleteCustomer(@RequestParam("customerId") int theId) {

			// delete the Book
			customerService.deleteById(theId);

			// redirect to /Books/list
			return "redirect:/home/list-Customers";

		}

	 
	 
	
}
