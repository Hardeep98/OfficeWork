package com.niit.carManifacture.ServiceImpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.carManifacture.Service.CarModelService;
import com.niit.carManifacture.dao.CarModelDao;
import com.niit.carManifacture.model.CarModel;

@Service
public class CarModelServiceImpl implements CarModelService {

	@Autowired
	CarModelDao carModelDao;

	@Override
	public List<CarModel> viewAllModels() {
		return carModelDao.viewAllModels();

	}

	@Override
	public boolean deleteCar(Long id) {
		return carModelDao.deleteCar(id);

	}

	@Override
	public CarModel findCar(Long id) {
		return carModelDao.findCar(id);

	}

	@Override
	public Long addCar(String modelName, String yearOfManifacturer, Long carMakerId) {

		return carModelDao.addCar(modelName, yearOfManifacturer, carMakerId);
	}

	@Override
	public boolean updateCar(Long carId, String modelName, String yearOfManifacturer, Long carMakerId) {
		return carModelDao.updateCar(carId, modelName, yearOfManifacturer, carMakerId);

	}

}
