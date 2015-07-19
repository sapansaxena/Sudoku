package com.sudoku.api.bean;

import javax.validation.constraints.NotNull;

public class Move {
	private static final long serialVersionUID = 7680671649158125863L;

	@NotNull(message="Number cannot be empty")
	private Integer number;
		
	@NotNull(message="Coordinates cannot be empty")
	private Integer x;
	
	@NotNull(message="Coordinates cannot be empty")
	private Integer y;
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
}
