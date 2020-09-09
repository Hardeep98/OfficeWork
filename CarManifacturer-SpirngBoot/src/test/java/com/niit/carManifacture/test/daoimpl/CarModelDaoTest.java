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

import com.niit.carManifacture.daoImpl.CarModelDaoImpli;
import com.niit.carManifacture.repository.CarsModelRepository;
import com.niit.carManifacture.test.service.CarManifactureData;

public class CarModelDaoTest extends CarManifactureData {

	@InjectMocks
	CarModelDaoImpli carModelDaoImpli;

	@Mock
	CarsModelRepository carsModelRepository;

	@Mock
	Jdbi jdbi;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void addCarModels() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.insert(getCarModelData1().getName(), getCarModelData1().getYear().toString(),
				getCarModelData1().getManufacturer().getId())).thenReturn(1L);
		assertEquals(1L, carModelDaoImpli.addCar(getCarModelData1().getName(), getCarModelData1().getYear().toString(),
				getCarModelData1().getManufacturer().getId()));

	}

	@Test
	public void deleteCarModel() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.deleteCarModel(1L)).thenReturn(true);
		assertTrue(carModelDaoImpli.deleteCar(1L));

	}

	@Test
	public void updateCarModel() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.updateCarModel(getCarModelData2().getId(), getCarModelData2().getName(),
				getCarModelData2().getYear().toString(), getCarModelData2().getManufacturer().getId())).thenReturn(true);
		assertTrue(carModelDaoImpli.updateCar(getCarModelData2().getId(), getCarModelData2().getName(),
				getCarModelData2().getYear().toString(), getCarModelData2().getManufacturer().getId()));
	}

	@Test
	public void showCarModels() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.viewAllModels())
				.thenReturn(Stream.of(getCarModelData1(), getCarModelData2()).collect(Collectors.toList()));
		int listSize = carModelDaoImpli.viewAllModels().size();
		assertEquals(2, listSize);
	}

	@Test
	public void findCarModel() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.viewCarModel(2L)).thenReturn(getCarModelData2());
		assertEquals(getCarModelData2(), carModelDaoImpli.findCar(2L));

	}
	@Test
	public void parentDeletion() {
		when(jdbi.onDemand(CarsModelRepository.class)).thenReturn(carsModelRepository);
		when(carsModelRepository.deleteParentCarMakerModel(1L)).thenReturn(true);
		assertTrue(carModelDaoImpli.parentCarMakerDataDeletion(1L));
	}

}
