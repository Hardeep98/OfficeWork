package com.niit.carManifacture.Service;

import java.util.List;

import com.niit.carManifacture.model.CarMaker;

public interface CarMakerService {
	List<CarMaker> viewAllCarMakers();

	Long insertCarMaker(CarMaker carmaker);

	CarMaker getCarMaker(Long id);

	boolean updateCarMaker(CarMaker carMaker);

	boolean deleteCarMaker(Long id);

	
}
