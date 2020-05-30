package com.jaswine.time;



import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期/时间工具类
 *
 * <p>
 *     提供对日期和时间的常规操作，基于Jdk1.8编写。Java8中新增日期和时间是符合{@code ISO-8601}标准的
 *     对时间格式返回进行了基本的封装，提拱了几种时间格式
 * </p>
 * <P>
 *     {@link Date}中对时间的操作的类不是安全的，Java8提供了新的日期时间类来操作
 * </P>
 *
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 * @see Duration
 *
 * @author Jaswine
 * @version 1.0
 *
 */
public final class DateTimeUtil {


	/*====================================时间类型转化============================================*/

	/**
	 * Date转化为Calendar
	 * @param date 时间
	 * @return calendar
	 */
	public static Calendar convertDate2Calendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}


	/**
	 * Date转化成为LocalDateTime
	 * @param date date
	 * @return localDateTime
	 */
	public static LocalDateTime convertDate2LocalDateTime(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * Date转化成为LocalDate
	 * @param date date
	 * @return localDate
	 */
	public static LocalDate convertDate2LocalDate(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Date转化成为LocalTime
	 * @param date date
	 * @return localTime
	 */
	public static LocalTime convertDate2LocalTime(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
	}

	public static Date convert2Date(LocalDateTime dateTime){
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date convert2Date(LocalDate date){
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}



	/*====================================时间计算============================================*/

	/**
	 * 判断两个时间段是否有交集
	 * @param d1 时间段1
	 * @param d2 时间段2
	 * @return boolean
	 */
	public static boolean isDruationIntersect(Druation d1,Druation d2){

		/*-----第一种情况:d1s======d1e--d2s======d2e--------*/
		if (DateTimeUtil.convertDate2Calendar(d1.getStart()).before(DateTimeUtil.convertDate2Calendar(d2.getStart()))) {
			if (DateTimeUtil.convertDate2Calendar(d1.getEnd()).before(DateTimeUtil.convertDate2Calendar(d2.getStart()))) {
				return false;
			}
		}

		/*-----第二种情况:d2s======d2e--d1s======d1e--------*/
		if (DateTimeUtil.convertDate2Calendar(d1.getStart()).after(DateTimeUtil.convertDate2Calendar(d2.getEnd()))){
			if (DateTimeUtil.convertDate2Calendar(d1.getEnd()).after(DateTimeUtil.convertDate2Calendar(d2.getEnd()))){
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断时间是否在某一区间
	 * @param paramDate 目标时间
	 * @param startDate 开始
	 * @param endDate 结束
	 * @return boolean
	 */
	public static boolean isBetweenDruation(Date paramDate,Date startDate ,Date endDate){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(paramDate);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		if (c1.after(start) && c1.before(end)){
			return true;
		}
		return false;
	}




	/*====================================时间格式化============================================*/

	public static String format(LocalDateTime dateTime,DateFormatEnum formatEnum){
		return DateTimeFormatter.ofPattern(formatEnum.getValue()).format(dateTime);
	}

	public static String format(LocalDate localDate,DateFormatEnum formatEnum){
		return DateTimeFormatter.ofPattern(formatEnum.getValue()).format(localDate);
	}


	/*====================================时间设定============================================*/

	public static LocalDateTime setDayOfDateTime(Date date,int day){
		LocalDateTime localDateTime = convertDate2LocalDateTime(date);
		return LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonth(),day, 0, 0);
	}


	/**
	 * 获得日期(默认:2018-10-10)
	 * @return yyyy-mm-dd
	 */
	public static String getDate(){
		return getDate(LocalDate.now(),DateFormatEnum.YYYY_MM_DD);
	}



	/**
	 * 获得日期 - 指定返回格式
	 * @param date 本地日期对象
	 * @param dateFormat 日期格式
	 * @return 对应格式的日期
	 * @throws UnsupportedTemporalTypeException 不支持的日期格式异常
	 */
	public static String getDate(LocalDate date,DateFormatEnum dateFormat) throws UnsupportedTemporalTypeException {
		return DateTimeFormatter.ofPattern(dateFormat.getValue()).format(date);
	}

	/**
	 * 获得日期时间(默认:2018-10-10 10:10:10)
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDateTime(){
		return getDateTime(LocalDateTime.now(),DateFormatEnum.YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * 获得日期时间 - 指定返回格式
	 * @param dateTime 本地日期时间
	 * @param dateFormat 日期时间格式
	 * @throws UnsupportedTemporalTypeException 不支持的日期时间格式
	 * @return 对应格式的日期时间
	 */
	public static String getDateTime(LocalDateTime dateTime,DateFormatEnum dateFormat) throws UnsupportedTemporalTypeException{
		return DateTimeFormatter.ofPattern(dateFormat.getValue()).format(dateTime);
	}

	/**
	 * 获得时间(默认:10:10：10)
	 * @return hh:mm:ss
	 */
	public static String getTime(){
		return getTime(LocalTime.now(),DateFormatEnum.HH_MM_SS);
	}

	/**
	 * 获得时间 - 指定返回格式
	 * @param time 时间
	 * @param dateFormatEnum 时间格式
	 * @throws UnsupportedTemporalTypeException 不支持的时间格式
	 * @return 对应格式的时间
	 */
	public static String getTime(LocalTime time,DateFormatEnum dateFormatEnum) throws UnsupportedTemporalTypeException{
		return DateTimeFormatter.ofPattern(dateFormatEnum.getValue()).format(time);
	}

	/**
	 * 返回指定月中的天
	 * @param date 日期
	 * @param day 指定天
	 * @return 指定日期
	 */
	public static Calendar getDayOfMonth(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),day);
		return calendar;
	}


	/**
	 * 获得date到月底的天数
	 * @param date 时间
	 * @return 天数
	 */
	public static Integer getDaysToEndOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 1;
	}

	/** 获得日期的月尾日期 */
	public static Calendar getEndDateOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	/** 格式化时间 */
	public static String formatDatetime(Date date,String format){
		return formatDatetime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), format);
	}

	/** 格式化时间 */
	public static String formatDatetime(LocalDateTime dateTime ,String format){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.CHINESE);
		return formatter.format(dateTime);
	}


	public static int getDaysToStartOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  calendar.get(Calendar.DAY_OF_MONTH)- calendar.getActualMinimum(Calendar.DAY_OF_MONTH) + 1;
	}

	public static long getDaysBtDate(Date d1,Date d2){
		LocalDateTime startDate = convertDate2LocalDateTime(d1);
		LocalDateTime endDate = convertDate2LocalDateTime(d2);
		return startDate.until(endDate, ChronoUnit.DAYS);
	}


	/*=======================转换==========================*/

	public static String month2YearAndMonth(Integer month){
		int y = (int)Math.floor(Double.valueOf(month) / Double.parseDouble("12"));
		int m = month % 12;
		if (m == 0){
			return y + "年";
		}
		if (y == 0){
			return m + "月";
		}
		return y + "年" + m + "月";
	}


	public static int getMonthByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}


	public static Calendar addMonth(Date date,int i ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		return calendar;
	}

	public static boolean isFirstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH) == 1;
	}

	public static Integer getYearOfDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static Calendar addDay(Date date,Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,days);
		return calendar;
	}

}

