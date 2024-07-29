package com.truetrack.sharing.service;


import com.truetrack.sharing.dtos.BusinessDataShareDetailDTO;
import com.truetrack.sharing.dtos.BusinessDataShareDetailInternal;
import com.truetrack.sharing.entity.BusinessDataShareDetail;

public interface BusinessDataShareDetailService {

	BusinessDataShareDetailDTO getDetails(Integer dataShareId)	;
	
	BusinessDataShareDetailInternal getDetail(Integer dataShareDetailId);
	
	BusinessDataShareDetailInternal addDetail(BusinessDataShareDetail detail);
	
}
