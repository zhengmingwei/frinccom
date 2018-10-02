/**
 * Created by wjn on 16/4/22.
 */
export default class util {
    focus(obj) {
        if (typeof obj == 'string')
            obj = document.getElementById(obj);
        obj.focus();
        if (obj.createTextRange) {
            var rtextRange = obj.createTextRange();
            rtextRange.moveStart('character', obj.value.length);
            rtextRange.collapse(true);
            rtextRange.select();
        }
        else if (obj.setSelectionRange) {
            obj.selectionStart = obj.value.length;
        }
    }

    getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = decodeURI(window.location.search).substr(1).match(reg);
        if (r != null) return (r[2]);
        return null;
    }

    /**
     * 等比例缩放
     * @param w0 容器宽度
     * @param h0 容器高度
     * @param w1 图片宽度
     * @param h1 图片高度
     */
     imgResize(maxWidth, maxHeight,imgWidth,imgHeight) {
        var ratio = 1;
        var wRatio = maxWidth / imgWidth;
        var hRatio = maxHeight / imgHeight;

        if (maxWidth ==0 && maxHeight==0){
            ratio = 1;
        }else if (maxWidth==0){//
            if (hRatio<1) ratio = hRatio;
        }else if (maxHeight==0){
            if (wRatio<1) ratio = wRatio;
        }else if (wRatio<1 || hRatio<1){
            ratio = (wRatio<=hRatio?wRatio:hRatio);
        }
        if (ratio<1){
            imgWidth = Math.round(imgWidth * ratio);
            imgHeight = Math.round(imgHeight * ratio);
        }
        return {x:0,y:0,width:imgWidth,height:imgHeight};
    }
    /**
     * 等比例截取
     * @param w0 容器宽度
     * @param h0 容器高度
     * @param w1 图片宽度
     * @param h1 图片高度
     */
     imgZoom(maxWidth, maxHeight,imgWidth,imgHeight) {
        var ratio = imgWidth/imgHeight;
        var x=0;
        var y=0;
        if(ratio>1){
            imgHeight=maxHeight;
            imgWidth = Math.round(imgHeight * ratio);
            x=-(imgWidth-maxWidth)/2
        }else{
            imgWidth = maxWidth;
            imgHeight=Math.round(imgWidth / ratio);
            y=-(imgHeight-maxHeight)/2

        }
        return {left:x,top:y,width:imgWidth,height:imgHeight,position:"relative"};
    }

    /**
     * 获取当前日期
     * @returns {string}
     */
    getNowFormatDate() {
        var day = new Date();
        var Year = 0;
        var Month = 0;
        var Day = 0;
        var CurrentDate = "";
        Year = day.getFullYear();
        Month = day.getMonth() + 1;
        Day = day.getDate();

        CurrentDate += Year + "-";
        if (Month >= 10) {
            CurrentDate += Month + "-";
        }
        else {
            CurrentDate += "0" + Month + "-";
        }
        if (Day >= 10) {
            CurrentDate += Day;
        }
        else {
            CurrentDate += "0" + Day;
        }
        return CurrentDate;
    }

    /**
     * 获取当前日期
     * @returns {string}
     */
    formatDate(date,fmt) {
        if(!date){
            date=new Date();
        }
        if(!fmt){
            fmt="yyyy-MM-dd"
        }
        var o = {
            "M+" : date.getMonth()+1, //月份
            "d+" : date.getDate(), //日
            "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时
            "H+" : date.getHours(), //小时
            "m+" : date.getMinutes(), //分
            "s+" : date.getSeconds(), //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S" : date.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[date.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
}