package com.truetrack.sharing.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataShareDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dataShareDetailId;

	@ManyToOne
	private BusinessDataShareMaster dataShare;
	private String secret;
	private LocalDateTime validTill;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	private String createdByUserId;
	private String createdByUser;
	private String createdByIp;
	private Boolean status;
}
