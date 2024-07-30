package com.truetrack.sharing.dtos;

import java.util.List;

public record BusinessDataShareDetailDTO(List<BusinessDataShareDetailInternal> details, DataShareDTO dataShare,
		UserDTO user) {
}
