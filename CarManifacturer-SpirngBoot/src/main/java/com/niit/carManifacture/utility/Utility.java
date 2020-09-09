package com.niit.carManifacture.utility;

import com.niit.carManifacture.model.ResponseMesg;

public class Utility {

	public static ResponseMesg getResponse(Long id, String mesg, boolean status) {
		ResponseMesg rmesg=new ResponseMesg();
		rmesg.setId(id.intValue());
		rmesg.setMessege(mesg);
		rmesg.setStatus(status);
		return rmesg;
	}
	
}
