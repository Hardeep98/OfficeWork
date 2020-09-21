package com.niit.carManifacture.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.niit.carManifacture.ServiceImpl.CarModelServiceImpl;
import com.niit.carManifacture.api.CarsModelApi;
import com.niit.carManifacture.exception.DataNotFindEXception;
import com.niit.carManifacture.exception.UnexpectedError;
import com.niit.carManifacture.model.CarModel;
import com.niit.carManifacture.model.ResponseMesg;
import com.niit.carManifacture.utility.Utility;

@RestController
public class CarsModelController implements CarsModelApi {

	@Autowired
	CarModelServiceImpl carModelSevice;

	@Override
	public ResponseEntity<ResponseMesg> addCar(@Valid CarModel addCar) {
		Long id = 0L;
		try {
			id = carModelSevice.addCar(addCar.getName(), addCar.getYear().toString(), addCar.getManufacturer().getId());
			return new ResponseEntity<ResponseMesg>(Utility.getResponse(id, "car Created Successfully", true),
					HttpStatus.OK);
		} catch (Exception e) {
			throw new UnexpectedError("Unexpected Error May be error with the Foreign key constrain");
		}
	}

	@Override
	public ResponseEntity<List<CarModel>> listCars() {

		List<CarModel> carModelList = carModelSevice.viewAllModels();
		if (carModelList != null)
			return new ResponseEntity<List<CarModel>>(carModelList, HttpStatus.OK);
		else
			throw new DataNotFindEXception("List is empty");
	}

	@Override
	public ResponseEntity<CarModel> searchCars(Integer id) {

		CarModel carModel = carModelSevice.findCar(id.longValue());
		System.out.println(carModel);
		if (carModel != null)
			return new ResponseEntity<CarModel>(carModel, HttpStatus.OK);
		else
			throw new DataNotFindEXception("ID not find");
   	}

	@Override
	public ResponseEntity<ResponseMesg> deleteCarModel(Long id) {
		boolean isDeleted = carModelSevice.deleteCar(id);
		if (isDeleted)
			return new ResponseEntity<ResponseMesg>(Utility.getResponse(id, "Deletion Done", true), HttpStatus.OK);
		else
			throw new DataNotFindEXception("ID not find");

	}

	@Override
	public ResponseEntity<ResponseMesg> updateCar(@Valid CarModel carModel) {
		boolean isUpdated = carModelSevice.updateCar(carModel.getId(), carModel.getName(),
				carModel.getYear().toString(), carModel.getManufacturer().getId());
		if (isUpdated)
			return new ResponseEntity<ResponseMesg>(Utility.getResponse(carModel.getId(), "Car Updated ", true),
					HttpStatus.OK);
		else
			throw new UnexpectedError("Unexpected Error May be Error with foreign key constrain");

	}

}
