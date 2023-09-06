function SaveCustomer() {
    let formData=$("#cusForm").serialize();
    $.ajax({
        url:"customer",
        method:"post",
        data:formData,
        success:function (res) {
            alert(res);
        },
        error:function (error){
            alert(error.responseJSON.message);
        }
    });
}