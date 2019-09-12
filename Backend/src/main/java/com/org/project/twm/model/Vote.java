package com.org.project.twm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * User entity model.
 *
 * @author abhishek.sisodiya
 */

@Entity
@Getter
@Setter
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "date", nullable = false)
	private String date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false, updatable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "provider_id", nullable = false, updatable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Provider provider;
}
