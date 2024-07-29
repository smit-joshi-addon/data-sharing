package com.truetrack.sharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truetrack.sharing.entity.BusinessDataShareDetail;
import com.truetrack.sharing.entity.BusinessDataShareMaster;

public interface BusinessDataShareDetailRepository extends JpaRepository<BusinessDataShareDetail, Integer>{

	List<BusinessDataShareDetail> findByDataShare(BusinessDataShareMaster dataShare);
}
