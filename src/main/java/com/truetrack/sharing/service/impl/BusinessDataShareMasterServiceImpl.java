package com.truetrack.sharing.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truetrack.sharing.entity.BusinessDataShareMaster;
import com.truetrack.sharing.repository.BusinessDataShareMasterRepository;
import com.truetrack.sharing.service.BusinessDataShareMasterService;

@Service
public class BusinessDataShareMasterServiceImpl implements BusinessDataShareMasterService {

	@Autowired
	private BusinessDataShareMasterRepository repository;

	@Override
	public List<BusinessDataShareMaster> getMasterRecords() {
		return repository.findAll();
	}

	@Override
	public BusinessDataShareMaster addMasterRecord(BusinessDataShareMaster master) {
		return repository.save(master);
	}

	@Override
	public BusinessDataShareMaster updateMasterRecord(BusinessDataShareMaster master, Integer dataShaeId) {
		Optional<BusinessDataShareMaster> dbMaster = repository.findById(dataShaeId);
		dbMaster.ifPresent(m -> {
			if (Objects.nonNull(master.getSecret()) && !"".equalsIgnoreCase("")) {
				m.setSecret(master.getSecret());
			}
			if (Objects.nonNull(master.getCreatedByIp()) && !"".equalsIgnoreCase("")) {
				m.setCreatedByIp(master.getCreatedByIp());
			}
		});
		return repository.save(dbMaster.get());
	}

	@Override
	public BusinessDataShareMaster getMasterRecord(Integer dataShareId) {
		return repository.findById(dataShareId).orElseGet(null);
	}

}
