package com.example.test.comm.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.util.StringUtils;

public class BaseTools {
	
	public boolean _isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|(([0-9]+))?)$");
	}


}
