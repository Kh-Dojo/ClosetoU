package com.closetou.donation.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationForm {

	private int Df_Code;
	private int no;
	private String Df_item;
	private String Df_amount;
	private String Df_name;
	private String Df_phone;
	private String Df_addr;
	private String Delivber;
	private String Df_details;
	private Date Df_create;
	private String Si_Do;
	
}
