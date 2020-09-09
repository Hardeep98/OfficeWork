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

import com.google.gson.JsonObject;
import com.niit.carManifacture.test.service.CarManifactureData;
import com.niit.carManifacture.util.Utility;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CarModelControllerIntegrationTest extends CarManifactureData {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void deleteCarModel() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/car/carModel/deleteCar/20")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String data = result.getResponse().getContentAsString();
		System.out.println(data);
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("Deletion Done", jsonObject.get("messege").getAsString());

	}

	@Test
	public void deleteCarModelwhenDataNotPresent() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/car/carModel/deleteCar/20")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(404, result.getResponse().getStatus());
		assertEquals("ID not find", jsonObject.get("messege").getAsString());

	}

	@Test
	public void addCarModel() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(getCarModelData1());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/car/carModel/addCar")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());
		assertEquals(201, result.getResponse().getStatus());
		assertEquals("car Created Successfully", jsonObject.get("messege").getAsString());
	}

	@Test
	public void addCarModelWhenHasEmptyBodyConstraint() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(getCarModelDataWithErrorInForiegnKey());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/car/carModel/addCar")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JsonObject jsonObject = Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());

		assertEquals(400, result.getResponse().getStatus());
		assertEquals("Unexpected Error May be error with the Foreign key constrain",
				jsonObject.get("messege").getAsString());

		// JsonObject jsonObject =
		// Utility.convertStringIntoJsonObject(result.getResponse().getContentAsString());

	}

	@Test
	public void updateCarModel() throws Exception {
		String inputJSON = Utility.objectToJsonConverter(actualDataUpdate());
		RequestBuilder builder = MockMvcRequestBuilders.put("/car/carModel/updateCar/")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals("Car Updated", jsonObject.get("messege").getAsString());

	}

	@Test
	public void updatedCarModelWenHasError() throws Exception {

		String inputJSON = Utility.objectToJsonConverter(getCarModelDataWithErrorInForiegnKey());
		RequestBuilder builder = MockMvcRequestBuilders.put("/car/carModel/updateCar/")
				.contentType(MediaType.APPLICATION_JSON).content(inputJSON);
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		JsonObject jsonObject = Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(400, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected Error May be Error with foreign key constrain",
				jsonObject.get("messege").getAsString());

	}

	@Test
	public void findCarModel() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carModel/findCar/24")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult=mockMvc.perform(builder).andReturn();
		//String result=mvcResult.getResponse().getContentAsString();
		JsonObject jsonObject=Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(200,mvcResult.getResponse().getStatus());
		assertEquals("24",jsonObject.get("id").getAsString() );
		assertEquals("c300", jsonObject.get("name").getAsString());
	}
	@Test
	public void findCarModelAsError() throws Exception{
		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carModel/findCar/29")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult=mockMvc.perform(builder).andReturn();
		//String result=mvcResult.getResponse().getContentAsString();
		JsonObject jsonObject=Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
		assertEquals(404,mvcResult.getResponse().getStatus());
		assertEquals("ID not find",jsonObject.get("messege").getAsString() );
		
		
	}
	
//	@Test
//	public void viewCarModel() throws Exception {
//		RequestBuilder builder = MockMvcRequestBuilders.get("/car/carModel/viewCars/")
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult mvcResult=mockMvc.perform(builder).andReturn();
//		
//		String data=mvcResult.getResponse().getContentAsString();
//		System.out.println(data);
//		
//		JsonObject jsonObject=Utility.convertStringIntoJsonObject(mvcResult.getResponse().getContentAsString());
//		
//		
////		assertEquals(200,mvcResult.getResponse().getStatus());
////		assertEquals("Benzc200",c.deepCopy().get("name").getAsString() );
//		
//	}
}
