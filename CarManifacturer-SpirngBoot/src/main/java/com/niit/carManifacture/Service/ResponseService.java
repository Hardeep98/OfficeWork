package com.niit.carManifacture.Service;

import com.niit.carManifacture.model.ResponseMesg;

public interface ResponseService {	
	ResponseMesg getResponse(Long id ,String mesg,boolean status);
}
