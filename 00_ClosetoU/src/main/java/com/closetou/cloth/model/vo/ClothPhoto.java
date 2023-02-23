package com.closetou.cloth.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothPhoto {

	private String photoId;

	private int no;
	
	private int clothNo;
	
	private String originalName;
	
	private Date createdDate;
	
	
}
