package com.cq.struts2.action;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cq.dao.Impl.SupperDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * 用于继承，处理用户是否登录及分页
 *
 * @author Li Yong Qiang
 */
public class  BaseAction extends ActionSupport implements ServletRequestAware {
	protected int recPerPage = 5; // 分页中每页的记录数
	protected Locale locale = null; // 本地语言信息
	protected HttpServletRequest request;
//	getText("system.total"); Struts2 国际化用 getText 的形式
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	@Override
	public String execute() throws Exception {
		// 获取Locale信息
		this.locale = request.getLocale();
		// 如果用户没有登录，跳转到登录页面
		if (request.getSession().getAttribute("user") == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	/**
	 * 分页
	 * @param hql hql语句（不包含select,从from子句开始）
	 * @param recPerPage 每页的记录数
	 * @param currPage 当前页码
	 * @param action 请求提交的action地址
	 * @param where 条件数组
	 * @return Map集合(装载结果集对象及分页条)
	 */
	public Map getPage(String hql, int recPerPage, String currPage,
					   String action, Object[] where) {
		// 实例化一个Map对象
		Map map = new HashMap();
		// 分页条
		StringBuffer pagingBar = new StringBuffer();
		List list = null; // 结果集
		int iCurrPage = 1; // 当前页码
		// 如果传递了页码则对当前页码赋值
		if (currPage != null && !currPage.isEmpty()) {
			iCurrPage = Integer.parseInt(currPage);
		}
		// 实例化SupperDao对象
		SupperDao dao = new SupperDao();
		int pages = 0; // 总页数
		// 获取总记录数
		Long l = (Long) dao.uniqueResult("select count(*) " + hql, where);
		int count = l.intValue(); // 将总记录数转为int型
		if (count > 0) {
			// 计算总页数
			if (count % recPerPage == 0) {
				pages = count / recPerPage;
			} else {
				pages = count / recPerPage + 1;
			}
			if (iCurrPage > pages) {
				iCurrPage = pages;
			}
			if (iCurrPage < 1) {
				iCurrPage = 1;
			}
			// 分页查询获取结果集
			list = dao.findPaging(hql, (iCurrPage - 1) * recPerPage,
					recPerPage, where);

				// 构造分页条
			pagingBar.append(
					"<form name='pagingForm' action='" + action + "' method='post'>");
			// 在分页条中添加总记录数
			pagingBar.append("总记录数：" + count);
			pagingBar.append("   ");
			pagingBar.append("总共" + "  "
					+ pages + "  " + "页");
			pagingBar.append("   ");
			// 页数大于1显示上一页超链接，否则不显示超链接
			if (iCurrPage > 1) {
				pagingBar.append("<a href=" + action + "&currPage=1>" + "第一页" + "</a>");
				pagingBar.append("   ");
				pagingBar.append("<a href=" + action + "&currPage=" + (iCurrPage - 1) + ">" + "前一页" + "</a>");
				pagingBar.append("   ");
			} else {
				pagingBar.append("第一页");
				pagingBar.append("   ");
				pagingBar.append("前一页");
				pagingBar.append("   ");
			}
			// 显示当前页码
			pagingBar.append("<font color='red'>" + iCurrPage + "</font>");
			pagingBar.append("   ");
			// 页数小于总页数显示下一页超链接，否则不显示超链接
			if (iCurrPage < pages) {
				pagingBar.append("<a href=" + action + "&currPage=" + (iCurrPage + 1) + ">" + "后一页" + "</a>");
				pagingBar.append("   ");
				pagingBar.append("<a href=" + action + "&currPage=" + pages + ">" + "最后页" + "</a>");
			} else {
				pagingBar.append("Next");
				pagingBar.append("   ");
				pagingBar.append("最后页");
			}
			pagingBar.append("   ");
			pagingBar.append("<input type='text' name='currPage' size='1'>");
			pagingBar.append("<input type='submit' value='GO'>");
			pagingBar.append("</form>");
		}

		map.put("list", list);// 结果集
		map.put("bar", pagingBar.toString());// 分页条的字符串形式
		return map;
	}
}