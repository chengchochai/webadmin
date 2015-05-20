/**
* @author liwang
* @date 2015年5月6日 上午11:47:08 
*/
package com.jipengblog.webadmin.web.common;

import net.sf.json.JSONArray;

/** 
 * @Description:
 * @author liwang
 * @date 2015年5月6日 上午11:47:08 
 *  
 */
public class DataTablesPojo {

	private int draw;//每次请求的值需要改变
	private int recordsTotal;//总纪录
	private int recordsFiltered;//总纪录
	private JSONArray data;//具体的数据

	public DataTablesPojo(int draw, int recordsTotal, int recordsFiltered,
			JSONArray data) {
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
		this.data = data;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

}
