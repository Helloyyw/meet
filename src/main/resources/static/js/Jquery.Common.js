var Common = Common || {};
$.extend(Common, {
    //浏览器时下窗口可视区域高度 
    HeightWindow: $(window).height(),
    //浏览器时下窗口可视区域宽度 
    WidthWindow: $(window).width(), 
    //浏览器判断
    IsIE: $.support.msie != undefined,
    IsIE6: $.support.msie && parseInt($.support.version) === 6,
    IsIE7: $.support.msie && parseInt($.support.version) === 7,
    IsIE8: $.support.msie && parseInt($.support.version) === 8,
    IsIE9: $.support.msie && parseInt($.support.version) === 9,
    IsIE10: $.support.msie && parseInt($.support.version) === 10,

    IsFireFox : $.support.mozilla!= undefined,
    IsSafari:$.support.safari!= undefined,
    IsOpera:$.support.opera!= undefined,
    //生成Guid
    Guid: function() { var guid = 'xxxxxxxa-xxxn-xxxt-xxxu-xxxxxxxxxxxx'.replace(/[xy]/g, function(c){ var r = Math.random() * 16|0, v = c == 'x' ? r : (r&0x3|0x8); return v.toString(16);  }).toUpperCase(); return guid; },
    
    //Cookie操作
    GetCookie: function(name) { var r = new RegExp('(^|;|\\s+)' + name + '=([^;]*)(;|$)'); var m = (document.cookie ? document.cookie : top.document.cookie).match(r); return (!m ? '' : Common.Decode(m[2])); },
    SetCookie: function(name, value, expire, domain, path) {  if(!expire){expire=24; }; var s = name + '=' + Common.Encode(value); if (!Common.IsUndefined(path)) s = s + '; path=' + path; else s = s + '; path=/'; if (expire > 0) { var d = new Date(); d.setTime(d.getTime() + expire * 1000* 3600); if (!Common.IsUndefined(domain)) s = s + '; domain=' + domain; s = s + '; expires=' + d.toGMTString(); } document.cookie = s; },
    RemoveCookie: function(name, domain, path) { var s = name + '='; if (!Common.IsUndefined(domain)) s = s + '; domain=' + domain; if (!Common.IsUndefined(path)) s = s + '; path=' + path; s = s + '; expires=Fri, 02-Jan-1970 00:00:00 GMT'; document.cookie = s; },
    GetAllCookie: function(){
        var allCookie = document.cookie.split(";");
        var result = {};
        for (var i = 0; i < allCookie.length; i++) {
            var allCrumb = allCookie[i].split("=");
            if(allCrumb[0].toString().trim()=='order_list'){continue;}
            // result[allCrumb[0].replace(" ", "")] = allCrumb[1];
            result[allCrumb[0].trim()] = allCrumb[1];
        }
        return result;
    },

    //Ztree操作
    ZTreeCheckClear: function(id, field) { var zTree = $.fn.zTree.getZTreeObj(id); if (zTree) { var nodes = zTree.getCheckedNodes(true); for (var i = 0; i < nodes.length; i++) { var node = zTree.getNodeByParam('id', nodes[i][field]); zTree.checkNode(node, false, true, true); } } },
    ZTreechecked: function(id, field, folder) { var zTree = $.fn.zTree.getZTreeObj(id); var values = []; if (zTree) { var checkedNodes = zTree.getCheckedNodes(true); if (!field || field == "") { field = "id"; } for (var i = 0; i < checkedNodes.length; i++) { if (folder) { values[values.length] = checkedNodes[i][field]; } else if (!checkedNodes[i].children) { values[values.length] = checkedNodes[i][field]; } } } return values },
    //Obj操作
    Clear: function(obj) { $("#" + obj).val("") },
    IsUndefined: function(obj) { return typeof obj == 'undefined'; },
    IsObject: function(obj) { return typeof obj == 'object'; },
    IsNumber: function(obj) { return typeof obj == 'number'; },
    IsString: function(obj) { return typeof obj == 'string'; },
    IsElement: function(obj) { return obj && obj.nodeType == 1; },
    IsFunction: function(obj) { return typeof obj == 'function'; },
    Upper: function(obj) { if(obj){return obj.toLocaleUpperCase();;}else{return "";} },
    Lower: function(obj) { if(obj){ return obj.toLocaleLowerCase();}else{return "";} },

    //判断是否对应数据类型
    IsInt: function(str) { return /^-?\d+$/.test(str); },
    IsFloat: function(str) { return /^(-?\d+)(\.\d+)?$/.test(str); },
    IsIntPositive: function(str) { return /^[0-9]*[1-9][0-9]*$/.test(str); },
    IsFloatPositive: function(str) { return /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test(str); },
    //是否字母
    IsLetter: function(str) { return /^[A-Za-z]+$/.test(str); },
    //是否中文
    IsChinese: function(str) { return /^[\u0391-\uFFE5]+$/.test(str); },
    //是否邮编
    IsZipCode: function(str) { return /^[1-9]\d{5}$/.test(str); },
    //是否Email
    IsEmail: function(str) { return /^[A-Z_a-z0-9-\.]+@([A-Z_a-z0-9-]+\.)+[a-z0-9A-Z]{2,4}$/.test(str); },
    //是否电话
    IsMobile: function(str) { return /^((\(\d{2,3}\))|(\d{3}\-))?((1[35]\d{9})|(18[89]\d{8}))$/.test(str); },
    //判断是否有效的URL
    IsUrl: function(str) { return /^(http:|ftp:)\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/.test(str); },
    //判断是否IP地址
    IsIpAddress: function(str) { return /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/.test(str); },

    //二维码
    
    QRCode:function(val,encoding,level,size)
    {
        val = escape(val);
        encoding = encoding || "Byte";
        level = level || "M";
        size = size || "4";
        var imgpath = "";
        $.ajax({type: "GET",
                async: false,
                data: "cmd=set&val=" + val + "&encoding=" + encoding + "&level=" + level + "&ver=7&size=" + size,
                url:Common.GetRootPath()+ "Model/Handler/QRCode.ashx",
                dataType: 'text',
                beforeSend: function(x) { 
                    x.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                },
                success: function(data) {
                    imgpath= data;
                },
                error: function(request, message, ex) {
                    alert("错误：" + message);
                    imgpath= "";
                }
          });
          return imgpath;
    },
    // 字符转ASCII
    EncodeAscii: function(str) {
	    if (!str) return "";
	    var encodeData = "";
	    for (var idLen = 0; idLen < str.length; idLen++) {
		    encodeData += str[idLen].charCodeAt();
		    if (idLen < str.length - 1) {
			    encodeData += "+";
		    }
	    }
	    encodeData = this.Encode(encodeData);
	    return encodeData;
    },
    // ASCII转字符
    DecodeAscii: function(str) {
	    if (!str) return "";
	    var decodeData = this.Decode(str);
	    var decodeDataArr = decodeData.split('+');
	    if (decodeDataArr.length > 1) {
		    decodeData = "";
		    for (var nameIndex = 0; nameIndex < decodeDataArr.length; nameIndex++) {
			    decodeData += String.fromCharCode(decodeDataArr[nameIndex]);
		    }
	    }
	    return decodeData;
    },
	    //URL转码
    Encode: function(str) { try{ return encodeURIComponent(str)} catch(e){return str}},
    //URL解码
    Decode: function(str) { try{ return decodeURIComponent(str)} catch(e){return str}},
    //加密算法  MD5加密算法不可逆加密算法
    //Encry: function(str, tp) { var vstr = Global.BussinessTools("Encrypt", "EncrypStr", str + "," + tp); return vstr; },
    //解密算法  MD5加密算法 不能解密
    //Decryp: function(str, tp) { var vstr = Global.BussinessTools("Encrypt", "DecrypStr", str + "," + tp); return vstr; },
    //获取角色树
    //GroupTree: function(region, treeid, setting) { var _znodes = Global.BussinessNetOffice("GroupHandle", "RegionGroups", region).toJson(); var zNodes = []; if (_znodes) { $(_znodes).each(function(i, col) { var _c = { id: col.id, parentId: col.pid, name: col.name, regionid: col.region_id, departmentid: col.department_id, sort: col.sort, memo: col.memo, open: false }; zNodes.push(_c); }); } $.fn.zTree.init($("#" + treeid), setting, zNodes); },
    
    //日期计算
    DataToLong:function(d){var vdate = "";if(d){var jdate = new Date(d.replaceAll("-","/"));var y = jdate.getFullYear();var m = jdate.getMonth()+1;var d = jdate.getDate();if(m<10){m = "0" + m;}if(d<10){d = "0" + d;}vdate = y + "-" + m + "-" + d;}return vdate;},
    DataToLongMD:function(d){var vdate = "";if(d){var jdate = new Date(d.replaceAll("-","/")); var m = jdate.getMonth()+1;var d = jdate.getDate();if(m<10){m = "0" + m;}if(d<10){d = "0" + d;}vdate =   m + "-" + d;}return vdate;},
    DataToLongZW:function(d){var vdate = "";if(d){var jdate = new Date(d.replaceAll("-","/"));var y = jdate.getFullYear();var m = jdate.getMonth()+1;var d = jdate.getDate();if(m<10){m = "0" + m;}if(d<10){d = "0" + d;}vdate = y + "年" + m + "月" + d + "日";}return vdate;},
    
    NowDate : function(){ var now = Global.PageChange("DATENOW");return new Date(now);},
    
    Monday : function(){ var currentDate = Common.NowDate(); var week = currentDate.getDay(); var millisecond = 1000*60*60*24; var minusDay = week != 0 ? week-1 : 6; var monday = new Date(currentDate.getTime() - (minusDay*millisecond));return monday},
    Sunday : function(){ var mondaystr = Common.Monday(); var monday = new Date(mondaystr);   var millisecond = 1000*60*60*24; var sunday = new Date(monday.getTime() + (6*millisecond));  return sunday},
    
    MonthBegin : function(){ var currentDate = Common.NowDate();var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear();var firstDay = new Date(currentYear,currentMonth,1);return firstDay},
    MonthEnd : function(day){ var currentDate = Common.NowDate(); if(day&&day!=""){currentDate = day;} var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear();if(currentMonth == 11){ currentYear++; currentMonth = 0; }else{currentMonth++; } ;var millisecond = 1000*60*60*24;var nextMonthDayOne = new Date(currentYear,currentMonth,1); var lastDay = new Date(nextMonthDayOne.getTime() - millisecond); return lastDay},
    
    QuarterBegin : function(){var currentMonth = Common.QuarterSeasonStartMonth();var currentDate = Common.NowDate(); var currentYear = currentDate.getFullYear();  var begin = new Date(currentYear,currentMonth,1);  return begin},
    QuarterEnd : function(){ var begin = Common.QuarterBegin();  var currentMonth = begin.getMonth(); var currentYear = begin.getFullYear(); var day = begin.getDate(); var end =Common.MonthEnd(new Date(currentYear,currentMonth+2,day));return end},
    
    HalfYearBegin:function(){var currentDate = Common.NowDate();var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear();  if(currentMonth>5){return new Date(currentYear,5,1) }else {return new Date(currentYear,0,1) }   },
    HalfYearEnd:function(){var currentDate = Common.NowDate();var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear();  if(currentMonth>5){return new Date(currentYear,11,31) }else {return new Date(currentYear,5,30) }   },
    
    YearBegin:function(){var currentDate = Common.NowDate();var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear(); return new Date(currentYear,0,1)  },
    YearEnd:function(){var currentDate = Common.NowDate();var currentMonth = currentDate.getMonth(); var currentYear = currentDate.getFullYear(); return new Date(currentYear,11,31)  },
    
    QuarterSeasonStartMonth:function(){var currentDate = Common.NowDate();var month = currentDate.getMonth(); var quarterMonthStart = 0; var spring = 0; var summer = 3;var fall = 6;var winter = 9;if(month < 3){ return spring; } if(month < 6){ return summer;  }  if(month < 9){return fall; } return winter;},
    StrData:function(date){var currentMonth = date.getMonth(); var currentYear = date.getFullYear();var currentDay = date.getDate(); return currentYear+"-"+(currentMonth+1)+"-"+currentDay },
    
    //计算工作日结束日期 开始日期+天数
    ComputeWorkEndDate: function(date, n) { var vdate = Global.BussinessHandle("WorkTimeHandle", "Compute_work_end_date", date + "," + n); },
    //计算两日期之间的工作日天数
    ComputeWorkDays: function(begindate, enddate) { var vdate = Global.BussinessHandle("WorkTimeHandle", "Compute_work_days", begindate + "," + enddate); },
    //比较两时间大小
    CompareDatetime: function(begindate, enddate) { var vdate = Global.BussinessHandle("WorkTimeHandle", "DateTime_Compare", begindate + "," + enddate); },
    //获取网站路径不要虚拟目录路径
    GetLocalPath: function() { var strFullPath = window.document.location.href; var strPath = window.document.location.pathname; var pos = strFullPath.indexOf(strPath); var prePath = strFullPath.substring(0, pos); return prePath; },
    //获取网站路径以及虚拟目录
    GetAppPath: function() { var strFullPath = window.document.location.href; var strPath = window.document.location.pathname; var pos = strFullPath.indexOf(strPath); var prePath = strFullPath.substring(0, pos); var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 2); return (prePath + postPath ); },
    //获取网站路径以及虚拟目录 + FrameWork
    GetRootPath: function() { return (Common.GetAppPath() +"FrameWork/"); },
    //获取页面完整路径
    GetPagePath: function() { var root = Common.GetRootPath(); var strFullPath = window.document.location.href; var pagePath = strFullPath.replace(root, ""); return pagePath; },
    //获取页面完整路径 去掉参数
    GetPagePathNoPram: function() {var root = Common.GetRootPath().toLocaleLowerCase(); var strFullPath = window.document.location.href.toLocaleLowerCase();var pagePath = strFullPath.replace(root, "");  var pages = pagePath.split('?');return pages[0]; },
    //json转换为字符串
    Json2String: function(obj) { var isArray = obj instanceof Array; var r = []; for (var i in obj) { var value = obj[i]; if (typeof value == 'string') { value = '"' + value + '"'; } else if (value != null && typeof value == 'object') { value = '"' + value + '"'; } r.push((isArray ? '' : i + ':') + value); } if (isArray) { return '[' + r.join(',') + ']'; } else { return '{' + r.join(',') + '}'; } },
    //json转换为OBject
    Json2Object: function(obj) { var isArray = obj instanceof Array; var r = []; for (var i in obj) { var value = obj[i]; if (typeof value == 'object') { value = '"' + value + '"'; } else if (value != null && typeof value == 'object') { value = '"' + value + '"'; } r.push((isArray ? '' : i + ':') + value); } if (isArray) { return '[' + r.join(',') + ']'; } else { return '{' + r.join(',') + '}'; } },
    JsonAdd: function(jsonname, element, attribute) { var jsonString = Common.Json2String(jsonname); var jsonlink = ",";if(jsonString=="{}"){jsonlink = ""}; jsonString = jsonString.substring(0, jsonString.length - 1); var jsonArr = jsonlink + element + ":" + attribute + "}"; jsonString = jsonString.concat(jsonArr); return jsonString.toJson(); },

    GetAllParam: function(str) {var left = str.substr(0, str.indexOf("}") + 1);var right = str.substr(str.indexOf("}") + 1, str.length);var pramname = left.substr(left.indexOf("{") + 1).replace("}", "") + ",";if (right.indexOf("{") > -1) { pramname += Common.GetAllParam(right);}return pramname;},
    //获取大括号里面的参数并返回结果
    ParamChange: function(str) {var parm = Common.GetAllParam(str).split(",");for (var i = 0; i < parm.length; i++) { if (parm[i] != "") {var val = Global.UserInfo(parm[i]);str = str.replace("{" + parm[i] + "}", "" + val + "");}} return str;},

    //根据URL判断页面是否存在
    //PageExist:function(url){var vmsg = Global.BussinessHandle("PageHandle", "PageIsExist", url);return vmsg;},
    //根据URL判断文件是否存在
    //FileExist:function(url){var pages = url.split('?');url = pages[0]; var vmsg = Global.BussinessTools("Files", "FileIsExists", url);return vmsg;},
    
    //组装控件属性
    ComConnectExt: function(attr,val) { if (val && val != "") { return " "  + attr + val; } else { return ""; } },
    ComConnect: function(val,attr) { if (val && val != "") { return " " + val + attr; } else { return ""; } },
    ComAttr: function(attr, val) { if (val && val != "") { return " " + attr + "=" + "'" + val + "'"; } else { return ""; } },
    ComStyle: function(attr, val,dw) { if(!dw){dw="";} if (val && val != "") { return " " + attr + ":"  + val + dw +";"; } else { return ""; } },
    
    GetTokenUserid:function(){return Global.GetConfig("Map.TokenUserid");},
    //获取用户在ATP中Token
    GetToken:function(uid){ var tokenwebservive = Global.GetConfig("TokenService"); var token = ""; $.ajax({type: "POST",contentType: "application/json",url: tokenwebservive + "/InsertSessionToken",data: '{"userid":"'+ uid +'" }',dataType: 'json',async: false,error: function(message) {alert(message.responseText);},success: function(data) {token = data.d;}});return token; },
    //JS获取后缀名
    Extend: function(val) { var d = /\.[^\.]+$/.exec(val); return d; },
    
    //判断是否都显示有图片，没有就下载图片在显示
    ImgLoad: function() {  var imgs = $("img"); for (var i = 0; i < imgs.length; i++) { var g = imgs[i];var img = new Image();img.src = g.src;if (img.fileSize > 0 || (img.width > 0 && img.height > 0)) {} else {var path = img.src; var newPath = Global.BussinessHandle("CopyImgHandle", "GetNewPaths", path);g.src = newPath;}}},
    
    //加载 
    LoadFlag:"0",     
    Loading:function(bool){if (bool) { $(".loadings").show();Common.LoadFlag = "1";} else { setInterval(Common.LoadingHide, 1000);} },
    LoadingHide:function(){if (Common.LoadFlag == "1"){$(".loadings").hide(); Common.LoadFlag = "0"; }},
    
    //JsonSelect Json 查询方法
    query: function(val, exp) { if (Common.left(exp, 1) != "@") { exp = "Common." + exp }; if (!exp) { return []; } var fn = Common.cache[exp]; try { if (!fn) { var code = Common.interpret(exp); code = Common.templ.replace("$C", code); fn = Common.cache[exp] = Common.complite(code); } return fn(val); } catch (e) { return []; } },

    //缓存,解决快速查找
    cache: {},
    rQuote: /""/g,
    rQuoteTemp: /!~/g,
    //扩展运算符
    alias: [/@/g, "_e.", /AND/gi, "&&", /OR/gi, "||", /<>/g, "!=", /NOT/gi, "!", /([^=<>])=([^=]|$)/g, '$1==$2'],
    // 定义常用的函数
    len: function(s) { return s.length; },
    left: function(s, n) { return s.substr(0, n); },
    right: function(s, n) { return s.substr(-n); },
    index: function(s, find) { return s.indexOf(find) + 1; },
    // 定义模函数
    templ: function(_list) { var _ret = []; var _i = -1; for (var _k in _list) { var _e = _list[_k]; if (_e != Object.prototype[_k]) { if ($C) { _ret[++_i] = _e; } } } return _ret; } .toString(),
    // 编译
    complite: function(code) { return eval("0," + code); },
    // 将扩展符号转换成标准的JS符号
    interpret: function(exp) { exp = exp.replace(Common.rQuote, "!~"); var arr = exp.split('"'); var i, n = arr.length; var k = Common.alias.length; for (var i = 0; i < n; i += 2) { var s = arr[i]; for (var j = 0; j < k; j += 2) { if (Common.index(s, Common.alias[j]) > -1) { s = s.replace(Common.alias[j], Common.alias[j + 1]); } } arr[i] = s; } for (var i = 1; i < n; i += 2) { arr[i] = arr[i].replace(Common.rQuoteTemp, '\\"'); } return arr.join('"'); },

    //引用js、css
    include: function(file) { var files = typeof file == "string" ? [file] : file; for (var i = 0; i < files.length; i++) { var name = files[i].replace(/^\s|\s$/g, ""); if(Common.FileExist(name)=="0"){name="../"+name;}; var att = name.split('.'); var ext = att[att.length - 1].toLowerCase(); var isCSS = ext == "css"; var tag = isCSS ? "link" : "script"; var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' "; var link = (isCSS ? "href" : "src") + "='" + Common.GetRootPath() + name + "'"; if ($(tag + "[" + link + "]").length == 0) { $("head").append("<" + tag + attr + link + "></" + tag + ">"); } } }

});

 

//Json查询例子
/*

var doTest1 = function() {

var heros = [
{ name: '冰室女巫', DP: 38, AP: 1.3, Str: 16, Dex: 16, Int: 21 },
{ name: '沉默术士', DP: 39, AP: 1.1, Str: 17, Dex: 16, Int: 21 },
{ name: '娜迦海妖', DP: 51, AP: 6.0, Str: 21, Dex: 21, Int: 18 },
{ name: '赏金猎人', DP: 39, AP: 4.0, Str: 17, Dex: 21, Int: 16 },
{ name: '剧毒术士', DP: 45, AP: 3.1, Str: 18, Dex: 22, Int: 15 },
{ name: '光之守卫', DP: 38, AP: 1.1, Str: 16, Dex: 15, Int: 22 },
{ name: '炼金术士', DP: 49, AP: 0.6, Str: 25, Dex: 11, Int: 25 }

];

var match = Common.query(heros, '@Str=21 AND @Dex=21');
ShowResult(match);


// 查询：“士”结尾的
// 结果：沉默术士,剧毒术士,炼金术士
var match = Common.query(heros, 'JsonSel.right(@name,1)="士"');
ShowResult(match);
}

function ShowResult(result) {
for (var i = 0; i < result.length; i++) {
alert(result[i].name + " " + result[i].DP + " " + result[i].AP + " " + result[i].Str + " " + result[i].Dex + " " + result[i].Int);
}
}


*/

(function($) {
    $.cookie = function(key, value, options) {

        // key and at least value given, set cookie...
        if (arguments.length > 1 && (!/Object/.test(Object.prototype.toString.call(value)) || value === null || value === undefined)) {
            options = $.extend({}, options);

            if (value === null || value === undefined) {
                options.expires = -1;
            }

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setDate(t.getDate() + days);
            }

            value = String(value);

            return (document.cookie = [
                encodeURIComponent(key), '=', options.raw ? value : encodeURIComponent(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path    ? '; path=' + options.path : '',
                options.domain  ? '; domain=' + options.domain : '',
                options.secure  ? '; secure' : ''
            ].join(''));
        }

        // key and possibly options given, get cookie...
        options = value || {};
        var decode = options.raw ? function(s) { return s; } : decodeURIComponent;

        var pairs = document.cookie.split('; ');
        for (var i = 0, pair; pair = pairs[i] && pairs[i].split('='); i++) {
            if (decode(pair[0]) === key) return decode(pair[1] || ''); // IE saves cookies with empty string as "c; ", e.g. without "=" as opposed to EOMB, thus pair[1] may be undefined
        }
        return null;
    };
})(jQuery);

//(function($){$.chromatable={defaults:{width:"99%",height:"400px",scrolling:"yes"}};$.fn.chromatable=function(options){var options=$.extend({},$.chromatable.defaults,options);return this.each(function(){var $this=$(this);var $uniqueID=$(this).attr("ID")+("wrapper");$(this).css('width',options.width).addClass("_scrolling");$(this).wrap('<div class="scrolling_outer"><div id="'+$uniqueID+'" class="scrolling_inner"></div></div>');$(".scrolling_outer").css({'position':'relative'});$("#"+$uniqueID).css({'border':'0px solid #CCCCCC','overflow-x':'hidden','overflow-y':'auto','padding-right':'17px'});$("#"+$uniqueID).css('height',options.height);$("#"+$uniqueID).css('width',options.width);$(this).before($(this).clone().attr("id","").addClass("_thead").css({'width':'auto','display':'block','position':'absolute','border':'none','border-bottom':'1px solid #CCC','top':'1px'}));$('._thead').children('tbody').remove();$(this).each(function($this){if(options.width=="100%"||options.width=="auto"){$("#"+$uniqueID).css({'padding-right':'0px'})}if(options.scrolling=="no"){$("#"+$uniqueID).before('<a href="#" class="expander" style="width:100%;">展开表格</a>');$("#"+$uniqueID).css({'padding-right':'0px'});$(".expander").each(function(int){$(this).attr("ID",int);$(this).bind("click",function(){$("#"+$uniqueID).css({'height':'auto'});$("#"+$uniqueID+" ._thead").remove();$(this).remove()})});$("#"+$uniqueID).resizable({handles:'s'}).css("overflow-y","hidden")}});$curr=$this.prev();$("thead:eq(0)>tr th",this).each(function(i){$("thead:eq(0)>tr th:eq("+i+")",$curr).width($(this).width())});if(options.width=="100%"||"auto"){$(window).resize(function(){resizer($this)})}})};function resizer($this){$curr=$this.prev();$("thead:eq(0)>tr th",$this).each(function(i){$("thead:eq(0)>tr th:eq("+i+")",$curr).width($(this).width())})}})(jQuery);


/*
 * File:        chromatable.js
 * Version:     1.3.0
 * CVS:         $Id$
 * Description: Make a "sticky" header at the top of the table, so it stays put while the table scrolls
 * Author:      Zachary Siswick
 * Created:     Thursday 19 November 2009 8:53pm 
 * Language:    Javascript
 *
 */
(function($){
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, //month
            "d+": this.getDate(),    //day
            "h+": this.getHours(),   //hour
            "m+": this.getMinutes(), //minute
            "s+": this.getSeconds(), //second
            "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
            "S": this.getMilliseconds() //millisecond
        }
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o) if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
            ("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }
    $.chromatable = {
        // Default options
        defaults: {
						//specify a pixel dimension, auto, or 100%
            width: "900px", 
						height: "300px",
						scrolling: "yes" 
        }
				
		};
		
		$.fn.chromatable = function(options){
		 
		// Extend default options
		var options = $.extend({}, $.chromatable.defaults, options);

		return this.each(function(){
															
				// Add jQuery methods to the element
				var $this = $(this);
				var $uniqueID = $(this).attr("ID") + ("wrapper");
				
				
				//Add dimentsions from user or default parameters to the DOM elements
				$(this).css('width', options.width).addClass("_scrolling");
				 
				$(this).wrap('<div class="scrolling_outer"><div id="'+$uniqueID+'" class="scrolling_inner"></div></div>');
								
				$(".scrolling_outer").css({'position':'relative'});
				$("#"+$uniqueID).css(
																	
					 {'border':'0px solid #CCCCCC',
						'overflow-x':'hidden',
						'overflow-y':'auto',
						'padding-right':'17px'						
						});
				
				$("#"+$uniqueID).css('height', options.height);
				$("#"+$uniqueID).css('width', options.width);
				
				// clone an exact copy of the scrolling table and add to DOM before the original table
				// replace old class with new to differentiate between the two
				$(this).before($(this).clone().attr("id", "").addClass("_thead").css(
																																															 
						{'width' : 'auto',
						 'display' : 'block', 
						 'position':'absolute', 
						 'border':'none', 
						 'border-bottom':'0px solid #CCC',
						 'top':'0px',
						 'z-index':'699'
							}));
	
				
				// remove all children within the cloned table after the thead element
				$('._thead').children('tbody').remove();
				$('._thead').children('tfoot').remove();
				
				$(this).each(function( $this ){
															
					// if the width is auto, we need to remove padding-right on scrolling container	
					
					if (options.width == "100%" || options.width == "auto") {
						
						$("#"+$uniqueID).css({'padding-right':'0px'});
					}
					
				
					if (options.scrolling == "no") {
												
						$("#"+$uniqueID).before('<a href="#" class="expander" style="width:100%;">展开表格</a>');
						
						$("#"+$uniqueID).css({'padding-right':'0px'});
						
						$(".expander").each(
	
							
							function(int){
								
								$(this).attr("ID", int);
								
								$( this ).bind ("click",function(){
																								 
										$("#"+$uniqueID).css({'height':'auto'});
										
										$("#"+$uniqueID+" ._thead").remove();
										
										$(this).remove();
						
									});
								});


						//this is dependant on the jQuery resizable UI plugin
						$("#"+$uniqueID).resizable({ handles: 's' }).css("overflow-y", "hidden");
	
					}
				
				});
				
				
				// Get a relative reference to the "sticky header"
				$curr = $this.prev();
				
				// Copy the cell widths across from the original table
				var thindex = 0;
				var trindex = 1;
				var trlen = $("thead:eq(0)>tr").length;
				
				if(window.navigator.userAgent.indexOf("Firefox")==1)
				{
				    $("thead:eq(0)>tr",this).each( function (i) {	
				        var thlen = $(this).find("th").length;	
    				     	    
				        $("th", this).each(function(j){				    
				             if(trlen == trindex)
				             {
                                if(trindex > 1 && j==0 )
                                {
                                    $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()+0.5);
                                }
                                else
                                {
                                   
                                    if(thlen/2<j)
                                    {
                                        $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()-0.5);
                                    }
                                    else
                                    {
                                        $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()-0.1);
                                    }
                                }
                                
				             }
				             else
				             {
				                if(thlen/2<j)
				                { 
				                    if(j == thlen-1)
				                    {
				                         $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()-0.5);
				                    }
				                    else
				                    {				                    
				                        $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()+0.5);
				                    }
				                }
				                else
				                {
				                    $("thead:eq(0)>tr th:eq("+thindex+")", $curr).width( $(this).width()-0.5);
				                }				            
				             }
				             thindex++;
				        });	
				        trindex++;
				    });
                }
                else 
                { 
                     resizer($this);
                } 
               
				if (options.width == "100%" || "auto"){
						$(window).resize(function(){
					    	var istablelist = $("[istablelist='1']");
					    	for(var i=0;i<	istablelist.length;i++)
					    	{
					    	    resizer($(istablelist[i]));
					    	}								
						});
					}
				}); 
				
				
				 
            };
            
		
		// private function to temporarily hide the header when the browser is resized
		
		function resizer($this) {		     
			var id = $($this).parents("[istablelist='1']").attr("id");		
			if(!id)
			{
			    id = $($this).attr("id");
			    $this = $("#"+id+"_tid");
			}	
		    if(id)
		    {
		        var removeHeight = 10;
		        var topbarshow = $("#" + id +"_topbar").is(":hidden");
		        var top = $("[forid='"+ id +"']").css("top") || "0";
		        var isstat = $("[forid='"+ id +"']").attr("isstat") || "0";
		        var istotal = $("[forid='"+ id +"']").attr("istotal") || "0";
		        if(isstat=="0")
		        {						            
		            removeHeight = Number( $("#" + id).find(".l-panel-bar").height());	 
		            removeHeight += Number( $("#"+ id ).find("._thead").height())-18;
		        }
		        if(istotal=="1")
		        {
		            
		            removeHeight += 27;
		        }
		        
		        if(!topbarshow)
		        {
		            
		            removeHeight += Number( $("#" + id +"_topbar").height()); 
		        } 
		        var oldlistheight =$("#" + id).data("oldlistheight");
		        if(oldlistheight=="auto")
		        {		             
		            var listheight =  document.body.clientHeight - removeHeight - Number(top.replace("px",""))-5; 
		            
		            $("#"+ id +"_tidwrapper").css("height",listheight);
		        }
		    }	
					    
			// Need a relative reference to the "sticky header"
			$curr = $this.prev();
			if($curr.length>0)
			{		
			    
			    $("thead:eq(0)>tr th", $this).each( function (i) {																														 
				    $("thead:eq(0)>tr th:eq("+i+")", $curr).width( $(this).width());					
			    });
			}
  	};
		
})(jQuery);