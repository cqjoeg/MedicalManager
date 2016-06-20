$(document).ready(function() {
    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });


    $("#send").click(function () {
        var username = $(".am-form").find("#add-username").val();
        var password = $(".am-form").find("#add-password").val();
        var re_password = $(".am-form").find("#add-re-password").val();
        var options = $("#wrap").find('input[name="tbUser.state"]');
        var state = $("#wrap").find('input[name="tbUser.state"]:checked ').val();


        if( $("#add-password").val() != $("#add-re-password").val()) {
            alert("两次密码要相同！");
            return false;
        }

        if(state != 0 && state != 1){
            alert("请选择账号状态！");
            return false;
        }
        //发送
        $("#add-user").attr("action","/users/UserMnt_addUser.do");
        $("#add-user").submit();

    });


});
