function doUpload() {

    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var ff = basePath+"/file/upload_r/1/1";

    var files = $("#imageFile").get(0).files[0]; //获取file控件中的内容

    var fd = new FormData();
    fd.append("errPic", files);


    $.ajax({
        //url: '<%=basePath%>UploadImage' ,  /*这是处理文件上传的servlet*/
        url: ff ,  /*这是处理文件上传的servlet*/
        type: 'POST',
        data: fd,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            //alert(returndata.result.url); businessLicense
            document.getElementById("businessLicense").value=JSON.stringify(returndata.result);
            //document.getElementById("businessLicense1").value=JSON.stringify(returndata.result);
            
            document.getElementById("upimg").src=returndata.result.url;
            //document.getElementById("showpic").src="<%=basePath%>UploadImage?picture=showpic";/*这是预览图片用的，自己在文件上传表单外添加*/
        },
        error: function (returndata) {
            alert(34);
            alert(returndata);
        }
    });
}



function doUpload1() {
    document.getElementById("imageFile").click();
}

function getPicCode() {
    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var url = basePath+"/view/picCheckCode?" + Math.random();
    document.getElementById("pic").src=url;
}
getPicCode();

var loginForm = document.getElementById("loginForm");
function login() {
    loginForm.submit();
}
function reset() {
    loginForm.reset();
}

function chackErr() {
    var myErr = GetQueryString("err");
    if (myErr != null  && myErr.toString().length > 0) {
        //alert(myErr.toString());
        if(myErr == "1"){
            var errText = "<span style=\"color: red\">用户名或密码错误！</span>";
        } else {
            var errText = "<span style=\"color: red\">验证码错误！</span>";
        }
        document.getElementById("loginErr").innerHTML = errText;
    }
}
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}

$("#captcha").keydown(function (e) {
    if (e.keyCode == 13) {
        login();
    }
});
chackErr();
function getPicCode1234() {
    url = "picCheckCode?" + Math.random();
    //alert(url);
    $("#pic").attr("src", url);
}

//邮箱 验证
function checkEmail() {
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    var obj = document.getElementById("mail"); //要验证的对象

    document.getElementById('msgEmail').innerHTML='';
    if(obj.value === ""){ //输入不能为空
        //alert("输入不能为空!");
        return false;
    }else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
        //alert("邮箱格式验证不通过!");
        document.getElementById('msgEmail').innerHTML='邮箱格式验证不通过';
        return false;
    }else{
        //alert("通过！");
        return true;
    }
}
//cloneEmail //邮箱 验证 清空
function cloneEmail() {
    document.getElementById('msgEmail').innerHTML='';
}

//验证联系电话
function checkMobile(str) {
    str =  document.getElementById("phone").value;
    var rl = "";
    if(str!=''){
        var re = /^1\d{10}$/; //手机验证
        if (re.test(str)) {
            //alert("手机正确");
        } else {
            rl = '手机验证不通过';
        }
        if(rl.length>0){
            var rer = /^0\d{2,3}-?\d{7,8}$/;//座机验证
            if(rer.test(str)){
                //alert("座机正确");
                rl = "";
            }else{
                rl = '联系电话验证不通过';
            }
        }
        if(rl.length>0){
            document.getElementById('msgPhone').innerHTML=rl;
        }
    }
}

//cloneEmail //邮箱 验证 清空
function cloneMobile() {
    document.getElementById('msgPhone').innerHTML='';
}


//验证密码
function checkPass(str) {
    document.getElementById('msgPass').innerHTML='';
    var password =  document.getElementById("password").value;
    var password2 =  document.getElementById("password2").value;
    var rl = "";
    if(password!=password2){
            document.getElementById('msgPass').innerHTML="两次密码不一致";
    }
}

//cloneEmail //密码 验证 清空
function clonePass() {
    document.getElementById('msgPass').innerHTML='';
}

//验证用户
function checkName(){

    document.getElementById('msgName').innerHTML='';
    var name =  document.getElementById("name").value;

    //alert(name);
    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var ff = basePath+"/manager/user/getUserByName?name="+name;

    //alert(ff);

    var fd = new FormData();
    fd.append("name", name);


    $.ajax({
        //url: '<%=basePath%>UploadImage' ,  /*这是处理文件上传的servlet*/
        url: ff ,  /*这是处理文件上传的servlet*/
        type: 'POST',
        data: fd,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            //alert(returndata.returnCode);
             var code = returndata.returnCode;
             if( returndata.returnCode=="500"){
                 document.getElementById('msgName').innerHTML="该用户已被使用";
             }
            //document.getElementById("upimg").src=returndata.result.url;
            //document.getElementById("showpic").src="<%=basePath%>UploadImage?picture=showpic";/*这是预览图片用的，自己在文件上传表单外添加*/
        },
        error: function (returndata) {
            document.getElementById('msgName').innerHTML="查询用户失败";
            //alert(returndata);
        }
    });

}

//cloneEmail //用户 验证 清空
function cloneName() {
    document.getElementById('msgName').innerHTML='';
}

//clonePicCode


//cloneEmail //验证码 清空
function clonePicCode() {
    document.getElementById('msgPicCode').innerHTML='';
}



