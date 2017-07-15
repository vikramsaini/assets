package com.hclfintech.management.assests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hclfintech.management.assets.AssetsApplication;
import com.hclfintech.management.assets.controller.ShopsController;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetsApplication.class)
@ContextConfiguration(classes = ShopsController.class)
@WebAppConfiguration
@EnableWebMvc
@EnableAutoConfiguration
public class IntegrationTest extends ParentTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testApplicationStatus() throws Exception {
		mockMvc
		.perform(post("/shops/add").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buildShop("abc", "abc","abc")))).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
		 
	}
}
