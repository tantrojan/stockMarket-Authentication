package org.wells.tests;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.wells.controller.AuthenticationController;
import org.wells.entity.AddUserRequest;
import org.wells.service.AuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthenticationController.class)
public class AuthenticationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthenticationService authenticationService;
	
	String exampleUser = "{\r\n" + 
			"    \\\"username\\\": \\\"aditya\\\",\r\n" + 
			"    \\\"firstName\\\": \\\"Aditya\\\",\r\n" + 
			"    \\\"password\\\": \\\"1234\\\",\r\n" + 
			"    \\\"lastName\\\": \\\"\\\",\r\n" + 
			"    \\\"email\\\": \\\"aditya.99@gmail.com\\\",\r\n" + 
			"    \\\"mobile\\\": \\\"7923632604\\\"\r\n" + 
			"}";
	
	@Test
	public void testCreateUser() throws Exception{
		AddUserRequest mockUser = new AddUserRequest();
		mockUser.setUsername("uname1");
		mockUser.setFirstName("Fname1");
		mockUser.setLastName("Lname");
		mockUser.setConfirmed(true);
		mockUser.setPassword("temp");;
		mockUser.setEmail("temp@gmail.com");
		mockUser.setMobile("123456789");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/register")
				.accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		}
}