package com.egen.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.User;
import com.egen.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setEmail("dummy@dummy.com");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setId(UUID.randomUUID().toString());
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testFindUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user.getId()))
				.andExpect(MockMvcResultMatchers.status().is(200));
		Mockito.verify(userService).findUserById(user.getId());
	}

	@Test
	public void testFindUserByIdNotFound() throws Exception {
		Mockito.when(userService.findUserById("testid")).thenThrow(
				new UserNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/users/testid")).andExpect(
				MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void testFindUserByEmail() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/email/" + user.getEmail()
						+ "/")).andExpect(
				MockMvcResultMatchers.status().is(200));
		Mockito.verify(userService).findUserByEmail(user.getEmail());
	}

	@Test
	public void testFindUserByEmailNotFound() throws Exception {
		Mockito.when(userService.findUserByEmail("test@test.com")).thenThrow(
				new UserNotFoundException());
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/email/test@test.com/"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void testCreateUser() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/users")
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(requestBody)).andExpect(
				MockMvcResultMatchers.status().is(200));

		Mockito.verify(userService).createUser(Mockito.any(User.class));
	}
	
	@Test
	public void testCreateUserException() throws Exception {
		Mockito.when(userService.createUser(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException());
		
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
											  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
											  .content(requestBody))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Configuration
	public static class TestConfig {

	}

}
