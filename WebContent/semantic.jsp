<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semantic.jsp</title>
<style>
/*div.header{border:1px solid;}*/
/*div.footer{background-color:gray;}*/
header {border:1px solid;width:400px; height: 50px;}
nav>ul {list-style: none; padding-left: 0px;}
nav>ul>li { display: inline-block; }
nav>ul>li>a {text-decoration: none;}
nav>ul>li>a:hover{background-color: yellow;}

footer {background-color:gray;
        position: absolute;
        bottom: 0;
        /* top:50px; */
        left:0;
        right:0;   
        text-align: center;
}
.div1{ 
      width: 70%;
      background: silver;
      text-align: center;
      padding:10px;
}
.div1>table{      
 	  width: 50%; 	        
      text-align: center;
}
.div1>table, .div1>table td, .div2>table, .div2>table td{
     border-collapse: collapse;
     border: 1px solid;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	//dom트리에서 nav>ul>li>a객체찾기
	var $menuArr = $("nav>ul>li>a");
	$menuArr.click(function(){//(e){
		var href = $(this).attr("href");
		var $section = $("section");
		//section영역지우기
		$section.empty();//$section.html('');
		
		//ajax기술로  href변수값에 해당 URL요청		
		$.ajax({
			url: href,
			method: 'post',
			success:function(result){
				if(href=='productlist.do'){
					//$section.html(result);	
				
				  var arr = JSON.parse(result.trim());
				  var data = '<div class="div1">';
				  data += '<h3>상품목록</h3>';
				  data += '<table>';
				  data += '<tr><td>상품번호</td><td>상품명</td><td>가격</td></tr>'
				  
				  for(var i=0; i<arr.length; i++){
					  var p = arr[i];
				  
					  var prod_no = p.no;
					  var prod_name = p.name;
					  var prod_price = p.price;
					  data += '<tr>';
					  data += '<td>' + prod_no+'</td>';
					  data += '<td>' + prod_name+'</td>';
					  data += '<td>' + prod_price+'</td>';
					  data += '</tr>';
				  }
				  
				  data += '</table>';
				  $section.html(data);
				/* }else{			
					$section.html(result);
				} */
				}else if(href=='viewcart.do'){
				  var arr = JSON.parse(result.trim());
				  var data = '<div class="div2" >';
				  data += '<h3>장바구니 내용</h3>';
				  data += '<table>';
				  data += '<tr><td>상품번호</td><td>상품명</td><td>가격</td><td>수량</td></tr>'
				  for(var i=0; i<arr.length; i++){
					  var p = arr[i];
				  
					  var prod_no = p.no;
					  var prod_name = p.name;
					  var prod_price = p.price;
					  var cnt = p.cnt;
					  data += '<tr>';
					  data += '<td>' + prod_no+'</td>';
					  data += '<td>' + prod_name+'</td>';
					  data += '<td>' + prod_price+'</td>';
					  data += '<td>' + cnt+'</td>';
					  data += '</tr>';
				  }
				  data += '<tr><td colspan="4">';
				  data += '<button>주문하기</button>';
				  data += '</td></tr>';
				  
				  data += '</table>';
				  $section.html(data);
				}else if(href=="logout.do"){
					alert(result);
					var obj = JSON.parse(result.trim());
					if(obj.status == 1){
						//alert("")
					}
					location.href = "semantic.jsp";
				}else{			
					$section.html(result);
				}
			}
		});
		return false;
	});
	//$("section>table>tbody>tr").click(function(){});
	$("section").on("click", "div.div1>table>tbody>tr", function(){
		var url = "productdetail.do";
		var tdObj = $(this).children().first(); //첫번째 td객체
		var no = tdObj.html().trim();
		console.log(no);
		$.ajax({			
			url : url,
			method: 'get',
			data: "no=" + no,
			success:function(result){
				var obj = JSON.parse(result.trim());
				console.log(obj);
				$("section").empty();
				var divObj = $("<div>"); //DOM에 추가될 DIV객체생성
				//$(divObj).css("border", "1px solid");
				$(divObj).addClass("div1");
				var h3Obj = $("<h3>");
				h3Obj.html("상품상세정보");
				divObj.append(h3Obj);
				
				var tableObj = $("<table>");
				var trObj = $("<tr>");
				
				var tdObj = $("<td>");				
				tdObj.html("상품번호");
				trObj.append(tdObj);				
				tdObj = $("<td>");
				tdObj.html(obj.no);
				trObj.append(tdObj);				
				tableObj.append(trObj);
				
				trObj = $("<tr>");
				tdObj = $("<td>");
				tdObj.html("상품명");
				trObj.append(tdObj);				
				tdObj = $("<td>");
				tdObj.html(obj.name);
				trObj.append(tdObj);				
				tableObj.append(trObj);

				trObj = $("<tr>");
				tdObj = $("<td>");
				tdObj.html("상품가격");
				trObj.append(tdObj);				
				tdObj = $("<td>");
				tdObj.html(obj.price);
				trObj.append(tdObj);				
				tableObj.append(trObj);
				//divObj.append(tableObj);
				
				trObj = $("<tr>");
				tdObj = $("<td>");
				tdObj.html("제조일자");
				trObj.append(tdObj);				
				tdObj = $("<td>");
				tdObj.html(obj.mfd);
				trObj.append(tdObj);				
				tableObj.append(trObj);
				
				trObj = $("<tr>");//테이블의 행태그용 객체생성
				tdObj = $("<td>");//열 객체생성
				tdObj.html("수량");//열 내용 설정
				trObj.append(tdObj);//행에 열추가				
				tdObj = $("<td>");//열 객체생성
				tdObj.html("<input type='number' name='cnt' min='1' value='1'>");//열 내용 설정
				trObj.append(tdObj);	
				tableObj.append(trObj);
				
				trObj = $("<tr>");//테이블의 행태그용 객체생성
				tdObj = $("<td colspan='2'>");//열 객체생성
				tdObj.html("<input type='button' name='btAddCart' value='장바구니넣기'>");//열 내용 설정
				trObj.append(tdObj);	
				tableObj.append(trObj);
				
				divObj.append(tableObj);
				$("section").append(divObj);
			}		    
		});
		return false;
	});
	$("section").on("click",
			        "div.div1 input[name=btAddCart]", 
			        function(){
		   			//alert("장바구니버튼이 클릭됨!");
		   				
		   			  var vno=$("section>div.div1>table>tr:first-child>td:nth-child(2)").html();
		   			  var vcnt=$("section>div.div1>table input[name=cnt]").val();
					  $.ajax({
						url:"addcart.do",
						method:"get",
						data: {no:vno, cnt:vcnt},//"no="+vno+"&cnt="+vcnt,
					    success:function(result){
					    	var obj = JSON.parse(result.trim());
					    	if(obj.status == 1){ //정상응답
					    		alert("장바구니에 추가되었습니다.");
					    		var aObj = $("nav>ul>li>a[href='productlist.do']");
				   				aObj.trigger("click");				   					
					    	}else {
					    		alert("장바구니에 넣기가 실패되었습니다.");
					    	}
					    }
					  });
					  return false;
					 
	});
	/* $("section").on("click", "nav>ul>li:nth-child(2)>a[href='logout.do']", function(){
		location.href = "semantic.jsp";
	}); */
	$("section").on("click", "div.div2 button", function(){
		alert("주문하기 클릭!!");
		$.ajax({
			url:"addorder.do", // property이름 : 값
			method:'get',
			success:function(result){
				var obj = JSON.parse(result.trim());
				if(obj.status==1){
					alert("주문 성공~");
					//주문목록보기로 이동
					  var aObj = $("nav>ul>li>a[href='vieworder.do']");
		 			  aObj.trigger("click");					
				}else if(obj.status==0){ // 로그인 안 한 경우
					var aObj = $("nav>ul>li>a[href='login.html']");
					aObj.trigger("click");
				}else{
					alert("주문 실팽~");
				}
			}
		});
		
		
	});
}
//$(document).ready(init);

$(init);
</script>
</head>

<body>
<header>머리말</header>
<jsp:include page="menu.jsp"/>
<section>본문</section>
<footer>꼬리말</footer>
</body>
</html>