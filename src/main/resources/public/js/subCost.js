onload = function() {
            var sel = document.getElementById('nextBonus');
            for(let i = 1; i < 13; i++){
                var option = document.createElement('option');
                option.value = i;
                var txt = document.createTextNode(i);
                option.appendChild(txt);
                sel.appendChild(op	tion);
            }
            var costModel = {};
            costModel["employeeNo"] = "LYC001";
            costModel["shoriKbn"] = "";
            var uploadURL = "/loadCost";
            $.ajax({
	            url: uploadURL,
	            type: "POST",
	            contentType:'application/json',
	            processData: false,
	            cache: false,
	            data: JSON.stringify(costModel),
	            timeout: 30000,
	            success: function(resultMap) {
	              var arr = resultMap.dataList;
	             $("#salary").val(arr[0].salary);
	             $("#waitSalary").val(arr[0].waitSalary);
	             $("#nextBonus").val(arr[0].nextBonus);
	             $("#transSalary").val(arr[0].transSalary);
	             $("#bonus").val(arr[0].bonus);
	             $("#remarks").val(arr[0].remarks);
	              
	            },
	          });
        }
function checkHave(){   
   if($('input:radio[name="isNotBonus"]:checked').val() == "notHave"){
    $("#bonus").attr("disabled",true);
    $("#nextBonus").attr("disabled",true);
    $("#nextBonus").val("0");
    $("#bonus").val("");
   }else if($('input:radio[name="isNotBonus"]:checked').val() == "yes"){
    $("#bonus").attr("disabled",false);
    $("#nextBonus").attr("disabled",false);
   }
}
function tokuroData(){
	var costModel = {};
    var formArray =$("#costForm").serializeArray();
    $.each(formArray,function(i,item){
    	costModel[item.name] = item.value;
    });
	$.ajax({
        url: "/toroku",
        type: "POST",
        contentType:'application/json',
        processData: false,
        cache: false,
        data: JSON.stringify(costModel),
        timeout: 30000,
        
      });
}
function bonusDateGet(){
    var date = $("#bonusDate").val();
    $("#nextBonusMonth").val(date.split("-")[1]);
}
function raiseDateGet(){
    var date = $("#raiseDate").val();
    $("#nextRaiseMonth").val(date.split("-")[1]);
}