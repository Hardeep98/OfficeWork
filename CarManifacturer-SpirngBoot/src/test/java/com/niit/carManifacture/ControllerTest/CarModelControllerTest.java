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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.niit.carManifacture.Controller.CarsModelController;
import com.niit.carManifacture.ServiceImpl.CarModelServiceImpl;
import com.niit.carManifacture.exception.UnexpectedError;
import com.niit.carManifacture.model.CarModel;
import com.niit.carManifacture.model.ResponseMesg;
import com.niit.carManifacture.test.service.CarManifactureData;

public class CarModelControllerTest extends CarManifactureData {

	@InjectMocks
	CarsModelController carsModelController;

	@Mock
	CarModelServiceImpl carModelServiceImpl;
 

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getList() {
		when(carModelServiceImpl.viewAllModels())
				.thenReturn(Stream.of(getCarModelData1(), getCarModelData2()).collect(Collectors.toList()));
		assertEquals(2, carsModelController.listCars().getBody().size());
	}

	@Test
	public void getListAsFalse() {
		when(carModelServiceImpl.viewAllModels()).thenReturn(null);
		try {
			carsModelController.listCars();
		} catch (Exception e) {
			assertEquals("List is empty", e.getMessage());
		}

	}

	@Test
	public void findCar() {
		when(carModelServiceImpl.findCar(1L)).thenReturn(getCarModelData1());
		ResponseEntity<CarModel> carModel = carsModelController.searchCars(1);
		assertEquals("R8", carModel.getBody().getName());
		assertEquals(1, carModel.getBody().getId());

	}

	@Test
	public void findCarWhenFalse() {
		when(carModelServiceImpl.findCar(1L)).thenReturn(null);
		try {
			carsModelController.searchCars(1);
		} catch (Exception e) {
			assertEquals("ID not find", e.getMessage());
		}
	}

	@Test
	public void deleteCarModel() {
		when(carModelServiceImpl.deleteCar(1L)).thenReturn(true);
		ResponseEntity<ResponseMesg> rMesg = carsModelController.deleteCarModel(1L);
		assertEquals(HttpStatus.OK, rMesg.getStatusCode());
		assertEquals(1,rMesg.getBody().getId());

	}

	@Test
	public void deleteCarModelWhenReturnFalse() {
		when(carModelServiceImpl.deleteCar(1L)).thenReturn(false);
		try {
			carsModelController.deleteCarModel(1L);
		} catch (Exception e) {
			assertEquals("ID not find", e.getMessage());
		}
	}

	@Test
	public void updateCarModel() {

		when(carModelServiceImpl.updateCar(getCarModelData1().getId(), getCarModelData1().getName(),
				getCarModelData1().getYear().toString(), getCarModelData1().getManufacturer().getId()))
						.thenReturn(true);
		ResponseEntity<ResponseMesg> rMesg = carsModelController.updateCar(getCarModelData1());
		assertEquals(HttpStatus.OK, rMesg.getStatusCode());
	}

	@Test
	public void updateCarModelWhenDataIsWrong() {
		when(carModelServiceImpl.updateCar(getCarModelData1().getId(), getCarModelData1().getName(),
				getCarModelData1().getYear().toString(), getCarModelData1().getManufacturer().getId()))
						.thenReturn(false);
		try {
			carsModelController.updateCar(getCarModelData1());
		} catch (Exception e) {
			assertEquals("Unexpected Error May be Error with foreign key constrain", e.getMessage());
		}
	}

	@Test
	public void addCarModel() {
		when(carModelServiceImpl.addCar(getCarModelData1().getName(), getCarModelData1().getYear().toString(),
				getCarModelData1().getManufacturer().getId())).thenReturn(1L);
		ResponseEntity<ResponseMesg> rMesg = carsModelController.addCar(getCarModelData1());
		assertEquals(HttpStatus.OK, rMesg.getStatusCode());

	}

	@Test
	public void AddCarModelWhenHasError() {
		when(carModelServiceImpl.addCar(getCarModelDataWithErrorInForiegnKey().getName(), getCarModelDataWithErrorInForiegnKey().getYear().toString(),
				getCarModelDataWithErrorInForiegnKey().getManufacturer().getId())).thenThrow(new UnexpectedError("Unexpected Error May be error with the Foreign key constrain"));
		try {
			carsModelController.addCar(getCarModelDataWithErrorInForiegnKey());
			
		}
		catch (Exception e) {
			assertEquals("Unexpected Error May be error with the Foreign key constrain", e.getMessage());
		}
		

	}
}
