package com.lishan.finance.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lishan.finance.pojo.InvestItem;
import com.lishan.finance.pojo.LoanDetail;
import com.lishan.finance.pojo.User;
import com.lishan.finance.service.LoanService;

@Controller
@RequestMapping("frontstage")
public class LoanController {

	@Autowired
	@Qualifier("lService")
	private LoanService loanService;
	
	@RequestMapping("/commitLoan")
	@ResponseBody
	public String commitLoan(LoanDetail loanDetail,HttpSession session)throws Exception{
		System.out.println(loanDetail);
		User user= (User)session.getAttribute("user");
		JSONObject jsonObject=new JSONObject();
		if(user != null) {
			Integer uid=user.getId();
			loanDetail.setUid(uid);
			String msg=loanService.commitLoan(loanDetail);
			if(msg != null) {
				jsonObject.put("success", false);
				jsonObject.put("msg", msg);
			}else {
				session.setAttribute("loanDetail", loanDetail);
				jsonObject.put("success", true);
			}
		}else {
			jsonObject.put("success", false);
			jsonObject.put("msg", "您还未登录,请先登录!");
		}
		return jsonObject.toJSONString();
	}
	
	@RequestMapping("/createInvestItem")
	public String createInvestItem(HttpSession session,InvestItem investItem,Integer loan_id)throws Exception{
		session.removeAttribute("loanDetail");
		loanService.createInvestItem(investItem,loan_id);
		return "forward:/frontstage/getAsset-Statistics";
	}
}
