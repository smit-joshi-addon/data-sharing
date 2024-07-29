package com.truetrack.sharing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truetrack.sharing.dtos.BusinessDataShareDetailDTO;
import com.truetrack.sharing.dtos.BusinessDataShareDetailInternal;
import com.truetrack.sharing.entity.BusinessDataShareDetail;
import com.truetrack.sharing.entity.BusinessDataShareMaster;
import com.truetrack.sharing.repository.BusinessDataShareDetailRepository;
import com.truetrack.sharing.service.BusinessDataShareDetailService;
import com.truetrack.sharing.service.BusinessDataShareMasterService;

@Service
public class BusinessDataShareDetailServiceImpl implements BusinessDataShareDetailService {

	@Autowired
	private BusinessDataShareDetailRepository repository;

	@Autowired
	private BusinessDataShareMasterService masterService;

	@Override
	public BusinessDataShareDetailDTO getDetails(Integer dataShareId) {

		BusinessDataShareMaster master = masterService.getMasterRecord(dataShareId);

		List<BusinessDataShareDetail> details = repository.findByDataShare(master);

		return new BusinessDataShareDetailDTO(
				details.stream().map(this::mapToInteral).collect(Collectors.toUnmodifiableList()), master);
	}

	private BusinessDataShareDetailInternal mapToInteral(BusinessDataShareDetail detail) {
		if (detail == null) {
			return null;
		}
		return new BusinessDataShareDetailInternal(detail.getDataShareDetailId(), detail.getSecret(),
				detail.getValidTill(), detail.getCreatedAt(), detail.getCreatedByUserId(), detail.getCreatedByUser(),
				detail.getCreatedByIp(), detail.getStatus());
	}

	@Override
	public BusinessDataShareDetailInternal getDetail(Integer dataShareDetailId) {
		return mapToInteral(repository.findById(dataShareDetailId).orElseGet(null));
	}

	@Override
	public BusinessDataShareDetailInternal addDetail(BusinessDataShareDetail detail) {
		return mapToInteral(repository.save(detail));
	}

}
