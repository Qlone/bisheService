package controller;

import bean.GoodsRoughBean;
import bean.GsonTestBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2017/2/11.
 */
@Controller
@RequestMapping(value = "")
public class MainTest {

    //入口
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String entry(){
        return "index.jsp";
    }
    //gson 数据测试
    @RequestMapping(value = "/gson",method = RequestMethod.GET)
    @ResponseBody
    public String getGsonTest(){
        List<GoodsRoughBean.GoodsBean> listBeanList = new ArrayList<GoodsRoughBean.GoodsBean>();
        GoodsRoughBean.GoodsBean listBean = new GoodsRoughBean.GoodsBean();
        GoodsRoughBean.GoodsBean listBean1 = new GoodsRoughBean.GoodsBean();
        GoodsRoughBean.GoodsBean listBean2 = new GoodsRoughBean.GoodsBean();
//        try {
//            listBean.setTitle(new String("耐克 顺丰 最后一天买不了吃亏买不了上当".getBytes("gb2312"),"UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        listBean.setTitle(" 耐克 顺丰 最后一天买不了吃亏买不了上当");
        listBean.setPrice(100.2);
        listBean.setSales(12);
        listBean.setType("recommend");
        listBean.setPicture("http://www.miss-rabbit.cc/resources/images/photos/28f04fbb-7a9a-4ab2-a12d-e67a45f4eedd.jpg");

        listBean1.setTitle(" EMS 秋装潮男 优惠打折");
        listBean1.setPrice(160.2);
        listBean1.setSales(120);
        listBean1.setType("hot");
        listBean1.setPicture("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486927384744&di=1d1cc2fce5838349e2a3d31afaf86199&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fbba1cd11728b47101489df48c0cec3fdfd03238b.jpg");

        listBean2.setTitle(" EMS 秋装潮男 优惠打折");
        listBean2.setPrice(16.2);
        listBean2.setSales(130);
        listBean2.setType("hot");
        listBean2.setPicture("http://e.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c6060131bee8fb1cb1349547728.jpg");
        listBeanList.add(listBean1);
        listBeanList.add(listBean);
        listBeanList.add(listBean2);
        GoodsRoughBean gsonTestBean = new GoodsRoughBean();
        gsonTestBean.setGoods(listBeanList);
        System.out.println(JsonUtil.toJson(gsonTestBean));
        return JsonUtil.toJson(gsonTestBean);
    }
}
