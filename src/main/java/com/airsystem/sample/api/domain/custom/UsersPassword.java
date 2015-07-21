package com.airsystem.sample.api.domain.custom;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public class UsersPassword {
	private String oldpassword;
	private String newpassword;

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String pOldpassword) {
		oldpassword = pOldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String pNewpassword) {
		newpassword = pNewpassword;
	}
}