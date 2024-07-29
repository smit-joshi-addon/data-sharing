package com.truetrack.sharing.dtos;

import java.util.List;

import com.truetrack.sharing.entity.BusinessDataShareMaster;

public record BusinessDataShareDetailDTO(List<BusinessDataShareDetailInternal> details,
		BusinessDataShareMaster dataShare) {
}


