package com.truetrack.sharing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truetrack.sharing.dtos.BusinessDataShareDetailDTO;
import com.truetrack.sharing.dtos.BusinessDataShareDetailInternal;
import com.truetrack.sharing.dtos.DataShareDTO;
import com.truetrack.sharing.dtos.UserDTO;
import com.truetrack.sharing.entity.BusinessDataShareDetail;
import com.truetrack.sharing.entity.BusinessDataShareMaster;
import com.truetrack.sharing.entity.Users;
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
				details.stream().map(this::mapToDataShareDetails).collect(Collectors.toUnmodifiableList()),
				mapToDataShare(master), mapToUser(master.getUser()));
	}

	private BusinessDataShareDetailInternal mapToDataShareDetails(BusinessDataShareDetail detail) {
		if (detail == null) {
			return null;
		}
		return new BusinessDataShareDetailInternal(detail.getDataShareDetailId(), detail.getSecret(),
				detail.getValidTill(), detail.getCreatedAt(), detail.getCreatedByUserId(), detail.getCreatedByUser(),
				detail.getCreatedByIp(), detail.getStatus());
	}

	private DataShareDTO mapToDataShare(BusinessDataShareMaster master) {
		return new DataShareDTO(master.getDataShareId(), master.getSecret(), master.getCeatedBy(),
				master.getCreatedByIp(), master.getStatus());
	}

	private UserDTO mapToUser(Users user) {
		return new UserDTO(user.getUserId(), user.getName(), user.getEmail(), user.getStatus());
	}

	@Override
	public BusinessDataShareDetailInternal getDetail(Integer dataShareDetailId) {
		return mapToDataShareDetails(repository.findById(dataShareDetailId).orElseGet(null));
	}

	@Override
	public BusinessDataShareDetailInternal addDetail(BusinessDataShareDetail detail) {
		return mapToDataShareDetails(repository.save(detail));
	}

}
