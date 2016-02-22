package com.ocellus.core.web.spring;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ocellus.core.util.StringUtil;

public class DateTypeEditor extends PropertyEditorSupport {
	public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DF_LONG.format(value) : "");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			if (!StringUtil.isEmpty(text))
				super.setValue(DF_LONG.parse(text));
		} catch (ParseException e) {
			if (!StringUtil.isEmpty(text))
				try {
					super.setValue(DF_SHORT.parse(text));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		}
	}
}