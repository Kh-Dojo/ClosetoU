package com.closetou.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SideMenu {

	private String mainMenuName;
	
	private String[] subMenuName;
	
}
