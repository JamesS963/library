package com.springproject.library.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.springproject.library.form.UserForm;
import com.springproject.library.model.Authority;
import com.springproject.library.model.User;
import com.springproject.library.security.Role;
import com.springproject.library.security.SecurityConfig;
import com.springproject.library.service.AuthorityServiceImpl;
import com.springproject.library.service.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = { AuthorityServiceImpl.class, UserController.class, UserDetailsServiceImpl.class,
		SecurityConfig.class })
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AuthorityServiceImpl authorityService;
	@MockBean
	private UserDetailsServiceImpl userService;
	List<Authority> authorities;

	@Before
	public void setup() {
		when(userService.loadUserByUsername("testUser")).thenReturn(new User("TestUser", "testPassword"));
		authorities = new ArrayList<>();
		authorities.add(new Authority(1L, Role.ROLE_USER));
		when(authorityService.getAuthorities()).thenReturn(authorities);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateUserPageGet() throws Exception {
		mockMvc.perform(get("/user/create")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testCreateUserPost() throws Exception {
		
		when(userService.loadUserByUsername("name")).thenReturn(null);
		UserForm userForm = new UserForm();
		userForm.setUsername("name");
		userForm.setPassword("password");

		userForm.setAuthorities(authorities);
		userForm.getAuthorities().forEach(authority->{authority.setChecked(true);});
		when(userService.createUser(userForm.toUser())).thenReturn(userForm.toUser());
		mockMvc.perform(post("/user/create").flashAttr("user", userForm)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/books"));
	}
}
