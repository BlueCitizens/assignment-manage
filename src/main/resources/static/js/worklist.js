function notify(state, message, title, delay) {
    var placementFrom = "bottom";
    var placementAlign = "right";
    var state = state;
    var style = "withicon";
    var content = {};

    content.message = message;
    content.title = title;
    if (style == "withicon") {
        content.icon = 'fa fa-bell';
    } else {
        content.icon = 'none';
    }
    content.url = 'index.html';
    content.target = '_blank';

    $.notify(content, {
        type: state,
        placement: {
            from: placementFrom,
            align: placementAlign
        },
        time: 1000,
        delay: delay,
    });
}

function fmtDate(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + '时';
}

var xhrOnProgress = function (fun) {
    xhrOnProgress.onprogress = fun; //绑定监听
    //使用闭包实现监听绑
    return function () {
        //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
        var xhr = $.ajaxSettings.xhr();
        //判断监听函数是否为函数
        if (typeof xhrOnProgress.onprogress !== 'function')
            return xhr;
        //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
        if (xhrOnProgress.onprogress && xhr.upload) {
            xhr.upload.onprogress = xhrOnProgress.onprogress;
        }
        return xhr;
    }
}

function addWorkBlock(data) {
    var addon_file_type = "<div class=\"col-sm-6 col-md-6 work-block\" style=\"display: none\">\n" +
        "                                    <div class=\"card card-stats card-round\">\n" +
        "                                        <div class=\"card-body\">\n" +
        "                                            <div class=\"row align-items-center\">\n" +
        "                                                <div class=\"col-icon\" style=\"width: 10%\">\n" +
        "                                                    <div class=\"icon-big text-center icon-info\">";
    var addon_course_id = "</div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"col col-stats ml-3 ml-sm-0\">\n" +
        "                                                    <div class=\"numbers\">\n" +
        "                                                        <span class=\"course-id\" style=\"display: none\">";
    var addon_course_name = "</span>\n" +
        "                                                        <p class=\"card-category course-name\">";
    var addon_work_id = "</p>\n" +
        "                                                        <span class=\"work-id\" style=\"display: none\">";
    var addon_work_name = "</span>\n" +
        "                                                        <h4 class=\"card-title work-name\">";
    var addon_deadline = "</h4>\n" +
        "                                                    </div>\n" +
        "                                                    <div class=\"info-user ml-3\">\n" +
        "                                                        <div class=\"username deadline\">截止";
    var addon_naming_rule = "</div>\n" +
        "                                                        <div class=\"status naming-rule\">";
    var addon_end = "</div>\n" +
        "                                                    </div>\n" +
        "                                                    <div>\n" +
        "                                                        <input type=\"file\" class=\"work-upload\"\n" +
        "                                                               style=\"display: none\"/>\n" +
        "                                                        <button data-style=\"expand-left\"\n" +
        "                                                                class=\"btn btn-icon btn-primary btn-round btn-sm ladda-button upload-button\">\n" +
        "                                                            <i class=\"fa fa-upload\"></i>\n" +
        "                                                        </button>\n" +
        "                                                        <button class=\"btn btn-icon btn-danger btn-round btn-sm preview-button\">\n" +
        "                                                            <i class=\"fas fa-eye\"></i>\n" +
        "                                                        </button>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                    </div>\n" +
        "                                </div>";
    var type_word = "<i class=\"fas fa-file-word\"></i>";
    var course_name = "";
    if (data.course_name !== null) {
        course_name = data.course_name;
    }
    var work_block = addon_file_type + type_word
        + addon_course_id + data.course_id
        + addon_course_name + course_name
        + addon_work_id + data.work_id
        + addon_work_name + data.work_name
        + addon_deadline + fmtDate(data.deadline)
        + addon_naming_rule + data.naming_rule
        + addon_end;
    /*    $("#div1").fadeIn();
        $("#div2").fadeIn(1000);
        $("#div3").fadeIn(2000);*/
    return work_block;
}

$(document).ready(function () {
    /*var d = {};
    var t = $('form').serializeArray();
    $.each(t, function() {
        d[this.name] = this.value;
    });
    alert(JSON.stringify(d));*/
    var stu_id = $.session.get('stu_id');
    $.ajax({
        type: "post",
        url: "/get_own_work",
        data: "stu_id=" + stu_id,
        success: function (data) {
            console.log(JSON.stringify(data));
            var list_body_all = $('#all-list-div');
            var list_body_undo = $('#undo-list-div');
            var list_body_done = $('#done-list-div');
            var undo = new Array();
            var done = new Array();
            var item_head = "<div class=\"item-list\">";
            var item_tail = "</div>";
            for (var i = 0, l = data.length, step = 2; i < l; i += step) {
                if (l - i < 2) {
                    step = 1;
                }
                if (data[i].version === 0) {
                    console.log("alert");
                    undo.push(data[i]);
                } else {
                    console.log("dan");
                    done.push(data[i]);
                }

                var work_block = item_head + addWorkBlock(data[i]);
                if (i + 1 < l) {
                    if (data[i + 1].version === 0) {
                        undo.push(data[i + 1]);
                    } else {
                        done.push(data[i + 1]);
                    }
                    work_block += addWorkBlock(data[i + 1]);
                }
                work_block += item_tail;
                list_body_all.append(work_block);
            }
            for (var i = 0, l = undo.length, step = 2; i < l; i += step) {
                if (l - i < 2) {
                    step = 1;
                }
                var work_block = item_head + addWorkBlock(undo[i]);
                if (i + 1 < l) {
                    work_block += addWorkBlock(undo[i + 1]);
                }
                work_block += item_tail;
                list_body_undo.append(work_block);
            }
            for (var i = 0, l = done.length, step = 2; i < l; i += step) {
                if (l - i < 2) {
                    step = 1;
                }
                var work_block = item_head + addWorkBlock(done[i]);
                if (i + 1 < l) {
                    work_block += addWorkBlock(done[i + 1]);
                }
                work_block += item_tail;
                list_body_done.append(work_block);
            }
            $('.work-block').each(function () {
                $(this).fadeIn();
            })
        }
    });

    /*        $('#start').datepicker({setDate: new Date(), dateFormat: 'yy-mm-dd'});
            $('#end').datepicker({setDate: new Date(), dateFormat: 'yy-mm-dd'});*/


    /*$('.work-block').hover(function (){
        console.log("hover"+$(this).attr('id'));
    })*/

/*    $(document).on('click', '.preview-button', function () {
        var work_id = $(this).parents('.work-block').find('.work-id').html();
        console.log("work_id=" + work_id);
        var url = '/bus/peek_work_myhistory?' + 'work_id=' + work_id;
        $('#peek-myhistory-table').dataTable().fnClearTable();
        $('#myhistory-peek').modal('show');
        $.ajax({
            type: 'POST',
            url: url,
            success: function (data) {
                console.log(data);
                for (var i in data) { //遍历data 数组时，i为索引
                    $('#peek-myhistory-table').dataTable().fnAddData([
                        data[i].business_id,
                        data[i].file_name,
                        data[i].version,
                        fmtDate(data[i].time)
                    ]);
                }
            },
            error: function (responseStr) {
                //出错后的动作
                alert("出错,请联系管理员");
            }
        });
    });*/
    //太坑了 每执行一次就会绑定一次 导致再次点击时会多次触发事件 必须先解绑
    $(document).off("click", '.upload-button');
    $(document).on('click', '.upload-button', function () {
        //console.log("click 触发");
        $(this).parents('.work-block').find('.work-upload').click();
    });
    $(document).on('change', '.work-upload', function () {
        //console.log("onchange触发");
        //  如果value不为空，调用文件加载方法
        if ($(this).val() !== "" && $(this).isEmpty !== true) {
            var formData = new FormData();
            var stu_id = $.session.get('stu_id');
            var work_id = $(this).parents('.work-block').find('.work-id').html();
            var obj = "{\"stu_id\":\"" + stu_id + "\",\"work_id\":\"" + work_id + "\"}";
            var jsonStr = $.parseJSON(obj);
            formData.append('data', JSON.stringify(jsonStr));
            formData.append('file', $(this)[0].files[0]);
            console.log(work_id);
            console.log("发送前=" + $(this).val() + obj);
            //创建一个formData对象
            var l = Ladda.create($(this).parent().find('.upload-button')[0]);
            //files 添加到formData中，键值对形式
            $.ajax({
                url: '/bus/work_upload',
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                    console.log("ready for up");
                    l.start();
                    //发送之前的动作
                },
                success: function (data) {
                    /*alert("成功啦");*/
                    console.log(data);
                    notify("success", "上传成功", "系统", 5000);
                },
                error: function (responseStr) {
                    //出错后的动作
                    l.stop();
                    alert("出错啦");
                },
                xhr: xhrOnProgress(function (e) {
                    var percent = e.loaded / e.total;//计算百分比
                    l.setProgress(percent);
                    if (percent == 1) {
                        l.stop();
                    }
                })
            });
            $(this).val("");
            console.log("发送后=" + $(this).val());
        } else {
            console.log("未选择=" + $(this).val());

            $(this).val("");
        }
    })
    // You can control loading explicitly using the JavaScript API
    // as outlined below:

    // var l = Ladda.create( document.querySelector( 'button' ) );
    // l.start();
    // l.stop();
    // l.toggle();
    // l.isLoading();
    // l.setProgress( 0-1 );

});