package com.closetou.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeArticle {
	
	private int no;
	
	private int clothNumber;
	
	private int price;
	
	private String tradeEnd;
	
	private String free;
	
	private String tradeMethod;
	
	private String location;
	
	
}
