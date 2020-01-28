$(document).ready(function () {
    $('#conExcel').click(function () {
        var stulist = $('#temptable').innerHTML;
        if (stulist !== "" || stulist !== null) {
            var keyArray = [];
            var timeList = [];
            var nameList = [];
            //keyArray.push()在数组的末尾添加
            $('#temptable tr').each(function (i) {                   // 遍历 tr
                if ($(this).children().eq(0).text() !== "" && $(this).children().eq(1).text() !== "") {
                    timeList.push($(this).children().eq(0).text());
                    nameList.push($(this).children().eq(1).text());
                }
                /*$(this).children('td').each(function(j){  // 遍历 tr 的各个 td
                    alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+$(this).text()+"。");
                });*/
            });
            console.log(timeList);
            console.log(nameList);
            for (var i = 0; i < timeList.length; i++) {
                keyArray.push({
                    stu_id: timeList[i],
                    username: nameList[i],
                    password: "",
                });
            }
            console.log(JSON.stringify(keyArray));
            let jsonObj = $.parseJSON(JSON.stringify(keyArray));//json字符串转化成json对象(jq方法)
            $.ajax({
                type: "post",
                data: JSON.stringify(keyArray),
                contentType: 'application/json',
                url: "/save_all",
                success: function (data) {
                    if (data == 1) {
                        $('#context').text('');
                        alert("添加成功！");
                    } else {
                        alert("添加失败！");
                        return false;
                    }
                    getcomment();
                }
            });
        } else {
            alert("请先选择文件");
        }
        $('#excelModal').modal('hide');
    });
})