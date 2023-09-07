$("#btnCustomer").click(function () {
     SaveCustomer();

    });
function SaveCustomer() {
    let formData=$("#cusForm").serialize();
    $.ajax({
        url:"http://localhost:8080/pos/pages/"+"customer",
        method:"post",
        data:formData,
dataType:"json",
        success:function (res) {
            alert(res);
        },
        error:function (error){
            alert(error.responseJSON.message);
        }
    });
}