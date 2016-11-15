/**
 * Created by liuchenfei on 2016/11/14.
 */

$(function(){
    $('#deviceName').textbox({
        height:30
    });
    $('#userName').textbox({
        height:30
    });
    $('#bt_submit').linkbutton({
        iconCls:'icon-ok'
    });
    $('#bt_viewData').linkbutton({
        iconCls:'icon-search'
    });
});
function test(){
    $.messager.progress({
        title:'提示',
        msg:'正在计算指纹信息'
    });
    new Fingerprint2().get(function(result, components){
        var features=new Object();
        features.video=checkVideo();
        features.cookie_enabled=window.navigator.cookieEnabled;
        //http-accept 可以使用browserspy.dk得到
        //supercookies
        //fonts  可以使用browserspy.dk得到
        $.ajax({
            type:'post',
            url:'/fingerprint/getfingerprint',
            data:{userName:$('#userName').val(),deviceName:$('#deviceName').val(),data:JSON.stringify(components),fingerprint:result,features:JSON.stringify(features)},
            success:function(r){
                $('#fingerprint').text('您的浏览器指纹是:'+result);
                $.messager.progress('close');
                alert(r);
            },
            error:function(){
                $.messager.progress('close');
                alert('请求失败');
            }
        });
    });
}


//检测是否支持video标签
function checkVideo() {
    if (!!document.createElement('video').canPlayType) {
        var vidTest = document.createElement("video");
        oggTest = vidTest.canPlayType('video/ogg; codecs="theora, vorbis"');
        if (!oggTest) {
            h264Test = vidTest.canPlayType('video/mp4; codecs="avc1.42E01E, mp4a.40.2"');
            if (!h264Test) {
                return false;
            }
            else {
                if (h264Test == "probably") {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            if (oggTest == "probably") {
                return true;
            }
            else {
                return false;
            }
        }
    }
    else {
        return false;
    }
}