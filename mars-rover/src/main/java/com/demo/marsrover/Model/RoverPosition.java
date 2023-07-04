package com.demo.marsrover.Model;

import java.util.ArrayList;
import java.util.List;

public class RoverPosition {
	private String name;
	private String coordinate;
	private String direction;
	private Long x;
	private Long y;
	private List<Long> rovers = new ArrayList<>();
	
	
	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<Long> getRovers() {
		return rovers;
	}

	public void setRovers(Long rover) {
		if (!rovers.contains(rover)) {
			rovers.add(rover);
		}
	}

	public Long getX() {
		return x;
	}

	public void setX(Long x) {
		this.x = x;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}
	
	public RoverPosition() {}


}
