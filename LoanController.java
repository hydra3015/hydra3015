package com.lishan.finance.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lishan.finance.pojo.LoanDetail;
import com.alibaba.fastjson.JSONObject;
import com.lishan.finance.service.LoanService;
import com.lishan.finance.pojo.InvestItem;
import com.lishan.finance.pojo.User;

@Controller
@RequestMapping("frontstage")
public class LoanController {

	
	@Qualifier("lService")
	@Autowired
	private LoanService loanService;
	
	@RequestMapping("/commitLoan")
	@ResponseBody
	public String commitLoan(LoanDetail loanDetail,HttpSession session)throws Exception{
		System.out.println(loanDetail);
		JSONObject jsonObject=new JSONObject();
		User user= (User)session.getAttribute("user");
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
			jsonObject.put("msg", "您还未登录,请先登录!");
			jsonObject.put("success", false);
		}
		return jsonObject.toJSONString();
	}
	
	@RequestMapping("/createInvestItem")
	public String createInvestItem(HttpSession session,InvestItem investItem,Integer loan_id)throws Exception{
		loanService.createInvestItem(investItem,loan_id);
		session.removeAttribute("loanDetail");
		return "forward:/frontstage/getAsset-Statistics";
	}
}
