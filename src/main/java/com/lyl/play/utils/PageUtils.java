package com.lyl.play.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.play.vo.OrderItem;
import com.lyl.play.vo.PageQuery;
import com.lyl.play.vo.PageResult;


import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static Page convertToPage(PageQuery pageQuery) {
        if (pageQuery == null) {
            return new Page();
        }

        Page page = new Page();
        page.setCurrent(pageQuery.getCurrent());
        page.setSize(pageQuery.getPageSize());
        List<OrderItem> orders = pageQuery.getOrders();
        if (orders != null && !orders.isEmpty()) {
            List<com.baomidou.mybatisplus.core.metadata.OrderItem> mybatisOrderItems = new ArrayList<>();
            for (OrderItem order : orders) {
                com.baomidou.mybatisplus.core.metadata.OrderItem orderItem = new com.baomidou.mybatisplus.core.metadata.OrderItem();
                orderItem.setAsc(order.isAsc());
                orderItem.setColumn(order.getColumn());
                mybatisOrderItems.add(orderItem);
            }
            page.setOrders(mybatisOrderItems);
        }
        return page;
    }

    public static <T, R> PageResult<R> convertToResult(Page<T> page) {
        return convertToResult(page, null);
    }

    public static <T, R> PageResult<R> convertToResult(Page<T> page, DataConverter<T, R> converter) {
        PageResult<R> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setSize(page.getSize());
        pageResult.setPages(page.getPages());
        pageResult.setCurrent(page.getCurrent());
        List<T> records = page.getRecords();
        if (converter != null) {
            if (records != null) {
                List<R> result = new ArrayList<>(records.size());
                for (T record : records) {
                    result.add(converter.convert(record));
                }
                pageResult.setRecords(result);
            }
        } else {
            pageResult.setRecords((List<R>) records);
        }

        return pageResult;
    }
    
    public static  <T> PageResult<T> customConvertToResult(int currentPage,int pageSize, List<T> total) {
    	  PageResult<T> pageResult = new PageResult<>(); 
    	  List<T> current = new ArrayList<>();
          pageResult.setCurrent(currentPage);
          pageResult.setSize(pageSize);
          if(total.size()==0) {
        	  pageResult.setTotal(0);
              pageResult.setPages(0);
              pageResult.setRecords(total);
    		return pageResult;
          }
          //总页数
          int totalPage = 0; 
          if(total.size() % pageSize==0){
              //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
              totalPage = total.size() / pageSize;
          }else{
              //不整除，就要在加一页，来显示多余的数据。
              totalPage = total.size() / pageSize + 1;
          }
         
          for (int i = (currentPage-1)*pageSize; i < currentPage*pageSize; i++) {
              if(i < total.size()){
              	current.add(total.get(i));
              }
          }
          pageResult.setTotal(total.size());
          pageResult.setPages(totalPage);
          pageResult.setRecords(current);
		return pageResult;
    	
    }
}
