package com.niit.carManifacture.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.niit.carManifacture.ServiceImpl.CarModelServiceImpl;
import com.niit.carManifacture.daoImpl.CarModelDaoImpli;

@SpringBootTest
public class CarModelServiceTest extends CarManifactureData {

	@InjectMocks
	private CarModelServiceImpl carModelServiceImpl;

	@Mock
	private CarModelDaoImpli repository;

	@Test
	public void viewAllModelsTest() {

		when(repository.viewAllModels())
				.thenReturn(Stream.of(getCarModelData1(), getCarModelData2()).collect(Collectors.toList()));

		assertEquals(2, carModelServiceImpl.viewAllModels().size());

	}

	@Test
	public void addAnewCar() {
		when(repository.addCar(getCarModelData1().getName(), getCarModelData1().getYear().toString(),
				getCarModelData1().getManufacturer().getId())).thenReturn(1L);

		assertEquals(1L, carModelServiceImpl.addCar(getCarModelData1().getName(),
				getCarModelData1().getYear().toString(), getCarModelData1().getManufacturer().getId()));

	}

	@Test
	public void findCar() {
		when(repository.findCar(1L)).thenReturn(getCarModelData1());
		assertEquals(getCarModelData1(), carModelServiceImpl.findCar(1L));

	}

	@Test
	public void deleteCar() {
		when(repository.deleteCar(1L)).thenReturn(true);
		assertTrue(carModelServiceImpl.deleteCar(1L));

	}

	@Test
	public void updateCar() {
		when(repository.updateCar(1L, "BMW", "2000", 1L)).thenReturn(true);
		assertTrue(carModelServiceImpl.updateCar(1L, "BMW", "2000", 1L));

	}
}
