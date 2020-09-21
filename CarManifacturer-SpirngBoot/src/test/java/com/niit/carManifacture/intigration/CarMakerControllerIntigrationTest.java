package com.niit.carManifacture.intigration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.niit.carManifacture.test.service.CarManifactureData;
import com.niit.carManifacture.util.Utility;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CarMakerControllerIntigrationTest extends CarManifactureData {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void deleteCarMaker() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/car/carMaker/findCarMaker/3")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String data = result.getResponse().getContentAsString();
		System.out.println(data);
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("Car Maker successfully Deleted", jsonObject.get("messege").getAsString());

	}

	@Test
	public void deleteCarMakerWhenIdNotPresent() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/car/carMaker/findCarMaker/888")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String data = result.getResponse().getContentAsString();
		System.out.println(data);
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(404, result.getResponse().getStatus());
		assertEquals("Data not Found", jsonObject.get("messege").getAsString());

	}

	@Test
	public void addCarMaker() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(getCarMakerDataforAdd());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/car/carMaker/addCarMaker")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("Car Maker Successfully", jsonObject.get("messege").getAsString());
	}

	@Test
	public void addCarMakerAsError() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(getCarMakerData());
		inputJSON=inputJSON.substring(0, inputJSON.length() - 6);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/car/carMaker/addCarMaker")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(400, result.getResponse().getStatus());
		assertEquals("Please Cheack your input Format May be there is an error with your JSON input", jsonObject.get("messege").getAsString());
	}

	@Test
	public void updateCarMaker() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(actualCarMakerData());
		
		RequestBuilder builder = MockMvcRequestBuilders.put("/car/carMaker/updateCarManifacturer/")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals("Record updated Sucessfully", jsonObject.get("messege").getAsString());

	}

	@Test
	public void updatedCarMakerWhenHasError() throws Exception {

		String inputJSON = Utility.objectToJsonConverter(getCarMakerDataWithoutID());
		RequestBuilder builder = MockMvcRequestBuilders.put("/car/carMaker/updateCarManifacturer/")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Unknown Id which you are trying to make changes", jsonObject.get("messege").getAsString());

	}

	@Test
	public void updatedCarMakerWhenHasErrorHasInJsonFormat() throws Exception {

		String inputJSON = Utility.objectToJsonConverter(getCarMakerData2());
		inputJSON = inputJSON.substring(0, inputJSON.length() - 6);

		RequestBuilder builder = MockMvcRequestBuilders.put("/car/carMaker/updateCarManifacturer/")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(400, mvcResult.getResponse().getStatus());
		assertEquals("Please Cheack your input Format May be there is an error with your JSON input",
				jsonObject.get("messege").getAsString());

	}

	@Test
	public void findCarModel() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carMaker/findCarMaker/4")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		
		// String result=mvcResult.getResponse().getContentAsString();
		
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals("4", jsonObject.get("id").getAsString());
		assertEquals("Amandeep", jsonObject.get("name").getAsString());
	}

	@Test
	public void findCarModelAsError() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carMaker/findCarMaker/3000")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		
		// String result=mvcResult.getResponse().getContentAsString();
		
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Data not Found", jsonObject.get("messege").getAsString());

	}

	@Test
	public void viewCarMakers() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carMaker").contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();

		// String data = mvcResult.getResponse().getContentAsString();

		JsonArray jsonArray = Utility.convertStringIntoJsonArray(mvcResult.getResponse().getContentAsString());

		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals("1", jsonArray.get(0).getAsJsonObject().get("id").getAsString());
		assertEquals("4", jsonArray.get(2).getAsJsonObject().get("id").getAsString());

	}
}
