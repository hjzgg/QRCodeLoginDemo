package cn.kuwo.vo;

import java.util.HashMap;

public class LoginUserVo {
	private static HashMap<String, UserVo> loginUserMap = new HashMap<String, UserVo>();
	private static LoginUserVo loginUserVo;
	public static LoginUserVo getVo(){
		if(loginUserVo == null){
			loginUserVo = new LoginUserVo();
		}
		return loginUserVo;
	}
	public static HashMap<String, UserVo> getLoginUserMap() {
		return loginUserMap;
	}
}
