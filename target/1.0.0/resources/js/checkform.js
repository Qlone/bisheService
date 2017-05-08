function checkitemform(){
    if(document.form1.title.value===""){
        window.alert("请输入商品标题！");
        document.form1.title.focus();
        return false;
    }if(document.form1.type.value===""){
        window.alert("请输入商品标签！");
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





function checkaddform() {
    if (document.form1.title.value === "") {
        window.alert("请输入商品标题！");
        document.form1.title.focus();
        return false;
    }
    if (document.form1.type.value === "") {
        window.alert("请输入商品标签！");
        document.form1.type.focus();
        return false;
    }if (document.form1.thempic.value == "") {
        window.alert("请输入商品圖片！");
        document.form1.thempic.focus();
        return false;
    }if (document.form1.price.value == "") {
            window.alert("请输入多組圖片！");
            document.form1.price.focus();
            return false;
        }
        if (document.form1.stock.value == "") {
            window.alert("请输入商品库存量！");
            document.form1.stock.focus();
            return false;
        }
        return true;
    }

