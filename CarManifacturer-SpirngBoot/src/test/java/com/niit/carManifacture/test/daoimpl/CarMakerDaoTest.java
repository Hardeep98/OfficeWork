package com.niit.carManifacture.test.daoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.niit.carManifacture.daoImpl.CarMakerDaoImpl;
import com.niit.carManifacture.repository.CarMakerRepository;
import com.niit.carManifacture.test.service.CarManifactureData;

@SpringBootTest
public class CarMakerDaoTest extends CarManifactureData {

	@InjectMocks
	CarMakerDaoImpl carMakerDaoImpl;

	@Mock
	CarMakerRepository carMakerRepository;

	@Mock
	Jdbi jdbi;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void addCarMaker() {

		when(jdbi.onDemand(CarMakerRepository.class)).thenReturn(carMakerRepository);

		when(carMakerRepository.insertCarMaker(getCarMakerData())).thenReturn(1L);
		Long id = carMakerDaoImpl.insertCarMaker(getCarMakerData());
		assertEquals(1L, id);
	}

	@Test
	public void viewCarMaker() {
		when(jdbi.onDemand(CarMakerRepository.class)).thenReturn(carMakerRepository);
		when(carMakerRepository.viewAllCarMakers())
				.thenReturn(Stream.of(getCarMakerData(), getCarMakerData2()).collect(Collectors.toList()));
		int listSize = carMakerDaoImpl.viewAllMakers().size();
		assertEquals(2, listSize);

	}
	@Test
	public void deleteCarMaker() {
		when(jdbi.onDemand(CarMakerRepository.class)).thenReturn(carMakerRepository);
		when(carMakerRepository.deleteCar(1L)).thenReturn(true);
		assertTrue(carMakerDaoImpl.deleteCar(1L));
		
	}
	
	@Test
	public void updateCarMaker() {
		when(jdbi.onDemand(CarMakerRepository.class)).thenReturn(carMakerRepository);
		when(carMakerRepository.updateCarMaker(getCarMakerData())).thenReturn(true);
		assertTrue(carMakerDaoImpl.updateCarMaker(getCarMakerData()));
	}
	
	@Test
	public void searchCarMaker() {
		when(jdbi.onDemand(CarMakerRepository.class)).thenReturn(carMakerRepository);
		when(carMakerRepository.searchCarMaker(1L)).thenReturn(getCarMakerData());
		assertEquals(getCarMakerData(), carMakerDaoImpl.searchCarMaker(1L));
		
	}
}
