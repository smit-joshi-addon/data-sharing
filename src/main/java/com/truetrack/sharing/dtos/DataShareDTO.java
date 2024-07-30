package com.truetrack.sharing.dtos;

public record DataShareDTO(Integer dataShareId, String secret, String ceatedBy, String createdByIp, Boolean status) {

}
