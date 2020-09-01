package com.niit.carManifacture.ServiceImpl;

import org.springframework.stereotype.Service;

import com.niit.carManifacture.model.ResponseMesg;
@Service
public class ResponseService implements com.niit.carManifacture.Service.ResponseService {

	
	@Override
	public ResponseMesg getResponse(Long id, String mesg, boolean status) {
		ResponseMesg rmesg=new ResponseMesg();
		rmesg.setId(id.intValue());
		rmesg.setMessege(mesg);
		rmesg.setStatus(status);
		return rmesg;
	}
	
}
