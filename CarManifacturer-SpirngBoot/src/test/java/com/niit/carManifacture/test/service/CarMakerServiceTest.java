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

import com.niit.carManifacture.ServiceImpl.CarMakerServiceImpl;
import com.niit.carManifacture.daoImpl.CarMakerDaoImpl;
import com.niit.carManifacture.daoImpl.CarModelDaoImpli;

@SpringBootTest
public class CarMakerServiceTest extends CarManifactureData {

	@InjectMocks
	CarMakerServiceImpl carMakerServiceImpl;

	@Mock
	CarMakerDaoImpl carMakerdao;
	
	@Mock
	CarModelDaoImpli carModelDao;

	@Test
	public void insertCarMaker() {
		when(carMakerdao.insertCarMaker(getCarMakerData())).thenReturn(1L);
		assertEquals(1L, carMakerServiceImpl.insertCarMaker(getCarMakerData()));
	}

	@Test
	public void viewCarMaker() {
		when(carMakerdao.viewAllMakers())
				.thenReturn(Stream.of(getCarMakerData(), getCarMakerData2()).collect(Collectors.toList()));
		assertEquals(2, carMakerServiceImpl.viewAllCarMakers().size());
	}

	@Test
	public void findMaker() {
		when(carMakerdao.searchCarMaker(1L)).thenReturn(getCarMakerData());
		assertEquals(getCarMakerData(), carMakerServiceImpl.getCarMaker(1L));
	}

	@Test
	public void deleteCarMaker() {
		when(carModelDao.parentCarMakerDataDeletion(1L)).thenReturn(true);
		when( carMakerdao.deleteCar(1L)).thenReturn(true);
		assertTrue(carMakerServiceImpl.deleteCarMaker(1L));
	}
	
	@Test
	public void deleteCarMakerValueNotPresent() {
		when(carModelDao.parentCarMakerDataDeletion(1L)).thenReturn(false);
		when( carMakerdao.deleteCar(1L)).thenReturn(true);
		assertTrue(carMakerServiceImpl.deleteCarMaker(1L));
		
	}

	@Test
	public void UpdateCarMaker() {
		when(carMakerdao.updateCarMaker(getCarMakerData2())).thenReturn(true);
		assertTrue(carMakerServiceImpl.updateCarMaker(getCarMakerData2()));
	}

}
