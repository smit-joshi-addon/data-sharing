package com.truetrack.sharing.dtos;

import java.time.LocalDateTime;

public record BusinessDataShareDetailInternal(Integer dataShareDetailId, String secret, LocalDateTime validTill,
		LocalDateTime createdAt, String createdByUserId, String createdByUser, String createdByIp, Boolean status) {
}
