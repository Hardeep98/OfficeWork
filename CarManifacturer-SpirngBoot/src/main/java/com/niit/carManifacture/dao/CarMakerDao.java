package com.niit.carManifacture.dao;

import java.util.Iterator;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;

import com.niit.carManifacture.model.CarMaker;

public interface CarMakerDao {
	List<CarMaker> viewAllMakers();

	Long insertCarMaker(CarMaker carMaker);

	CarMaker searchCarMaker(Long id);

	boolean updateCarMaker(CarMaker carMaker);

	boolean deleteCar(Long id);

	List<Long> insertBulkCarMaker( List<CarMaker> carMaker);

}
