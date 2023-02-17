package com.closetou.cloth.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cloth {
	private int no;

	private String id;	
	
	private String photoId;
	
	private String name;
	
	private Date createDate;
		
	private String[] catagory;
	
	private String detail;
	
}
