package com.niit.carManifacture.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.niit.carManifacture.Service.CarModelService;
import com.niit.carManifacture.Service.ResponseService;
import com.niit.carManifacture.api.CarsModelApi;
import com.niit.carManifacture.exception.DataNotFindEXception;
import com.niit.carManifacture.exception.UnexpectedError;
import com.niit.carManifacture.model.CarModel;
import com.niit.carManifacture.model.ResponseMesg;

@RestController
public class CarsModelController implements CarsModelApi {

	String mesg = null;
	boolean status;
	Long id = 0L;

	@Autowired
	CarModelService carModelSevice;

	@Autowired
	ResponseService resService;

	@Override
	public ResponseEntity<ResponseMesg> addCar(@Valid CarModel addCar) {
		try {
			id = carModelSevice.addCar(addCar.getName(), addCar.getYear().toString(), addCar.getManufacturer().getId());
			return new ResponseEntity<ResponseMesg>(resService.getResponse(id, "car Created Successfully", true),
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
		if (carModel != null)
			return new ResponseEntity<CarModel>(carModel, HttpStatus.OK);
		else
			throw new DataNotFindEXception("ID not find");

	}

	@Override
	public ResponseEntity<ResponseMesg> deleteCarModel(Long id) {
		if (carModelSevice.deleteCar(id))
			return new ResponseEntity<ResponseMesg>(resService.getResponse(id, "Deletion Done", true), HttpStatus.OK);
		else
			throw new DataNotFindEXception("ID not find");

	}

	@Override
	public ResponseEntity<ResponseMesg> updateCar(@Valid CarModel carModel) {
		if(carModelSevice.updateCar(carModel.getId(), carModel.getName(), carModel.getYear().toString(),
					carModel.getManufacturer().getId()))
			return new ResponseEntity<ResponseMesg>(resService.getResponse(carModel.getId(), "Car Updated ", true),HttpStatus.OK);
		else
			throw new UnexpectedError("Unexpected Error May be Error with foreign key constrain");
		
	}


}
