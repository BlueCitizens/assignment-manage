try {
    $("<link>").attr({
        href: "../static/assets/waifu.min.css",
        rel: "stylesheet",
        type: "text/css"
    }).appendTo('head');
    $.ajax({
        url: "../static/assets/waifu-tips.min.js", dataType: "script", cache: true, success: function () {
            $.ajax({
                url: "../static/assets/live2d.min.js", dataType: "script", cache: true, success: function () {
                    /* 可直接修改部分参数 */
                    live2d_settings['hitokotoAPI'] = "hitokoto.cn";  // 一言 API
                    live2d_settings['modelId'] = 5;                  // 默认模型 ID
                    live2d_settings['modelTexturesId'] = 1;          // 默认材质 ID
                    live2d_settings['modelStorage'] = false;         // 不储存模型 ID
                    live2d_settings['waifuEdgeSide'] = 'right:20';         // 位置
                    /* 在 initModel 前添加 */
                    initModel("../static/assets/waifu-tips.json");
                }
            });
        }
    });
} catch (err) {
    console.log("[Error] JQuery is not defined.")
}
