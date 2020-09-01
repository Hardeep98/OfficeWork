package com.niit.carManifacture.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.niit.carManifacture.Service.CarMakerService;
import com.niit.carManifacture.Service.ResponseService;
import com.niit.carManifacture.api.CarMakerApi;
import com.niit.carManifacture.exception.DataNotFindEXception;
import com.niit.carManifacture.exception.UnexpectedError;
import com.niit.carManifacture.model.CarMaker;
import com.niit.carManifacture.model.ResponseMesg;

@RestController
public class CarsMakerController implements CarMakerApi {

	String mesg = null;
	boolean status;
	Long id = 0L;

	@Autowired
	CarMakerService carMakerService;

	@Autowired
	ResponseService resService;

	@Override
	public ResponseEntity<List<CarMaker>> listCarMaker() {
		List<CarMaker> carMakerList = carMakerService.viewAllCarMakers();
		if (carMakerList != null)
			return new ResponseEntity<List<CarMaker>>(carMakerList, HttpStatus.OK);
		else
			throw new DataNotFindEXception("There is no Data Present in the list");
	}

	@Override
	public ResponseEntity<ResponseMesg> addCarMaker(@Valid CarMaker carMaker) {

		try {
			if (carMaker.getId() != null)
				return new ResponseEntity<ResponseMesg>(resService.getResponse(carMakerService.insertCarMaker(carMaker),
						"Car Maker Successfully", true), HttpStatus.OK);
			else
				throw new DataNotFindEXception("Please Provide ID");

		} catch (Exception e) {
			throw new UnexpectedError(e.getMessage());
		}

	}

	@Override
	public ResponseEntity<CarMaker> searchCarMaker(Integer id) {

		CarMaker carMaker = carMakerService.getCarMaker(id.longValue());
		if (carMaker != null)
			return new ResponseEntity<CarMaker>(carMaker, HttpStatus.OK);
		else
			throw new DataNotFindEXception("Data not Found");
	}

	@Override
	public ResponseEntity<ResponseMesg> deleteMaker(Long id) {

		if (carMakerService.deleteCarMaker(id))
			return new ResponseEntity<ResponseMesg>(resService.getResponse(id, "Car Maker successfully Deleted", true),
					HttpStatus.OK);
		else
			throw new DataNotFindEXception("Data not Found");
	}

	@Override
	public ResponseEntity<ResponseMesg> updateCarMaker(@Valid CarMaker carMaker) {
		if (carMakerService.updateCarMaker(carMaker))
			return new ResponseEntity<ResponseMesg>(
					resService.getResponse(carMaker.getId(), "Record updated Sucessfully", true), HttpStatus.OK);
		else
			throw new DataNotFindEXception("Unknown Id which you are trying to make changes");

	}

}
