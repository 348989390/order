package com.wechat.order.service.impl;

import com.wechat.order.dao.OrderDetailDao;
import com.wechat.order.dao.OrderMasterDao;
import com.wechat.order.dataobject.OrderDetail;
import com.wechat.order.dataobject.OrderMaster;
import com.wechat.order.dataobject.ProductInfo;
import com.wechat.order.dto.CartDTO;
import com.wechat.order.dto.OrderDTO;
import com.wechat.order.enums.ResultEnum;
import com.wechat.order.exception.SellException;
import com.wechat.order.service.OrderService;
import com.wechat.order.service.ProductInfoService;
import com.wechat.order.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    /**
     * 创建订单
     */
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量、价格）
        List<OrderDetail> orderDetailList =  orderDTO.getOrderDetailList();
        List<CartDTO> cartDTOList = new ArrayList<>();
        for(OrderDetail orderDetail:orderDetailList){
            //根据商品id查询此商品信息
            ProductInfo productInfo =  productInfoService.findOne(orderDetail.getProductId());
            if(productInfo==null){//无此商品
                throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
            }
            //2.计算总价(用于存入主表中)
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.保存OrderDetail
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            //属性拷贝
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        //4.保存订单（OrderMaster）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrder_status(0);
        orderMaster.setPayStatus(0);
        orderMasterDao.save(orderMaster);

        //4.下单成功库存减一
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderDTO orderDTO = new OrderDTO();
        //查询订单
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if(orderMaster==null){//订单不存在
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findlist(String buyerOpendId, Pageable pageable) {

//        PageRequest page = PageRequest.of(0,1);

        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpendId,pageable);
//        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>();
        return null;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //判断订单状态
        if(!orderDTO.getOrder_status().equals("0")){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrder_status());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrder_status(2);
        OrderMaster updateResult=orderMasterDao.save(orderMaster);
        if(updateResult==null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        log.info("成功修改订单状态");
        //返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        CartDTO cartDTO = new CartDTO("1",1);
//        cartDTO.setProductId("1584086894274478371");
//        cartDTO.setProductQuantity(1);
        cartDTOList.add(cartDTO);
        productInfoService.increaseStock(cartDTOList);
        log.info("成功返还库存");
        //退款
        if(orderDTO.getPayStatus().equals("")){
            //TODO
        }
        log.info("成功取消订单");
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrder_status().equals(0)){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrder_status());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrder_status(1);
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster update = orderMasterDao.save(orderMaster);
        if(update==null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrder_status()!=0){
            log.error("【订单支付】订单状态不正确，orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrder_status());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(orderDTO.getPayStatus()!=0){
            log.error("【订单支付】订单支付状态不正确，orderId={},payStatus={}",
                    orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAID_ERROR);
        }

        //修改支付状态
        orderDTO.setOrder_status(1);
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster update = orderMasterDao.save(orderMaster);
        if(update==null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }
}
