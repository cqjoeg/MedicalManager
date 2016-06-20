/**
 * Created by admin on 2016/6/3.
 */
$(function () {
    /*
     *思路大概是先为每一个required添加必填的标记，用each()方法来实现。
     *在each()方法中先是创建一个元素。然后通过append()方法将创建的元素加入到父元素后面。
     *这里面的this用的很精髓，每一次的this都对应着相应的input元素，然后获取相应的父元素。
     *然后为input元素添加失去焦点事件。然后进行用户名、邮件的验证。
     *这里用了一个判断is()，如果是用户名，做相应的处理，如果是邮件做相应的验证。
     *在jQuery框架中，也可以适当的穿插一写原汁原味的javascript代码。比如验证用户名中就有this.value，和this.value.length。对内容进行判断。
     *然后进行的是邮件的验证，貌似用到了正则表达式。
     *然后为input元素添加keyup事件与focus事件。就是在keyup时也要做一下验证，调用blur事件就行了。用triggerHandler()触发器，触发相应的事件。
     *最后提交表单时做统一验证
     *做好整体与细节的处理
     */
    //如果是必填的，则加红星标识.

    $("form :input.required").each(function(){
        var $required = $("<strong class='high'> *</strong>"); //创建元素
        $(this).parent().append($required); //然后将它追加到文档中
    });
    //文本框失去焦点后
    $('form :input').blur(function(){
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();

        //验证邮件
        if( $(this).is('#add-username') ){
            if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
                var errorMsg = '请输入正确的E-Mail地址.';
                $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
            }else{
                var okMsg = '输入正确.';
                $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
            }
        }

        // 验证密码
        if($(this).is('#add-password')){
            if( this.value==""){
                var errorMsg = '请输入密码.';
                $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
            }else{
                var okMsg = '正确.';
                $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
            }
        }

        // 验证 重新输入密码
        if($(this).is('#add-re-password')){
            if( this.value==""){
                var errorMsg = '请重新输入密码.';
                $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
            }else{
                var okMsg = '正确.';
                $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
            }
        }

        if($(this).is('#add-re-password')&& $(this).is('#add-password')){
           if($('#add-re-password').val() == $('#add-password').val()){
               var okMsg = '正确.';
               $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
           }else{
               var errorMsg = '请重新输入密码.';
               $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
           }
        }


    }).keyup(function(){
        $(this).triggerHandler("blur");
    }).focus(function(){
        $(this).triggerHandler("blur");
    });//end blur


    //提交，最终验证。
    $('#send').click(function(){
        $("form :input.required").trigger('blur');
        var numError = $('form .onError').length;
        if(numError){
            alert("请填写所有信息!")
            return false;
        }

       //发送
        $("#add-user").attr("action","/UserMnt_addUser.do"+"tbUser.state");
        $("#add-user").submit();
        // alert("注册成功,密码已发到你的邮箱,请查收.");
    });

    //重置
    $('#res').click(function(){
        $(".formtips").remove();
    });

});