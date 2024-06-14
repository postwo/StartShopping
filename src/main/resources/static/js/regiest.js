$(function(){

    $("#memberEmail").on("change keyup",function(){
        $.ajax({
            type:'post',
            url:"/checkRest/userEmailCheck",
            data:{"userEmail":$("#memberEmail").val()},
            dataType:'text',
            success:function(result){
                console.log("들어왔어요");
                $("#emailCheck").text(result);
                if(result.trim() === "사용가능한 Email입니다."){
                    $("#emailCheck").css("color","blue");
                }else{
                    $("#emailCheck").css("color","red");
                }
            },
            error:function(){

            }
        });
    });

    // 전송시 사용할 수 없는 이메일인경우 전송하지 못하게 차단
    $("#frm").submit(function(){

        if($("#emailCheck").text()==="" || $("emailCheck").text()==="사용중인 Email입니다."){
            alert("이메일 중복확인을 해주세요");
            return false;
        }
    });
});