/**
 * Created by wjn on 16/6/13.
 */



var server = "../assets/";

//var pubs = ['godatrip.com','www.godatrip.com','101.201.221.16','60.205.92.31'];

//var isPubHost = function(hostname) {
//    for(var i in pubs) {
//        if(pubs[i]===hostname) return true;
//    }
//    return false;
//}

//if(isPubHost(location.hostname)){
//    server="../assets/";
//}


var devList = ['localhost', '127.0.0.1'];

var isDevHost = function (hostname) {
    for (var i in devList) {
        if (devList[i] === hostname.toLowerCase()) return true;
    }
    return false;
}

if (isDevHost(location.hostname)) {
    server = "http://127.0.0.1:9090/assets/";
}

function getBrowserVersion() {
    var browser = {
        versions: function () {
            var u = navigator.userAgent, app = navigator.appVersion;
            return {//移动终端浏览器版本信息
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1,//是否web应该程序，没有头部与底部
                google: u.indexOf('Chrome') > -1,
                version: app
            };
        }(),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    };
    if (browser.versions.trident) {
        if (browser.versions.version.indexOf("MSIE 6.0") > -1) {
            return false;
        } else if (browser.versions.version.indexOf("MSIE 7.0") > -1) {
            return false;
        } else if (browser.versions.version.indexOf("MSIE 8.0") > -1) {
            return false;
        } else if (browser.versions.version.indexOf("MSIE 9.0") > -1) {
            return false;
        } else if (browser.versions.version.indexOf("MSIE 10.0") > -1) {
            return true;
        } else if (browser.versions.version.indexOf("rv:11.0") > -1) {
            return true;
        }
    }
    return true;
}

function browsrcheck(bundle) {
    if (!getBrowserVersion()) {
        location.href = "../view/nonsupport.html";
    } else {
        var oHead = document.getElementsByTagName('BODY').item(0);

        var oScript = document.createElement("script");

        oScript.type = "text/javascript";

        oScript.src = server + bundle;
        oHead.appendChild(oScript);
        //document.write('<script src="http://192.168.1.130:9090/assets/welcome.bundle.js"/>');
    }
}
