package com.xiexin.service;

import com.xiexin.bean.Customer;
import com.xiexin.bean.CustomerExample;
import com.xiexin.dao.CustomerDAO;
import com.xiexin.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired(required = false)
	private CustomerDAO customerDAO;
	public long countByExample(CustomerExample example){
    	return customerDAO.countByExample(example);
    }

	public int deleteByExample(CustomerExample example){
    	return customerDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return customerDAO.deleteByPrimaryKey(id);
    }

	public int insert(Customer record){
    	return customerDAO.insert(record);
    }

	public int insertSelective(Customer record){
    	return customerDAO.insertSelective(record);
    }

	public List<Customer> selectByExample(CustomerExample example){
    	return customerDAO.selectByExample(example);
    }

	public Customer selectByPrimaryKey(Integer id){
    	return customerDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Customer record, CustomerExample example){
    	return customerDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Customer record, CustomerExample example){
    	return customerDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Customer record){
    	return customerDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Customer record){
    	return customerDAO.updateByPrimaryKey(record);
    }

	@Override
	public double getMoney(double gongSiLng, double gongSiLat, double customerLng, double customerLat) {
		//根据两个经纬度,算出距离,然后把距离(km)除以100得出的钱算作人民币
		double m = MapUtils.algorithm(gongSiLng, gongSiLat, customerLng, customerLat);
		//要想计计算人民币,最简单的算法
		m = m/100; //人民币   起步价!!!
		if(m<10){
			m=10;
		}
		return m;
	}


}
