package com.org.project.twm.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "provider")
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
	public Set<Vote> votingDaos;

	@Column(name = "providername", unique = true, nullable = false)
	public String providername;

}
