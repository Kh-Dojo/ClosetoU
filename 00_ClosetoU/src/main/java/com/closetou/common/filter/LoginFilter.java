package com.closetou.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.closetou.member.model.vo.Member;

@WebFilter({"/member/*", "/article/tradeWrite", "/article/communityWrite", "/donation/donation_Form", "/trade/article/update", "/trade/article/delete"})
public class LoginFilter extends HttpFilter implements Filter {
    public LoginFilter() {
    }

	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			Member loginMember = (Member)(session.getAttribute("loginMember"));

			if(loginMember == null) {
				request.setAttribute("msg","로그인 후 이용해주세요.");
				request.setAttribute("location","/");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

				return;
			}
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
