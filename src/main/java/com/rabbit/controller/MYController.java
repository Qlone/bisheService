
package com.rabbit.controller;


import com.rabbit.bean.GsonLogin;
import com.rabbit.entity.CommentEntity;
import com.rabbit.entity.GoodsEntity;
import com.rabbit.entity.OrdersEntity;
import com.rabbit.entity.UserEntity;
import com.rabbit.service.*;
import com.rabbit.service.impl.GoodService;
import com.rabbit.service.impl.OtherSerivce;
import com.rabbit.service.impl.UserService;
import com.rabbit.util.DatagridUtil;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.OwnerFileSaver;
import com.rabbit.util.RabbitLog;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
@Controller
@RequestMapping(value = "")
public class MYController {

    @Autowired
    GoodService mGoodService;

    @Autowired
    ICommentService iCommentService;

    @Autowired
    IOrderService iOrderService;

    @Autowired
    OtherSerivce mOtherSerivce;

    @Autowired
    UserService mUserService;

    @RequestMapping(value = "/")
    public String getHome(){
        return "redirect: /index";
    }

    @RequestMapping("/{mainpage}")//返回对应的页面
    public String showPage(@PathVariable String mainpage){
        return mainpage;
    }


    /**
     * 登陆
     * @param
     * @param
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password,Model model){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(password);
        userEntity.setUserName(username);
        GsonLogin user = mUserService.login(userEntity);
        if (user.getUserEntity() == null){
            String msg = "账号密码错误";
            model.addAttribute("message",msg);
            return "/login";
        }
        String type = user.getUserEntity().getUserType();
        if (!type.equals("administrator")){
            String msg = "没有管理员权限";
            model.addAttribute("message",msg);
            RabbitLog.debug(type);
            return "/login";
        }else {
            session.setAttribute("username",username);
            return "redirect: /index";
        }
    }

    //退出
    @RequestMapping("logout")
    public String loginout(HttpSession session) throws Exception{
        session.invalidate();
        return "/login";
    }

   @RequestMapping("/item/list")//商品展示
   @ResponseBody
    public ModelAndView itemList(
       @RequestParam(value = "page", required=false) Integer page,
       @RequestParam(value = "lines",required = false) Integer lines) {
       ModelAndView modelAndView = new ModelAndView();
       page = 1;
       lines = 100;
       List<GoodsEntity> list= mGoodService.getAllList(page, lines).getList();
       modelAndView.setViewName("/item_list");
       modelAndView.addObject("alladmin",list);
       return modelAndView;
   }

    @RequestMapping("/item/{Mpage}/{goodsId}")//商品展示
    public String viewUPGoods(
            @PathVariable String Mpage,
            @PathVariable Integer goodsId,
            Model model){
        IListBean<GoodsEntity> goodsEntity = mGoodService.getGoodsItem(goodsId);
        List itemList =  goodsEntity.getList();
        GoodsEntity item = (GoodsEntity) itemList.get(0);
        model.addAttribute("good",item);
        //用户评分
        double score = iCommentService.getCommentsScore(goodsId);
        model.addAttribute("score",score);
        return "item_"+Mpage;
    }

    //增加商品
    @RequestMapping("/item/add")
    public String addItem(HttpSession session,GoodsEntity goodsEntity,MultipartFile thempic,@RequestParam MultipartFile[] picturegroup){
        //文件路径上下文
        String contextPath = session.getServletContext().getRealPath("/");
        //图片数量
        final int count = picturegroup.length;
        //图片 路径存储
        StringBuffer stringBuffer = new StringBuffer();
        String themeImgString =null;
        try {
            themeImgString = OwnerFileSaver.saveImage(thempic, contextPath);
            for (int i = 0; i < count; i++) {
                stringBuffer.append(OwnerFileSaver.saveImage(picturegroup[i], contextPath) + "#");
                RabbitLog.debug("图片存储 : " + i);
            }
            goodsEntity.setGoodsDelete(IGoodService.GOODS_DELETE_FALSE);
            goodsEntity.setPicture(themeImgString);
            goodsEntity.setSales(0);
            goodsEntity.setViews(0);
            //图片保存
            goodsEntity.setPictureGroup(stringBuffer.toString());
            //添加商品
            mGoodService.addGoods(goodsEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/item_add";

    }

    @RequestMapping(value = "/item/edit/update")//修改商品........
    public String upGoods(HttpSession session,GoodsEntity goodsEntity,
                           MultipartFile themeImg){

        int id = goodsEntity.getGoodsId();
        IListBean<GoodsEntity> listBean = mGoodService.getGoodsItem(id);
        List<GoodsEntity> list = listBean.getList();
        GoodsEntity goods = list.get(0);

        String fileName = themeImg.getOriginalFilename();
       if(themeImg!=null&&fileName!=null &&fileName.length()>0) {
           //文件路径上下文
           String contextPath = session.getServletContext().getRealPath("/");

           //图片 路径存储
           StringBuffer stringBuffer = new StringBuffer();
           String themeImgString = null;

           try {
               themeImgString = OwnerFileSaver.saveImage(themeImg, contextPath);
           } catch (IOException e) {
               e.printStackTrace();
           }
           goods.setPicture(themeImgString);

       }

        goods.setPrice(goodsEntity.getPrice());
        goods.setStock(goodsEntity.getStock());
        goods.setStatus(goodsEntity.getStatus());
        goods.setType(goodsEntity.getType());
        goods.setTitle(goodsEntity.getTitle());
        try {
            mGoodService.updataGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/item_list";
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @RequestMapping("/item/delete/{goodsId}")
    public String delete(@PathVariable int goodsId) throws Exception {
        IListBean<GoodsEntity> goodsEntity = mGoodService.getGoodsItem(goodsId);
        List itemList =  goodsEntity.getList();
        GoodsEntity item = (GoodsEntity) itemList.get(0);
        item.setGoodsDelete(0);
        mGoodService.updataGoods(item);
        return "/item_list";
    }


    @RequestMapping(value = "/comment/list/{goodsId}")//根据商品id获取评论
    @ResponseBody
    public List getCommentList(@PathVariable int goodsId){
        int page = 1; int lines = 100;
        List<CommentEntity> list = iCommentService.getComments(goodsId, page, lines).getList();
        return list;
    }

    /**
     * 订单显示
     */
    @RequestMapping(value = "/order/list")
    @ResponseBody
    public DatagridUtil getOrder(Integer page, Integer rows) {

        IListBean<OrdersEntity> res = mOtherSerivce.getOrderNotSend(page,rows);
        List<OrdersEntity> list = res.getList();
        RabbitLog.debug(JsonUtil.toJson(list));

        long total = rows*res.getMaxPages();
        DatagridUtil result = new DatagridUtil();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    @RequestMapping(value = "/order/list/all")
    @ResponseBody
    public DatagridUtil getOrderall(Integer page, Integer rows) {

        IListBean<OrdersEntity> res = mOtherSerivce.getOrderAll(page,rows);
        List<OrdersEntity> list = res.getList();
        RabbitLog.debug(JsonUtil.toJson(list));

        long total = rows*res.getMaxPages();
        RabbitLog.debug(total);
        DatagridUtil result = new DatagridUtil();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }




    /**
     * 发货:完成
     */
    @RequestMapping(value = "/send/item")
    public String sendItems(Integer [] ids){
        List<Integer> list = new ArrayList<Integer>();
        for(int id: ids){
            list.add(id);
        }
        mOtherSerivce.sendGoods(list);

        return "/order/list";
    }

    /**用户list
     *
     */
    @RequestMapping(value = "user/list")
    public ModelAndView showUser (ModelAndView mv){
        IListBean<UserEntity> iListBean = mOtherSerivce.getUserAll(1,1000);
        List ul = iListBean.getList();
        mv.setViewName("/user_admin");
        mv.addObject("users",ul);
        return mv;
    }

    //添加余额
    @RequestMapping(value = "user/pay/{userId}")
    public String addBalance (@PathVariable int userId, double money){
        UserEntity userEntity = mUserService.getUserById(userId);
        double current_balance = userEntity.getBalance() + money;
        userEntity.setBalance(current_balance);
        try {
            mUserService.updataUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user_admin";
    }

}

