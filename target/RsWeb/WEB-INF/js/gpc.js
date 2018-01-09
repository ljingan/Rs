Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//var time1 = new Date().Format("yyyy-MM-dd");
//var time2 = new Date().Format("yyyy-MM-dd hh:mm:ss");

var gpc;
(function (gpc) {
    gpc.formatDate =  function(date) {
        return date.Format("yyyy-MM-dd hh:mm:ss");
    }

    gpc.parseDate =   function(dateStr) {
        var regexDT = /(\d{4})-?(\d{2})?-?(\d{2})?\s?(\d{2})?:?(\d{2})?:?(\d{2})?/g;
        var matchs = regexDT.exec(dateStr);
        if(!matchs) return new Date();
        var date = new Array();
        for (var i = 1; i < matchs.length; i++) {
            if (matchs[i]!=undefined) {
                date[i] = matchs[i];
            } else {
                if (i<=3) {
                    date[i] = '01';
                } else {
                    date[i] = '00';
                }
            }
        }
        return new Date(date[1], date[2]-1, date[3], date[4], date[5],date[6]);
    }

    gpc.parseDate2 = function(str) {
        if (!str) return Date.parse(new Date())/1000;
        var y = str.substring(0, 4);
        var m = str.substring(5, 7);
        var d = str.substring(8, 10);
        var h = str.substring(11, 14);
        var min = str.substring(15, 17);
        var sec = str.substring(18, 20);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)) {
            return  Date.parse(new Date(y, m - 1, d, h, min, sec))/1000;
        } else {
            return  Date.parse(new Date())/1000;
        }
    }

    gpc.unix2DateStr = function(unix){
        var date = new Date(parseInt(unix) * 1000);
        return gpc.formatDate(date);
    }

    gpc.dateStr2unix = function(dateStr){
        var date = gpc.parseDate(dateStr)
        return Date.parse(date) / 1000;
    }



})(gpc || (gpc = {}));

