     //script를 이용해서 checkbox를 하나이상 선택하도록 한다
        $(function(){
        $("#frm").submit(function(){
            //이름이 "memDels"인 체크박스 중에서 선택된 체크박스의 개수를 나타냅니다.
            //선택된 체크박스의 개수가 1보다 작으면 (즉, 선택된 체크박스가 하나도 없으면) 조건이 참이 되어, 알림 메시지를 표시하고 제출을 막는다
            if($("input:checkbox[name=memDels]:checked").length < 1 ){
                alert("하나이상 선택하세요."); //1이하면 true이기 때문에
                return false; //현재 페이지에 머무르게 하기 위해사용
            }
        });

        // checkBoxs를 선택을 하면 모두 선택이 되게 한다.
        $("#checkBoxs").click(function(){
        if($("#checkBoxs").prop("checked")){
        //이름이 "memDels"인 모든 체크박스를 선택하여 그들의 checked 속성을 true(선택됨)로 설정
        $("input:checkbox[name=memDels]").prop("checked",true);
    }else{
        //이름이 "memDels"인 모든 체크박스를 선택하여 그들의 checked 속성을 false(선택되지 않음)로 설정
        $("input:checkbox[name=memDels]").prop("checked",false);
    }
    });



        $("input:checkbox[name=memDels]").click(function(){
        //먼저 체크박스의 개수를 가지고 온다.
        var tot = $("input:checkbox[name=memDels]").length;
        // 체크된 체크박스의 개수를 가지고 온다.
        var checked = $("input:checkbox[name=memDels]:checked").length;
        //두개를 비교한다.
        if(tot == checked){
        // 같으면 checkBoxs에 체크를한다.
        $("#checkBoxs").prop("checked",true);
    }else{
        $("#checkBoxs").prop("checked",false);
    }
    });

    });

     function del(id){
         location.href="memberDel?id="+id;
     }
