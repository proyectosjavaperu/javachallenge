package com.example.demo.model;

import java.io.Serializable;

public class ProductExternalApi implements Serializable {

	private static final long serialVersionUID = 1L;

	public Long id;

	public String marca;

	public String imgUrl;

	public String idProductAlike;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIdProductAlike() {
		return idProductAlike;
	}

	public void setIdProductAlike(String idProductAlike) {
		this.idProductAlike = idProductAlike;
	}
}
