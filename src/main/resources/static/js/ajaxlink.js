function LoadAjaxContent(url){
    $('.preloader').show();
    $.ajax({
        mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
        url: url,
        type: 'GET',
        success: function(data) {
            $('#ajax-content').html(data);
            $('.preloader').hide();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        },
        dataType: "html",
        async: false
    });
}

$(document).ready(function () {
    $('.main-menu').on('click', 'a', function (e) {
        /*var parents = $(this).parents('li');
        var li = $(this).closest('li.dropdown');
        var another_items = $('.main-menu li').not(parents);
        another_items.find('a').removeClass('active');
        another_items.find('a').removeClass('active-parent');*/
        if ($(this).hasClass('ajax-link')) {
            e.preventDefault();
            /*if ($(this).hasClass('add-full')) {
                $('#content').addClass('full-content');
            } else {
                $('#content').removeClass('full-content');
            }*/
            var url = $(this).attr('href');
            window.location.hash = url;
            LoadAjaxContent(url);
        }
        if ($(this).attr('href') === '#') {
            e.preventDefault();
        }
    });
});