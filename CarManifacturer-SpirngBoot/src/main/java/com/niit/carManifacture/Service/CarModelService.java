package com.niit.carManifacture.Service;

import java.util.List;

import com.niit.carManifacture.model.CarModel;

public interface CarModelService {
	List<CarModel> viewAllModels();
	Long addCar(String modelName,String yearOfManifacturer, Long carMakerId );
	boolean deleteCar(Long id);
	CarModel findCar(Long id);
	boolean updateCar( Long carId , String modelName,String yearOfManifacturer,Long carMakerId);
}
