package edu.poly.shop.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	/**
	* Đọc cookie từ request
	* @param name tên cookie cần đọc
	* @return đối tượng cookie đọc được hoặc null nếu không tồn tại
	*/
	public Cookie get(String name) {
		return null;
	}
	/**
	* Đọc giá trị của cookie từ request
	* @param name tên cookie cần đọc
	* @return chuỗi giá trị đọc được hoặc rỗng nếu không tồn tại
	*/
	public String getValue(String name) {
		return null;
	}
	/**
	* Tạo và gửi cookie về client
	* @param name tên cookie
	* @param value giá trị cookie
	* @param hours thời hạn (giờ)
	* @return đối tượng cookie đã tạo
	*/
	public Cookie add(String name, String value, int hours) {
		return null;
	}
	/**
	* Xóa cookie khỏi client
	* @param name tên cookie cần xóa
	*/
	public void remove(String name) {
		
	}
}
