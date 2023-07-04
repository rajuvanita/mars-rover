package com.demo.marsrover.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROVER")
public class Rover implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long roverId;
	private String roverName;
	private String direction;
	private Long xCoordinate;
	private Long yCoordinate;
	

	public Rover() {
	};

	public Rover(Long id, Long roverId, String roverName, Long xCoordinate, Long yCoordinate, String direction) {
		super();
		this.id = id;
		this.roverId = roverId;
		this.roverName = roverName;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.direction = direction;
	}


	public Long getRoverId() {
		return roverId;
	}


	public Long getId() {
		return id;
	}

	public void setRoverId(Long roverId) {
		this.roverId = roverId;
	}

	
	public String getRoverName() {
		return roverName;
	}
	public void setRoverName(String roverName) {
		this.roverName = roverName;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Long getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Long xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Long getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Long yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Override
	public String toString() {
		return "Rover [id=" + id + ", roverId=" + roverId + ", roverName=" + roverName + ", direction=" + direction
				+ ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
	}

}
