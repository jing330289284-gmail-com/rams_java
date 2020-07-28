const $ = require('jquery');
const axios = require('axios');
var oldForm_data;
var oldForm_dataJson;
var newForm_data;
var newForm_dataJson;

//radio切换
export  function checkHave(radioName){   
    var changeId = (radioName == "SocialInsuranceFlag") ? "InsuranceFee" : "BonusFee";
    if($('input:radio[name="'+radioName+'"]:checked').val() == "1"){
        $("#"+changeId+"").attr("disabled",true);
        $("#"+changeId+"").val("");
        if(radioName == "SocialInsuranceFlag"){
          $("#jidokeisan").attr("disabled",true);
        }
    }else if($('input:radio[name="'+radioName+'"]:checked').val() == "0"){
        $("#"+changeId+"").attr("disabled",false);
        if(radioName == "SocialInsuranceFlag"){
          $("#jidokeisan").attr("disabled",false);
        }
    }
 }
 //从日历获取月份
 export function monthGet(calendarId){
    var date = $("#"+calendarId+"").val();
    var needMonth = calendarId.split("Date")[0] + "Month";
    $("#"+needMonth+"").val(date.split("-")[1]);
 }
 //设置日历最小值
 export function dateMinValue(){
    var dateNow = new Date();
    var yearNow = dateNow.getFullYear();
    var month = dateNow.getMonth()+1<10 ? "0" +(dateNow.getMonth()+1) : (dateNow.getMonth()+1);
    var date = dateNow.getDate() < 10 ? "0"+dateNow.getDate() : dateNow.getDate();
    $("#NextBonusDate").attr("min",yearNow+"-"+month+"-"+date);
    $("#NextRaiseDate").attr("min",yearNow+"-"+month+"-"+date);
}
//登录按钮
export function tokuro(){
  newForm_data = $("#costForm").serializeArray();
  newForm_dataJson = JSON.stringify({ dataform: newForm_data });
  if(newForm_dataJson != oldForm_dataJson){
    var costModel = {};
    var formArray =$("#costForm").serializeArray();
    $.each(formArray,function(i,item){
      costModel[item.name] = item.value;     
    });
    axios.post("http://127.0.0.1:8080/toroku", costModel)
    .then(function (result) {
      
      if(result.data == true){
        alert("登录完成");
        window.close();
      }else{
        alert("登录错误，请检查程序");
      }
    })
    .catch(function (error) {
      alert("登录错误，请检查程序");
    });
  }else{
    alert("修正してありません!")
  }
    
}
//页面加载
export function onloadPage(){
  var costModel = {};
  costModel["employeeNo"] = $("#employeeNo").val();
  costModel["shoriKbn"] = $("#shoriKbn").val();
  axios.post("http://127.0.0.1:8080/loadCost", costModel)
  .then(function (resultMap) {
    var resultList = resultMap.data.dataList
    $("#BonusFee").val(resultList[0]["BonusFee"]);
      if(resultList[0]["BonusFlag"] == "0"){
        $("#bonusCheckYes").attr("checked",true)
      }else{
        $("#bonusCheckNo").attr("checked",true)
      }
    $("#InsuranceFee").val(resultList[0]["InsuranceFee"]);
      if(resultList[0]["SocialInsuranceFlag"] == "0"){
        $("#hokenCheckYes").attr("checked",true)
      }else{
        $("#hokenCheckNo").attr("checked",true)
      }
    $("#TransportationExpenses").val(resultList[0]["TransportationExpenses"]);
    $("#remark").val(resultList[0]["remark"]);
    $("#salary").val(resultList[0]["salary"]);
    $("#WaitingCost").val(resultList[0]["WaitingCost"]);
    $("#NextBonusMonth").val(resultList[0]["NextBonusMonth"]);
    $("#NextRaiseMonth").val(resultList[0]["NextRaiseMonth"]);
    oldForm_data = $("#costForm").serializeArray();
    oldForm_dataJson = JSON.stringify({ dataform: oldForm_data });
  })
  .catch(function (error) {
    alert("查询错误，请检查程序");
  });
}
//页面不可更改
export function setDisabled(){
  $("#BonusFee").attr("disabled",true)
  $("#bonusCheckYes").attr("disabled",true)
  $("#bonusCheckNo").attr("disabled",true)
  $("#InsuranceFee").attr("disabled",true);
  $("#hokenCheckYes").attr("disabled",true)
  $("#hokenCheckNo").attr("disabled",true)
  $("#TransportationExpenses").attr("disabled",true)
  $("#remark").attr("disabled",true)
  $("#salary").attr("disabled",true)
  $("#WaitingCost").attr("disabled",true)
  $("#NextBonusMonth").attr("disabled",true)
  $("#NextRaiseMonth").attr("disabled",true)
  $("#toroku").attr("disabled",true)
  $("#reset").attr("disabled",true)
  $("#jidokeisan").attr("disabled",true)
}
