function checkitemform(){
    if(document.form1.title.value===""){
        window.alert("请输入商品标题！");
        document.form1.title.focus();
        return false;
    }if(document.form1.type.value===""){
        window.alert("请输入商品类型！");
        document.form1.type.focus();
        return false;
    }if(document.form1.price.value==""){
        window.alert("请输入商品价格！");
        document.form1.price.focus();
        return false;
    }if(document.form1.stock.value==""){
        window.alert("请输入商品库存量！");
        document.form1.stock.focus();
        return false;
    }
    return true;
}

function checkregform(){
    if(document.form1.email.value===""){
        window.alert("请输入有效的邮箱地址！");
        document.form1.name.focus();
        return false;
    }if(document.form1.password.value==""){
        window.alert("请输入密码！");
        document.form1.password.focus();
        return false;
    }if(document.form1.passwordConfirm.value==""){
        window.alert("请再次输入密码！");
        document.form1.passwordConfirm.focus();
        return false;
    }else{
        window.location.href="home.htm";
    }
}

function checkcategoryform(){
    if(document.form1.name.value===""){
        window.alert("请输入目录名称！");
        document.form1.name.focus();
        return false;
    }else{
        window.location.href="category_list.htm";
    }

}

function checkstorageform(){
    if(document.form1.storage.value===""){
        window.alert("请输入库存量！");
        document.form1.storage.focus();
        return false;
    }else{
        window.location.href="storage_list.htm";
    }

}

function checkpriceform(){
    if(document.form1.price.value===""){
        window.alert("请输入价格！");
        document.form1.price.focus();
        return false;
    }if(document.form1.discount.value===""){
        window.alert("请输入库存量！");
        document.form1.discount.focus();
        return false;
    }else{
        window.location.href="price_list.htm";
    }

}

function checkloginform(){
    if(document.form1.name.value===""){
        window.alert("请输入用户名！");
        document.form1.name.focus();
        return false;
    }if(document.form1.password.value===""){
        window.alert("请输入密码！");
        document.form1.password.focus();
        return false;
    }else{
        window.location.href="category_list.htm";
    }

}

function checkusersetform(){

    if(document.form1.credit.value===""){
        window.alert("请输入积分下限！");
        document.form1.credit.focus();
        return false;
    }if(document.form1.ratio.value===""){
        window.alert("请输入积分比例！");
        document.form1.ratio.focus();
        return false;
    }else{
        window.location.href="user_admin.htm";
    }
}

function checkordercheck(){
    window.alert("审核成功！");
    window.location.href="order_list_refresh.htm";
}

function checkorderselect(){
    var k=0;
    for(i=0;i<document.form1.ordersn.length;i++){
        if(document.form1.ordersn[i].checked)
            k=1;
    }
    if(k===0){
        window.alert("请选择要审核的订单！");
        return false;
    }else{
        window.location.href="order_check.htm";
    }

}

function checkitemrefresh(){
    if(document.form1.name.value===""){
        window.alert("请输入商品名称！");
        document.form1.name.focus();
        return false;
    }if(document.form1.price.value==""){
        window.alert("请输入商品价格！");
        document.form1.price.focus();
        return false;
    }if(document.form1.storage.value==""){
        window.alert("请输入商品库存量！");
        document.form1.storage.focus();
        return false;
    }if(document.form1.discount.value==""){
        window.alert("请输入商品折扣！");
        document.form1.discount.focus();
        return false;
    }else{
        window.location.href="item_list_refresh.htm";
    }

}