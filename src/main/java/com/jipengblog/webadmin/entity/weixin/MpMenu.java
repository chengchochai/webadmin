package com.jipengblog.webadmin.entity.weixin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jipengblog.webadmin.entity.constant.MpMenuType;

@Entity
@Table(name = "wx_mpMenu")
public class MpMenu implements Serializable {

	public MpMenu() {
	}

	public MpMenu(String mpMenuName, MpMenuType mpMenuType, String mpMenuKey,
			String mpMenuUrl, String mpMenuMediaId, Long mpAccountId,
			Integer parentMpMenuId) {
		this.mpMenuName = mpMenuName;
		this.mpMenuType = mpMenuType;
		this.mpMenuKey = mpMenuKey;
		this.mpMenuUrl = mpMenuUrl;
		this.mpMenuMediaId = mpMenuMediaId;
		this.mpAccountId = mpAccountId;
		this.parentMpMenuId = parentMpMenuId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056706385461423850L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mpMenuId;// 自定义菜单在本系统的标识

	@Column(nullable = false, length = 4)
	private String mpMenuName;// 菜单标题，不超过16个字节，子菜单不超过40个字节

	@Enumerated(EnumType.STRING)
	private MpMenuType mpMenuType;// 菜单的响应动作类型

	@Column(nullable = true, length = 50)
	private String mpMenuKey;// 菜单KEY值，用于消息接口推送，不超过128字节

	@Column(nullable = true, length = 256)
	private String mpMenuUrl;// 网页链接，用户点击菜单可打开链接，不超过256字节

	@Column(nullable = true, length = 50)
	private String mpMenuMediaId;// 调用新增永久素材接口返回的合法media_id

	@Column(nullable = false, columnDefinition = "INT default 0")
	private Integer parentMpMenuId;// 如果是0表示一级按钮，否则为二级按钮

	@Column(nullable = false)
	private Long mpAccountId;

	public Long getMpMenuId() {
		return mpMenuId;
	}

	public void setMpMenuId(Long mpMenuId) {
		this.mpMenuId = mpMenuId;
	}

	public String getMpMenuName() {
		return mpMenuName;
	}

	public void setMpMenuName(String mpMenuName) {
		this.mpMenuName = mpMenuName;
	}

	public MpMenuType getMpMenuType() {
		return mpMenuType;
	}

	public void setMpMenuType(MpMenuType mpMenuType) {
		this.mpMenuType = mpMenuType;
	}

	public String getMpMenuKey() {
		return mpMenuKey;
	}

	public void setMpMenuKey(String mpMenuKey) {
		this.mpMenuKey = mpMenuKey;
	}

	public String getMpMenuUrl() {
		return mpMenuUrl;
	}

	public void setMpMenuUrl(String mpMenuUrl) {
		this.mpMenuUrl = mpMenuUrl;
	}

	public String getMpMenuMediaId() {
		return mpMenuMediaId;
	}

	public void setMpMenuMediaId(String mpMenuMediaId) {
		this.mpMenuMediaId = mpMenuMediaId;
	}

	public Integer getParentMpMenuId() {
		return parentMpMenuId;
	}

	public void setParentMpMenuId(Integer parentMpMenuId) {
		this.parentMpMenuId = parentMpMenuId;
	}

	public Long getMpAccountId() {
		return mpAccountId;
	}

	public void setMpAccountId(Long mpAccountId) {
		this.mpAccountId = mpAccountId;
	}

}
