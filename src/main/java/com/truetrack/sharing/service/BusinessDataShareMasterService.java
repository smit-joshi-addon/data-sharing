package com.truetrack.sharing.service;

import java.util.List;

import com.truetrack.sharing.entity.BusinessDataShareMaster;

public interface BusinessDataShareMasterService {

	List<BusinessDataShareMaster> getMasterRecords();
	
	BusinessDataShareMaster addMasterRecord(BusinessDataShareMaster master);
	
	BusinessDataShareMaster updateMasterRecord(BusinessDataShareMaster master,Integer dataShaeId);
	
	BusinessDataShareMaster getMasterRecord(Integer dataShareId);
	
}
