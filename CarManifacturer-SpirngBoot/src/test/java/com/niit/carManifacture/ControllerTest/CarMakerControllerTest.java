package com.niit.carManifacture.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.niit.carManifacture.Controller.CarsMakerController;
import com.niit.carManifacture.ServiceImpl.CarMakerServiceImpl;
import com.niit.carManifacture.model.CarMaker;
import com.niit.carManifacture.model.ResponseMesg;
import com.niit.carManifacture.test.service.CarManifactureData;

@SpringBootTest
public class CarMakerControllerTest extends CarManifactureData {

	@InjectMocks
	CarsMakerController carsMakerController;

	@Mock
	CarMakerServiceImpl carMakerServiceImpl;

	
	@Mock
	CarMaker carMaker;
	
	@Mock
	ResponseEntity<ResponseMesg> rMesg;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void listCarMaker() {
		when(carMakerServiceImpl.viewAllCarMakers())
				.thenReturn(Stream.of(getCarMakerData(), getCarMakerData2()).collect(Collectors.toList()));
		assertEquals(2L, carsMakerController.listCarMaker().getBody().size());
	}

	@Test
	public void listCarMakerAsFalseCase() {
		when(carMakerServiceImpl.viewAllCarMakers()).thenReturn(null);
		try {
			carsMakerController.listCarMaker();

		} catch (Exception e) {

			assertEquals("There is no Data Present in the list", e.getMessage());
		}

	}

	@Test
	public void searchCarMaker() {
		when(carMakerServiceImpl.getCarMaker(2L)).thenReturn(getCarMakerData2());
		ResponseEntity<CarMaker> carmaker = carsMakerController.searchCarMaker(2);
		assertEquals("Inder Nagpal", carmaker.getBody().getName());
		assertEquals(2L, carmaker.getBody().getId());

	}

	@Test
	public void searchCarMakerWhenResultIsNotPresent() {
		when(carMakerServiceImpl.getCarMaker(1L)).thenReturn(null);
		try {
			carsMakerController.searchCarMaker(1);
		} catch (Exception e) {  
			assertEquals("Data not Found", e.getMessage());
		}

	}

	@Test
	public void deleteCarMaker() {
		when(carMakerServiceImpl.deleteCarMaker(1L)).thenReturn(true);
		rMesg = carsMakerController.deleteMaker(1L);
		System.out.println(rMesg);
		assertEquals(HttpStatus.OK, rMesg.getStatusCode());

	}

	@Test
	public void deleteCarMakerAsfalseCase() {
		when(carMakerServiceImpl.deleteCarMaker(1L)).thenReturn(false);
		try {
			carsMakerController.deleteMaker(1L);
		} catch (Exception e) {
			assertEquals("Data not Found", e.getMessage());
		}
	}

	@Test
	public void updateCarMaker() {

		when(carMakerServiceImpl.updateCarMaker(getCarMakerData())).thenReturn(true);
		ResponseEntity<ResponseMesg> rmesg = carsMakerController.updateCarMaker(getCarMakerData());
		
		assertEquals(HttpStatus.OK, rmesg.getStatusCode());

		assertEquals(500, rmesg.getBody().getId());
	}

	@Test
	public void updateCarFalseCase() {
		when(carMakerServiceImpl.updateCarMaker(getCarMakerData())).thenReturn(false);
		try {
			carsMakerController.updateCarMaker(getCarMakerData());
		} catch (Exception e) {
			assertEquals("Unknown Id which you are trying to make changes", e.getMessage());
		}

	}

	@Test
	public void addCarMaker() {

		when(carMakerServiceImpl.insertCarMaker(getCarMakerData())).thenReturn(1L);
		ResponseEntity<ResponseMesg> rmesg = carsMakerController.addCarMaker(getCarMakerData());
		assertEquals(HttpStatus.OK, rmesg.getStatusCode());
		assertEquals("Car Maker Successfully",rmesg.getBody().getMessege() );

	}

	@Test
	public void addCarWhenIDNotPresent() {
		when(carMakerServiceImpl.insertCarMaker(getCarMakerDataWithoutID())).thenReturn(1L);
		try {
			carsMakerController.addCarMaker(getCarMakerDataWithoutID());

		} catch (Exception e) {
			assertEquals("Please Provide ID", e.getMessage());
		}

	}

	@Test
	public void addCarWhenDataNotPresent() {
		when(carMakerServiceImpl.insertCarMaker(getCarMakerData())).thenReturn(null);
		try {
			carsMakerController.addCarMaker(new CarMaker());
					
		} catch (Exception e) {
			
			assertEquals("SomeUnexpected Error occuer", e.getMessage());
		}

	}

}
