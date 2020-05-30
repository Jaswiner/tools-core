package com.jaswine.time;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author : Jaswine
 * @date : 2020-04-02 14:01
 **/
@Data
public class Druation {


	private Date start;

	private Date end;

	public  Druation(Date start,Date end){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);

		if (c1.after(c2)){
			throw new TimeOrderException("ERROR - Start Time Is After End Time");
		}
		this.start = start;
		this.end = end;
	}
}
