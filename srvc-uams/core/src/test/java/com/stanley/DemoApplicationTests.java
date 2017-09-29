package com.stanley;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Assert;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import javax.annotation.Resource;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplicationTests.class)
public class DemoApplicationTests {
//	private MockMvc mockMvc;
//
//	@Resource
//	private WebApplicationContext webApplicationConnect;
//
//	@Before
//	public void setUp() throws JsonProcessingException {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
//	}
//
//	@Test
//	public void testShow() throws Exception {
//		String uri = "/getOne";
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		String content = mvcResult.getResponse().getContentAsString();
//		Assert.assertTrue("错误，正确的返回值为200", status == 200);
//		Assert.assertFalse("错误，正确的返回值为200", status != 200);
//		//Assert.assertTrue("数据一致", expectedResult.equals(content));
//		//Assert.assertFalse("数据不一致", !expectedResult.equals(content));
//	}
//
//	@Test
//	public void testShow2() throws Exception {
//		String uri = "/getOne?idkey=44942";
//		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(jsonPath("$.idKey").value(44942))
//		;
//	}
//
}
